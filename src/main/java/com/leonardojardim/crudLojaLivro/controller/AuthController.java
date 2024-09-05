package com.leonardojardim.crudLojaLivro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardojardim.crudLojaLivro.model.User;
import com.leonardojardim.crudLojaLivro.security.CustomUserDetailsService;
import com.leonardojardim.crudLojaLivro.security.JwtTokenUtil;
import com.leonardojardim.crudLojaLivro.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> user) {
        String email = user.get("email");
        String password = user.get("password");

        // Autenticar o usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        // Configurar o contexto de autenticação
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Carregar os detalhes do usuário a partir do e-mail
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Gerar o token JWT com os detalhes do usuário
        String token = jwtTokenUtil.generateToken(userDetails);

        // Retornar o token no corpo da resposta
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
}
