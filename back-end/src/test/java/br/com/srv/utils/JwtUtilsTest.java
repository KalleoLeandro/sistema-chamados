package br.com.srv.utils;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
	    "jwt.token.validity=1000",
	    "secret.jwt.secret=myTestSecretKey"
	})
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @BeforeEach
    public void setUp() {
                 
    }

    @Test
    public void testGenerateToken() {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);

        Assertions.assertNotNull(token);
        Assertions.assertFalse(token.isEmpty());
    }

    @Test
    public void testGetUsernameFromToken() throws Exception {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);

        String extractedUsername = jwtUtils.getUsernameFromToken(token);

        Assertions.assertEquals(username, extractedUsername);
    }

    @Test
    public void testGetLoginFromToken() throws Exception {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);

        String extractedLogin = jwtUtils.getLoginFromToken(token);

        Assertions.assertEquals(login, extractedLogin);
    }

    @Test
    public void testGetExpirationDateFromToken() throws Exception {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);

        Date expirationDate = jwtUtils.getExpirationDateFromToken(token);

        Assertions.assertNotNull(expirationDate);
        Assertions.assertTrue(expirationDate.after(new Date()));
    }

    @Test
    public void testIsTokenExpired() throws Exception {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);        
                
        Boolean isExpired = jwtUtils.isTokenExpired(token);

        Assertions.assertTrue(!isExpired);
    }
    
    @Test
    public void testIsTokenExpiredErro() throws Exception {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);        

        // Simular a expiração do token
        Thread.sleep(2000); // Aguardar a expiração do token       

        Exception exception = Assertions.assertThrows(Exception.class, () -> {
        	 jwtUtils.isTokenExpired(token);
        });
        
        Assertions.assertTrue(exception.getMessage().contains("Erro"));
    }

    @Test
    public void testValidateToken() {
        String username = "testUser";
        String login = "testLogin";
        String token = jwtUtils.generateToken(username, login);

        Boolean isValid = jwtUtils.validateToken(token);
        assertTrue(isValid);

        // Testando um token inválido
        String invalidToken = "invalidToken";
        Boolean isInvalid = jwtUtils.validateToken(invalidToken);
        assertFalse(isInvalid);
    }
}
