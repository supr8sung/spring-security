package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.example.demo.security.ApplicationUserRole.ADMIN;
import static com.example.demo.security.ApplicationUserRole.ADMINTRAINEE;
import static com.example.demo.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
                .and()
                // .httpBasic();
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/courses", true)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .rememberMeParameter("remember-me")
                .key(UUID.randomUUID().toString())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID","remember-me")
                .logoutSuccessUrl("/login");
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails user =
                User.builder().username("user").password(passwordEncoder().encode("root"))
//                        .roles(STUDENT.name())
                        .authorities(STUDENT.getGrantedAuthorities())
                        .build();
        UserDetails admin =
                User.builder().username("admin").password(
                        passwordEncoder().encode("root"))
                        // .roles(ADMIN.name())
                        .authorities(ADMIN.getGrantedAuthorities())
                        .build();
        UserDetails adminTrainee =
                User.builder().username("admintrainee")
                        .password(passwordEncoder().encode("root"))
//                        .roles(ADMINTRANIE.name())
                        .authorities(ADMINTRAINEE.getGrantedAuthorities())
                        .build();
        return new InMemoryUserDetailsManager(user, admin, adminTrainee);
//        return new InMemoryUserDetailsManager(Arrays.asList(user, admin,adminTrainee));
    }
}
