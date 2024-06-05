package es.metrica.PreguntaTech.utils.jwt;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import es.metrica.PreguntaTech.model.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Jwt {
	
	//@Value("${application.security.jwt.secret}")
	private String KEY = "12fthuijo3xfetryuljhfdsewrtikjhgfdswe4r5ty67uikjhfdsew4";
	
	//@Value("${application.security.jwt.hoursTillExp}")
	private long EXPIRATION_HOURS = 12;
	/*
	 * @param user User with at least name and Id
	 * @param claims Map<String, Object> with a key-value containing the (at least) the password.
	 * @return Token
	 */
	@SuppressWarnings("deprecation")
	public String generateToken(User user, Map<String, Object> claims) {
		Date issuedDate = new Date(System.currentTimeMillis());
		Date expiration = new Date(issuedDate.getTime() + (EXPIRATION_HOURS *3600000));
		return Jwts.builder()
						.setClaims(claims)
						.setSubject(user.getName())
						.setId(user.getId()+"")
						.setIssuedAt(issuedDate)
						.setExpiration(expiration)
						.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
						.signWith(SignatureAlgorithm.HS256, KEY)
						.compact();
	}
	/*
	 * @param jwt complete token
	 * @return ID from user in the token
	 */
	public long getUser(String jwt) {

		@SuppressWarnings("deprecation")
		String id = Jwts.parser()
						.setSigningKey(KEY)
						.parseClaimsJws(jwt)
						.getBody()
						.getId();
		return Long.parseLong(id);
  
  }
}
