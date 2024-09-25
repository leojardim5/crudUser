package com.leonardojardim.crudLojaLivro.security;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // Extrair o username do token JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrair data de expiração do token JWT
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrair qualquer claim do token JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extrair todos os claims do token JWT
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getDecoder().decode(secretKey))
                .parseClaimsJws(token)
                .getBody();
    }

    // Verificar se o token está expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Gerar o token JWT, incluindo as roles (papéis) do usuário
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        // Adiciona as roles (authorities) ao token JWT
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("roles", roles); // Adiciona as roles ao claim "roles"
        return createToken(claims, userDetails.getUsername());
    }

    // Criar o token com as claims e o subject (username)
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiração em 10 horas
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    // Validar o token comparando o username e verificando a expiração
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
