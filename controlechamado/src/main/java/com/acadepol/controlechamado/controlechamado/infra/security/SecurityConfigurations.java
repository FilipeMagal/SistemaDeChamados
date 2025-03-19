package com.acadepol.controlechamado.controlechamado.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())  // Garantir que o CSRF está desabilitado
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Definindo sessão sem estado
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/acadepol/auth/login").permitAll()  // Login permitido sem autenticação
                        .requestMatchers(HttpMethod.POST, "/acadepol/auth/cadastro").hasAnyRole( "ADMINISTRADOR") // Cadastro com autenticação
                        .requestMatchers(HttpMethod.GET, "/acadepol/chamado/chamados").hasAnyRole("ADMINISTRADOR", "TECNICO") // Protegido
                        .requestMatchers(HttpMethod.PUT, "/acadepol/chamado/registro/{id}").hasAnyRole("ADMINISTRADOR", "TECNICO") // Protegido
                        .requestMatchers(HttpMethod.DELETE, "/acadepol/chamado/deletar/{id}").hasAnyRole( "ADMINISTRADOR") // Protegido
                        .anyRequest().authenticated())// Qualquer outra requisição precisa de autenticação
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
