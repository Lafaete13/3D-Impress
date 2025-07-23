package com.impress3d.demo.configurations;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(request -> {
	            CorsConfiguration config = new CorsConfiguration();
	            config.setAllowedOrigins(Arrays.asList("*"));
	            config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	            config.setAllowedHeaders(Arrays.asList("*"));
	            config.setAllowCredentials(true);
	            return config;
	        }))
	        .authorizeHttpRequests(auth -> auth
	     
	            .requestMatchers("/h2-console/**").permitAll()

	            
	            .requestMatchers("/api/auth/**").permitAll()
	            .requestMatchers("/api/produtos/**").permitAll()
	            .requestMatchers("/api/cliente/cadastro").permitAll()
	            .requestMatchers("/api/admin/**").hasRole("ADMIN")
	            .requestMatchers("/api/cliente/**", "/api/carrinho/**", "/api/pedidos/**", "/api/solicitacoes/**")
	                .hasAnyRole("USER", "ADMIN")
	            .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        
	        
	        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

	    return http.build();
	}

}
