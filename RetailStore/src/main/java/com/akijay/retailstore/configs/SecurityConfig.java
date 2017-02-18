package com.akijay.retailstore.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The Spring SecurityConfig file
 *
 * @EnableWebSecurity is needed so that AuthenticationManagerBuilder can be configured.
 * It can only be configured in a class that is annotated with @EnableWebSecurity,
 * @EnableGlobalMethodSecurity, or @EnableGlobalAuthentication.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).hasRole("SUPERUSER")
                .and()
                .formLogin();
                //If no loginPage/failureUrl is provided, Spring will provide a default one. Uncomment below to add custom forms.
                //.loginPage("/login").failureUrl("/login-error");

                /*http.authorizeRequests()
                .antMatchers("/css/**" , "/index/**").hasRole("USER")
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login-error"); */

        //TODO: Temporariliy disabling until we write our own custom JSP page
        http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("admin").roles("SUPERUSER");
    }


}
