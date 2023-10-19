package ee.sda.ticketingsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ee.sda.ticketingsystem.dto.UserDTO;
import ee.sda.ticketingsystem.entity.User;
import ee.sda.ticketingsystem.hydrator.UserHydrator;
import ee.sda.ticketingsystem.service.UserDetailServiceImp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
@NoArgsConstructor
public class SecurityConfig {

    private static final String REGISTER_ENDPOINT = "/api/v1/user/register";
    private static final String LOGIN_ENDPOINT = "/api/v1/login";
    private static final int COOKIE_VALIDATION_MINUTES = 60;
    private UserDetailServiceImp userDetailServiceImp;
    private ObjectMapper mapper;
    private UserHydrator userHydrator;

    @Autowired
    public SecurityConfig(UserDetailServiceImp userDetailServiceImp, ObjectMapper mapper, UserHydrator userHydrator) {
        this.userDetailServiceImp = userDetailServiceImp;
        this.mapper = mapper;
        this.userHydrator = userHydrator;
    }

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
        configuration.setAllowedOrigins(List.of("**")); // Allow this origin ie angular
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
                    String json = mapper.writeValueAsString(userHydrator.convertToDTO(userEntity));
                    response.getWriter().write(json);
                });

    }

    private Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authRequests() {
        return authorizeRequests -> authorizeRequests
                .requestMatchers(REGISTER_ENDPOINT, LOGIN_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.PUT,"/api/v1/ticket/agent").hasRole("AGENT")
                .requestMatchers(HttpMethod.POST,"/api/v1/ticket/agent").hasRole("AGENT")
                .requestMatchers(HttpMethod.GET,"/api/v1/ticket/agent").hasRole("AGENT")
                .requestMatchers(HttpMethod.DELETE ,"/api/v1/ticket/agent").hasRole("AGENT")
                .requestMatchers(HttpMethod.POST, "/api/v1/ticket/user").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.GET, "/api/v1/ticket/user").hasRole("CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/v1/ticket/user").hasRole("CUSTOMER")
                .anyRequest().authenticated();
    }
}
