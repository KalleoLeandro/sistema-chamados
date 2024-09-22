package br.com.srv.utils;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.srv.entities.LoginEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;
	public static final long JWT_TOKEN_VALIDITY = 1200000;

	@Value("${secret.jwt.secret}")
	private String secret;

	// retorna o username do token jwt
	public String getUsernameFromToken(String token) throws Exception {
	    // Obtém o claim "username" do token JWT
	    String username = getClaimFromToken(token, claims -> claims.get("username", String.class));
	    return username;
	}

	// retorna o login do token jwt
	public String getLoginFromToken(String token) throws Exception {
	    // Obtém o claim "login" do token JWT
	    String login = getClaimFromToken(token, claims -> claims.get("login", String.class));
	    return login;
	}

	// retorna expiration date do token jwt
	public Date getExpirationDateFromToken(String token) throws Exception {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws Exception {
		try {
			final Claims claims = getAllClaimsFromToken(token);
			return claimsResolver.apply(claims);
		} catch (Exception e) {			
			throw new Exception("Erro!", e);
		}

	}

	// para retornar qualquer informação do token nos iremos precisar da secret key	
	@SuppressWarnings("deprecation")
	private Claims getAllClaimsFromToken(String token) throws Exception {
		try {
			return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
		}catch (Exception e) {
			throw new Exception();
		}
	}

	// check if the token has expired
	public Boolean isTokenExpired(String token) throws Exception {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// gera token para user
	public String generateToken(LoginEntity loginEntity) {
	    Map<String, Object> claims = new HashMap<>();
	    claims.put("username", loginEntity.getNome());
	    claims.put("login", loginEntity.getLogin());	    
	    return doGenerateToken(claims, loginEntity.getNome());
	}

	// Cria o token e devine tempo de expiração pra ele
	@SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
				.signWith(SignatureAlgorithm.HS512, getSecretKey()) // Use a chave do application.yml
				.compact();
	}

	// valida o token
	/*public Boolean validateToken(String token) throws Exception {
	    try {
	        // Tenta obter o username do token, apenas para verificar a integridade
	        getUsernameFromToken(token);
	        // Verifica se o token está expirado
	        return !isTokenExpired(token);
	    } catch (Exception e) {
	        // Em caso de qualquer exceção, considera o token inválido
	        return false;
	    }
	}*/
	
	public Boolean validateToken(String token) {
	    try {
	        // Cria um JwtParser utilizando a nova API
	        JwtParser parser = Jwts.parserBuilder()
	            .setSigningKey(getSecretKey()) // Define a chave de assinatura
	            .build();
	        
	        // Faz a verificação do token
	        @SuppressWarnings("unused")
			Claims claims = parser.parseClaimsJws(token).getBody();
	        	        
	        // O token é válido
	        return true;
	    } catch (JwtException | IllegalArgumentException e) {
	        // Qualquer problema com o token, ele é considerado inválido
	        return false;
	    }
	}		

	// Método para obter a chave secreta como uma instância de Key
	public Key getSecretKey() {
		// Aqui você pode fazer qualquer manipulação necessária para garantir que a
		// chave tenha o tamanho adequado
		byte[] keyBytes = secret.getBytes();
		// Se a chave for muito pequena, você pode considerar fazer um hash dela ou
		// usá-la como seed para gerar uma chave mais segura
		// Neste exemplo, estamos apenas cortando os bytes extras, o que não é ideal
		// para segurança real
		byte[] truncatedKeyBytes = new byte[64]; // 512 bits
		System.arraycopy(keyBytes, 0, truncatedKeyBytes, 0, Math.min(keyBytes.length, truncatedKeyBytes.length));
		return Keys.hmacShaKeyFor(truncatedKeyBytes);
	}
}
