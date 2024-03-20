package com.platzi.platzipizzeria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platzi.platzipizzeria.service.dto.LoginDto;
import com.platzi.platzipizzeria.web.config.JwtUtil;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    
    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }



    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto LoginDto){
        //este autauthenticationManager va a loadUserByUsername de usersecurityservice ya que es la clase que implementa la seguridad por medio de UserDetailsService
        UsernamePasswordAuthenticationToken login =new UsernamePasswordAuthenticationToken(LoginDto.getUsername(), LoginDto.getPassword());
        Authentication authentication=this.authenticationManager.authenticate(login);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String jwt=this.jwtUtil.create(LoginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,jwt).build();
    }    
}
