package com.platzi.platzipizzeria.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    //establece la configuracion inicial por defectro de  la aplicacion ,en esta estamos haciendo un filtro de segurdad en el que interceptamos
    // las http request y autorizamos cualquiere peticion el cual ese autenticado , esta autenticacion aca es la Basic 
    // donde se le envia el usuario,este dato lo genera spring una vez se ejecute la aplicacion en desarrollo
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(customizeRequests -> {
                customizeRequests
                        // .anyRequest()
                        // .authenticated()
                        .requestMatchers(HttpMethod.GET,"/pizzeria/api/pizzas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT).denyAll()
                        .anyRequest().authenticated();
                }
            )
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
}
