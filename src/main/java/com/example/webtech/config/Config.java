package com.example.webtech.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Config {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    SuccessHandler successHandler;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((request)->request
                .requestMatchers("/login/**","/regist/**","/index/**","/Man_HomePage/**","/Woman_HomePage/**","/Kid_HomePage/**").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers("/addNewUser/**"
                ,"/addNewRole/**"
                ,"/addNewColor/**"
                ,"/addNewCategory/**"
                ,"/addNewSize/**"
                ,"/addNewProduct/**"
                ,"/getAllProduct/**"
                ,"/getAllCategory/**"
                ,"/getAllColor/**"
                ,"/getAllRole/**"
                ,"/getAllSize/**"
                ,"getAllUser/**").hasRole("ADMIN")
                .requestMatchers("/Cart/**").hasRole("USER")
                .anyRequest().authenticated())
//                .anyRequest().permitAll())
                .formLogin(
                        form->form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler)
                        .permitAll())
                .logout((logout)->logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll());
        return http.build();
    }
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
