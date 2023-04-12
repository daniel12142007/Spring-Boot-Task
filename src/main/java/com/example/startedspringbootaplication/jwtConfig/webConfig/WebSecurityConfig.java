package com.example.startedspringbootaplication.jwtConfig.webConfig;

import com.example.startedspringbootaplication.jwtConfig.JwtConfig;
import com.example.startedspringbootaplication.jwtConfig.JwtTokenFilter;
import com.example.startedspringbootaplication.jwtConfig.JwtUtils;
import com.example.startedspringbootaplication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserRepository authInfoRepository;
    private final JwtConfig jwtConfig;
    private final JwtUtils jwtUtils;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(getUserDetailsService());
        provider.setPasswordEncoder(getPasswordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        return (email) -> authInfoRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        String.format("not found exception=%s", email)
                ));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/test", "/swagger-ui.html").permitAll()
                .anyRequest()
                .permitAll();
        http.addFilterBefore(
                new JwtTokenFilter(
                        jwtConfig,
                        jwtUtils,
                        getUserDetailsService()),
                UsernamePasswordAuthenticationFilter.class
        );
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}