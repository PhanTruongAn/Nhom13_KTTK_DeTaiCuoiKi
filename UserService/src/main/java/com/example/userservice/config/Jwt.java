package com.example.userservice.config;
import com.example.userservice.models.User;
import io.jsonwebtoken.Claims;
import java.util.logging.Logger;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class Jwt{
    private static final Logger LOGGER = Logger.getLogger(Jwt.class.getName());
    private static final String USER = "user";
    private static final String SECRET = "0388123402";

    public static final long EXPIRATION_TIME = 30L * 24 * 60 * 60 * 1000;

    public static String generateToken(User user) {
        String token = "";
        try {
            token = Jwts.builder()
                    .setSubject("user")
                    .claim("user", user.getUserName())
                    .setIssuedAt(new java.util.Date(System.currentTimeMillis()))
                    .setExpiration(new java.util.Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return token;
    }

    private static Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static User getUserFromToken(String token) {
        User userPrincial = null;
        try {
            Claims claims = getClaims(token);
            userPrincial = (User) claims.get(USER);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return userPrincial;
    }

    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaims(token);
            System.out.println(claims.getExpiration());
            return claims.getExpiration().before(new java.util.Date());
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return true;
    }
}

