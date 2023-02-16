package com.trkgrn.studentinformationsystem.config.security;

import com.trkgrn.studentinformationsystem.config.security.jwt.service.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtFilter jwtFilter;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtFilter jwtFilter, UserDetailsService userDetailsService) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(AUTH_WHITE_LIST)
                .permitAll()
                .antMatchers("/api/user/**")
                .hasAnyAuthority("STUDENT", "TEACHER","ADMIN")
                .anyRequest()
                .authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }


    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html/**",
            "/webjars/**",
            "/csrf/**",
            "/api/auth/**",
            "/api/role/**",
            "/api/faculty/**",
            "/api/branch/**",
            "/api/class/**",
            "/api/teacher/**",
            "/api/student/**",
            "/api/lesson/**",
            "/api/live-lesson/**",
            "/api/note/**",
            "/api/letter-grade/**",
            "/api/education-season/**",
            "/api/lesson-request/**"};


}
