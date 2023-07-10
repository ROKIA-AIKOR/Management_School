package com.impt.Gestion_Ecole.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

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
    @Bean
    public HttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/js/**",
                        "/css/**",
                        "/assets/**",
                        "/fragments/**",
                        "/layouts/**",
                        "/static/**",
                        "/images/**",
                        "/img/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/home/**").permitAll()

                //professeur

                .antMatchers("/professeur-form/**").permitAll()
                .antMatchers("/professeur-list/**").permitAll()
                .antMatchers("/professeurs/**").permitAll()
                .antMatchers("/detail-professeurs/**").permitAll()
                .antMatchers("/professeurs/edit/**").permitAll()
                .antMatchers("/pagination-professeurs/**").permitAll()

                //Matiere
                .antMatchers("/matiere-form/**").permitAll()
                .antMatchers("/matiere-list/**").permitAll()
                .antMatchers("/matiere/**").permitAll()
                .antMatchers("/matiere-delete/**").permitAll()
                .antMatchers("/matieres/edit/**").permitAll()


                //Etudiantes
                .antMatchers("/etudiant-list/**").permitAll()
                .antMatchers("/detail-etudiants/**").permitAll()
                .antMatchers("/etudiant-form/**").permitAll()
                .antMatchers("/etudiants/edit/**").permitAll()
                .antMatchers("/etudiants/delete/**").permitAll()

                //Note
                .antMatchers("/note-list/**").permitAll()
                .antMatchers("/detail-notes/**").permitAll()
                .antMatchers("/note-form/**").permitAll()
                .antMatchers("/notes/edit/**").permitAll()
                .antMatchers("/delete-notes/**").permitAll()



                .antMatchers("/index/**").permitAll()
                .antMatchers("/home/**").permitAll()
                .antMatchers("/registration_admin/**").permitAll()
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
