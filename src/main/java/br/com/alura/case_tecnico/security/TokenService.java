package br.com.alura.case_tecnico.security;

import br.com.alura.case_tecnico.entity.User;
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
    private String SECRET_KEY;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT
                    .create()
                    .withIssuer("case-tecnico")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException jwtCreationException) {
            // TODO: criar exception personalizada
            throw new RuntimeException("Error while authenticating");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            return JWT
                    .require(algorithm)
                    .withIssuer("case-tecnico")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException jwtVerificationException) {
            return null;
        }
    }

}
