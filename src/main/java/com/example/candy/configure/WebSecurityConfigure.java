package com.example.candy.configure;

import com.example.candy.security.Jwt;
import com.example.candy.security.JwtAuthenticationProvider;
import com.example.candy.security.JwtAuthenticationTokenFilter;
import com.example.candy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {
    @Autowired
    private Jwt jwt;
    @Autowired
    private JwtTokenConfigure jwtTokenConfigure;
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
//                .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                    .and()

                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()

                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .and()

                .formLogin()
                    .disable();
        http
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
        web.ignoring().antMatchers("/h2-console/**");

    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(jwtTokenConfigure.getHeader(), jwt);
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder builder, JwtAuthenticationProvider authenticationProvider) {
        // AuthenticationManager 는 AuthenticationProvider 목록을 지니고 있다.
        // 이 목록에 JwtAuthenticationProvider 를 추가한다.
        builder.authenticationProvider(authenticationProvider);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider(Jwt jwt, UserService userService) {
        return new JwtAuthenticationProvider(jwt, userService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
