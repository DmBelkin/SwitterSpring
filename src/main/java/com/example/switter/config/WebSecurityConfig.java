package com.example.switter.config;
import com.example.switter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/registration", "/static/**", "/activate/*").permitAll()
                        .anyRequest().
                        authenticated().
                        and()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll().
                        and()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }



    @Autowired
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService(userService).
          passwordEncoder(encoder);
    }
}
