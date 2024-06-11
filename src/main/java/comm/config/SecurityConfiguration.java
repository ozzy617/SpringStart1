package comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static comm.database.entity.Role.ADMIN;
import static comm.database.entity.Role.OPERATOR;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth ->  auth
                        .requestMatchers("/login", "/users/registration", "/v3/api-docs/", "/swagger-ui/").permitAll()
                        .requestMatchers("/admin/**").hasRole(ADMIN.getAuthority())
                        .requestMatchers(antMatcher("/users/{\\d}/delete")).hasAnyAuthority(ADMIN.getAuthority(), OPERATOR.getAuthority())
                        .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults());
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }
}
