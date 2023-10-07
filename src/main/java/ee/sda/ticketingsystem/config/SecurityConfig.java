package ee.sda.ticketingsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.sda.ticketingsystem.service.UserDetailServiceImp;
import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor

public class SecurityConfig {

    private static final String REGISTER_ENDPOINT = "/api/v1/user/register";
    private static final String LOGIN_ENDPOINT = "/api/v1/user/login";
    private static final int COOKIE_VALIDATION_MINUTES = 60;

    private UserDetailServiceImp userDetailServiceImp;

    private ObjectMapper mapper;




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailServiceImp);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authRequests())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(successLogin())
                .rememberMe(rememberMe())
                .build();
    }

    private Customizer<RememberMeConfigurer<HttpSecurity>> rememberMe() {
        return rememberMe -> rememberMe
                .tokenValiditySeconds(COOKIE_VALIDATION_MINUTES * 60 * 60)
                .useSecureCookie(true);
    }

    private Customizer<FormLoginConfigurer<HttpSecurity>> successLogin() {
        return formLogin -> formLogin
                .loginProcessingUrl(LOGIN_ENDPOINT)
                .successHandler((request, response, authentication) -> {
                    System.out.println("Login successful");
                    response.setContentType("application/json;charset=UTF-8");
                    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                    User userEntity = userDetailServiceImp.findByUsername(userDetails.getUsername());
                    String json = mapper.writeValueAsString(userEntityToUserDto(userEntity));
                    response.getWriter().write(json);
                });
    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authRequests() {
        return authorizeRequests -> authorizeRequests
                .requestMatchers(REGISTER_ENDPOINT,LOGIN_ENDPOINT).permitAll()
                .anyRequest().authenticated();
    }

    private UserDTO userEntityToUserDto(User userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getUserId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUserType(userEntity.getUserType());
        userDTO.setName(userEntity.getName());

        return  userDTO;




    }


}
