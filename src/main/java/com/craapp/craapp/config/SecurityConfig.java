package com.craapp.craapp.config;


import com.craapp.craapp.services.AppuserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final AppuserDetailsService appuserDetailsService;

    @Autowired
    private CorsFilterConfig corsFilterConfig;

    public SecurityConfig(AppuserDetailsService appuserDetailsService) {
        this.appuserDetailsService = appuserDetailsService;
    }



   /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }*/
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain FilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(csrf -> csrf.disable()/*ignoringAntMatchers("/h2-console/**")*/)
                .authorizeRequests(auth -> auth
                        .antMatchers("/api/**").permitAll()
                        .antMatchers("/api/auth/signin").permitAll()
                        .antMatchers("/h2-console/**").permitAll()
                        .antMatchers("/api/user").hasRole("USER")
                        .antMatchers("/api/admin").hasRole("ADMIN")
                        .antMatchers("/api/home").permitAll()
                        .anyRequest().authenticated())
                        .userDetailsService(appuserDetailsService)
                .addFilterBefore(corsFilterConfig, ChannelProcessingFilter.class)
                        .headers(headers -> headers.frameOptions().sameOrigin())
                        .httpBasic(Customizer.withDefaults()).build();



    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
