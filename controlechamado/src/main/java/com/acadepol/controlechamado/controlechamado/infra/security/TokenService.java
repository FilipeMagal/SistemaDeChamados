package com.acadepol.controlechamado.controlechamado.infra.security;

import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Usuario user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);

            // Gerando o token JWT
            String token = JWT.create()
                    .withIssuer("ControleChamado")  // Emissor do token
                    .withSubject(String.valueOf(user.getMatricula()))  // Sujeito (a matricula do usuário)
                    .withExpiresAt(this.generateExpirationDate())  // Expiração do token (2 hora)
                    .withClaim("id", user.getMatricula())  // Claim personalizada: ID do usuário
                    .withClaim("role", (user.getTipoUsuario()).toString())  // Claim personalizada: Tipo de usuário
                    .sign(algorithm);  // Assinando o token com o algoritmo e a chave secreta

            return token;  // Retorna o token gerado
        }catch (JWTCreationException exception){
            throw new RuntimeException("Error while authenticating", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("ControleChamado")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }
    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

