package com.platzi.platzipizzeria.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
//con esto habilito la validacion de roles por metdio de antocaciones en los metodos de los servicios ,esos irian con la anotacion @Secured
@EnableMethodSecurity(securedEnabled =true )
public class SecurityConfig {
    //establece la configuracion inicial por defectro de  la aplicacion ,en esta estamos haciendo un filtro de segurdad en el que interceptamos
    // las http request y autorizamos cualquiere peticion el cual ese autenticado , esta autenticacion aca es la Basic 
    // donde se le envia el usuario,este dato lo genera spring una vez se ejecute la aplicacion en desarrollo

    private final JwtFilter jwtFilter;
    
    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(customizeRequests -> {
                customizeRequests
                        // .anyRequest()
                        // .authenticated()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/customers/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/pizzas/**").hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers(HttpMethod.POST,"/pizzas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        // primero aplica el permiso de random_order , porque el has rolse si esta de primera no la va a autorizar
                        .requestMatchers("/orders/random").hasAuthority("random_order")
                        .requestMatchers(HttpMethod.GET,"/orders").hasAuthority("getOrder")
                        .requestMatchers("/orders/**").hasRole("ADMIN")
                        .anyRequest().authenticated();
                }
            )
            .csrf(AbstractHttpConfigurer::disable)
            .cors(Customizer.withDefaults())
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // .httpBasic(Customizer.withDefaults())
            //agregar filtro de autehnticacion por JWT PRINCIPALMENTE
            .addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }


    //esto se hace para gestionar usuariores en memoria , le pasamos el nombre de user , la password con el tipo de 
    // encode para encryptar, para el rol ADMIN ,SE PRUEBA CON LA RUTA  http://localhost:8090/pizzeria/api/orders SI NO SE LE PASA 
    // BASIC AUTH CON LA INFO DEL USUARIO EN MEMORIA  VA A SALIR NO AUTORIZADO 
    // @Bean
    // public UserDetailsService memoryUsers(){
    //     UserDetails admin= User.builder()
    //         .username("admin")
    //         .password(passwordEncoder().encode("admin"))
    //         .roles("ADMIN")
    //         .build();


    //         UserDetails customer= User.builder()
    //         .username("customer")
    //         .password(passwordEncoder().encode("customer123"))
    //         .roles("CUSTOMER")
    //         .build();

    //         return new InMemoryUserDetailsManager(admin,customer);
    // }

    // se comenta porque se hace el uso por medio de el servicio userSecurityService
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
