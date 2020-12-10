package com.bitboxlab.wallet.utils;

import com.bitboxlab.wallet.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;


/**
 * Utilities which help to create and parse existing authentication tokens of the user
 */
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public JwtUtil() {
    }

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User u = new User();
            u.setEmail(body.getSubject());
            u.setId(Long.parseLong((String) body.get("userId")));

            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

}
