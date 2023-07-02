package com.impt.Gestion_Ecole.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// ...

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    public void someMethod() {
        // You can access the email and password values here
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/js/**",
                        "/css/**",
                        "/assets/**",
                        "/img/**").permitAll()
                .antMatchers("/**").permitAll()


                .antMatchers("/professeur-form/**").permitAll()
                .antMatchers("/professeur-list/**").permitAll()
                .antMatchers("/professeurs/**").permitAll()
                .antMatchers("/detail-professeurs/**").permitAll()
                .antMatchers("/professeurs/edit/**").permitAll()
                .antMatchers("/edit-professeurs/**").permitAll()
                .antMatchers("/pagination-professeurs/**").permitAll()

                .antMatchers("/ajouter/**").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/registration_admin/**").permitAll()
                .antMatchers("/registration_prof/**").permitAll()
                .antMatchers("/professeur/**").permitAll()
                .antMatchers("/registration_etudiante/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")

                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/user")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }
}
