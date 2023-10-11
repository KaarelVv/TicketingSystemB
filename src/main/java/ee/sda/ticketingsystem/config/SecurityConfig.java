package ee.sda.ticketingsystem.config;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.service.UserDetailServiceImp;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

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
        public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Allow this origin ie angular
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        // Optional: Enable credentials, if you need to send cookies or authentication headers
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authRequests())
                .cors()
                .and()
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
                .requestMatchers("/api/v1/ticket/agent").hasRole("AGENT")
                .requestMatchers("/api/v1/ticket/user").hasRole("USER")
                .anyRequest().authenticated();
    }

    private UserDTO userEntityToUserDto(User userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setUserType(userEntity.getUserType());
        userDTO.setName(userEntity.getName());

        return  userDTO;
    }
}
