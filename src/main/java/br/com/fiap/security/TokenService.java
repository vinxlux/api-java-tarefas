package br.com.fiap.security;

import br.com.fiap.models.Usuario;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class TokenService {
    public String generateToken(Usuario usuario) {
        Set<String> roles = new HashSet<>();
        roles.add("USER");

        long duration = Duration.ofHours(24).toSeconds();

        return Jwt.issuer("token-app")
                .subject(usuario.getLogin())
                .groups(roles)
                .expiresIn(duration)
                .sign();
    }
}
