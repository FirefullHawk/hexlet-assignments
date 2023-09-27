package exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import exercise.model.UserRole;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    final UserDetailsServiceImpl userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // BEGIN
        http.csrf().disable().authorizeHttpRequests().and().sessionManagement().disable();

        http.csrf().disable()
                .authorizeHttpRequests((request) -> request
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users")
                        .hasAnyAuthority(UserRole.USER.name(), UserRole.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE).hasAuthority(UserRole.ADMIN.name())
                        .anyRequest().authenticated()
                )
                .httpBasic();
        return http.build();
        // END
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
