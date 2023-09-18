package com.macedocaio.customermanager.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> {
            request.anyRequest().hasAnyRole("ADMINISTRATOR", "CUSTOMERS");
        }).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public static UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(getPasswordEncoder().encode("admin"))
                .roles("ADMINISTRATOR")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
