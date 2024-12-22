package br.com.srv.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import br.com.srv.entities.LoginEntity;
import br.com.srv.entities.UsuarioEntity;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.models.requests.LoginRequest;
import br.com.srv.models.responses.LoginResponse;
import br.com.srv.repositories.LoginRepository;
import br.com.srv.repositories.UsuarioRepository;
import br.com.srv.services.impl.LoginServiceImpl;
import br.com.srv.utils.JwtUtils;

@SpringBootTest
@TestPropertySource(properties = {
	    "jwt.token.validity=10000",
	    "secret.jwt.secret=myTestSecretKey"
	})
@ActiveProfiles("test")
public class LoginServiceImplTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private LoginServiceImpl loginService;

    private LoginRequest loginRequest;
    private LoginEntity loginEntity;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        
        loginRequest = new LoginRequest();
        loginRequest.setLogin("user");
        loginRequest.setSenha("password");

        
        loginEntity = new LoginEntity();
        loginEntity.setId(1L);
        loginEntity.setLogin("user");
        loginEntity.setSenha("password");

        
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome("Usuário Teste");
    }

    @Test
    public void testValidarLogin_Sucesso() throws Exception {
        
        Mockito.when(loginRepository.findByLoginAndSenha(anyString(), anyString())).thenReturn(Optional.of(loginEntity));
        Mockito.when(usuarioRepository.findByLoginEntityId(1L)).thenReturn(Optional.of(usuarioEntity));
        Mockito.when(jwtUtils.generateToken(anyString(), anyString())).thenReturn("mockToken");
        Mockito.when(jwtUtils.getExpirationDateFromToken(any())).thenReturn(new Date());

        
        LoginResponse response = loginService.validarLogin(loginRequest);

        
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Usuário Teste", response.getUserName());
        Assertions.assertEquals("mockToken", response.getToken());
    }

    @Test
    public void testValidarLogin_Falha_LoginNaoEncontrado() {
        
    	Mockito.when(loginRepository.findByLoginAndSenha(anyString(), anyString())).thenReturn(Optional.empty());

        
        LoginResponse response = loginService.validarLogin(loginRequest);

        
        Assertions.assertEquals(401, response.getStatus());
    }

    @Test
    public void testValidarLogin_Falha_UsuarioNaoEncontrado() {
        
    	Mockito.when(loginRepository.findByLoginAndSenha(anyString(), anyString())).thenReturn(Optional.of(loginEntity));
    	Mockito.when(usuarioRepository.findByLoginEntityId(1L)).thenReturn(Optional.empty());
        
        LoginResponse response = loginService.validarLogin(loginRequest);
        
        Assertions.assertEquals(401, response.getStatus());
    }
    
    @Test
    public void testValidarLoginErro() {
        
    	Mockito.when(loginRepository.findByLoginAndSenha(anyString(), anyString())).thenThrow(new DefaultErrorException("Erro", HttpStatus.INTERNAL_SERVER_ERROR));    	
        
    	DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            loginService.validarLogin(loginRequest);
        });
        
        Assertions.assertTrue(exception.getMessage().contains("Erro"));
    }
    
    @Test
    public void testValidarTokenOk() {
    	
    	Mockito.when(jwtUtils.validateToken(anyString())).thenReturn(true);
    	
        Boolean response = loginService.validarToken("token");
        
        Assertions.assertTrue(response);
    }
    
    @Test
    public void testValidarTokenFalse() {
    	
    	Mockito.when(jwtUtils.validateToken(anyString())).thenReturn(false);
    	
        Boolean response = loginService.validarToken("token");
        
        Assertions.assertFalse(response);
    }
    
    @Test
    public void testValidarTokenFalseWithEmptyToken() {
    	
    	Mockito.when(jwtUtils.validateToken(anyString())).thenReturn(false);
    	
        Boolean response = loginService.validarToken("");
        
        Assertions.assertFalse(response);
    }
    
    @Test
    public void testValidarTokenErro() {
    	
    	Mockito.when(jwtUtils.validateToken(anyString())).thenThrow(new DefaultErrorException("Erro", HttpStatus.INTERNAL_SERVER_ERROR));
    	
    	DefaultErrorException exception = Assertions.assertThrows(DefaultErrorException.class, () -> {
            loginService.validarToken("token");
        });
        
    	Assertions.assertTrue(exception.getMessage().contains("Erro"));
    }
}
