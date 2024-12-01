package lernia.c10_springboot_v2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(GET, "/api/category/**").permitAll()
                        .requestMatchers(POST, "/api/category/**").hasAuthority("SCOPE_admin")


                        .requestMatchers(GET, "/api/locations/**").permitAll()
                        .requestMatchers(GET, "/api/locations/category/**").permitAll()
                        .requestMatchers(GET, "/api/locations/radius/**").permitAll()
                        .requestMatchers(POST, "/api/locations/**").authenticated()
                        .requestMatchers(PUT, "/api/locations/**").authenticated()
                        .requestMatchers(DELETE, "/api/locations/**").authenticated()
                        .anyRequest().denyAll()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(Customizer.withDefaults())
                );
        return http.build();
    }
}