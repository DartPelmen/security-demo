package edu.festu.ivankuznetsov.securitydemo.security;


import edu.festu.ivankuznetsov.securitydemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AccountService accountService;

    public SecurityConfig(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }

    @Bean
    static BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .cors().disable().formLogin()
                .and()
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .antMatchers(HttpMethod.GET,"/","/login**","/logout**")
                            .permitAll()
                            .antMatchers(HttpMethod.GET,"/admin").hasRole("ADMIN")
                            .antMatchers(HttpMethod.GET,"/orders").hasAnyRole("USER","ADMIN")
                            .antMatchers(HttpMethod.GET,"/user").hasRole("USER");
                }).httpBasic(Customizer.withDefaults()).build();
    }
}
