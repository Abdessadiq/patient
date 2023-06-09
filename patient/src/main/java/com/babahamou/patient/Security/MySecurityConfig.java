package com.babahamou.patient.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * La classe pour la configuration de spring security
 */
@Configuration
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.jdbcAuthentication()
                .dataSource(dataSource).usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
                .passwordEncoder(passwordEncoder)
                .rolePrefix("ROLE_");


//        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("azertyuio")).roles("USER")
//                .and().withUser("user2").password(passwordEncoder.encode("azertyuio")).roles("USER")
//                .and().withUser("user3").password(passwordEncoder.encode("azertyuio")).roles("USER")
//                .and().withUser("admin").password(passwordEncoder.encode("azertyuiop")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        //http.formLogin();
        http.authorizeRequests().antMatchers("/save**/**","/delete**/**", "/form**/**", "/edit**/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");

        http.authorizeRequests().antMatchers("/login", "/user/**", "ressources/**").permitAll();
       // http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
        http.csrf();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
