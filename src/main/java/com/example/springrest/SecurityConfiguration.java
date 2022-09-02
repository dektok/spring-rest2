package com.example.springrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Вариант 2 (через  @PreAuthorize)
        http.csrf().disable().authorizeHttpRequests((httpz) ->
                        httpz
                                .antMatchers("/users", "/users/{id}").hasAnyRole("ROLE1","ROLE2")
        ).httpBasic();

        /*  Вариант 1
        http.csrf().disable().authorizeHttpRequests((httpz) ->
                httpz
                        .antMatchers(HttpMethod.GET, "/users", "/users/{id}").hasAnyRole("ROLE1","ROLE2")
                        .antMatchers(HttpMethod.POST,"/users").hasRole("ROLE2")
                        .antMatchers(HttpMethod.PUT,"/users/{id}").hasRole("ROLE2")
                        .antMatchers(HttpMethod.DELETE,"/users/{id}").hasRole("ROLE2")
        ).httpBasic();

 */
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager detailsManager() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password1")
                .roles("ROLE1")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("password2")
                .roles("ROLE2")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
