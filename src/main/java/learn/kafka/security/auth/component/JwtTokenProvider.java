package learn.kafka.security.auth.component;

import io.jsonwebtoken.Jwts;
import learn.kafka.security.auth.model.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;


@Component
public class JwtTokenProvider {
    private final static SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(User user) {
        return Jwts.builder().subject("Joe").signWith(key).compact();
    }

    public String getNameFromToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }
}
