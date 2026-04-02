package br.com.gustavo.espacoKids.infra.config.security;

import br.com.gustavo.espacoKids.domain.entity.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JwtService {

    @Value("${api.security.token.secret}")
    private String secret;

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(secret);
    }

    public String gerarToken(UserDetails userDetails) {
        Usuario usuario = (Usuario) userDetails;
        var builder = JWT.create()
                .withSubject(usuario.getUsername())
                .withClaim("perfil", usuario.getPerfil().name())
                .withClaim("userId", usuario.getId())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS));
        if (usuario.getResponsavel() != null) {
            builder.withClaim("responsavelId", usuario.getResponsavel().getId());
        }
        return builder.sign(getAlgorithm());
    }

    public String extrairLogin(String token){
        return JWT.require(getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
    }

}
