package br.com.cotiinformatica.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {
	@Value("${jwt.secretkey}")
	private String secretKey;
	@Value("${jwt.expiration}")
	private String expiration;

	public Date getExpirationDate() {
		Date dataAtual = new Date();
		return new Date(dataAtual.getTime() + expiration);
	}

	public String generateToken(Usuario usuario) {
		return Jwts.builder()
				.setSubject(usuario.getEmail())
				.setNotBefore(getExpirationDate())
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}

}
