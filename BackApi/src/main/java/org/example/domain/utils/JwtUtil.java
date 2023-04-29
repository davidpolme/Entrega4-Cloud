package org.example.domain.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.example.domain.dto.UserDto;
import org.example.persistence.entities.UserEntity;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "mySecretKey"; // Clave secreta para firmar el token
    private static final long EXPIRATION_TIME = 864_000_000; // Tiempo de expiración: 10 días en milisegundos

    public static String generateToken(UserEntity user) {

        // Crear el token
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return token;
    }

    public static boolean validateToken(String token) {
        try {
            // Verificar el token
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            // El token no es válido
            return false;
        }
    }

    public static Claims getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
