package br.com.srv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srv.models.requests.LoginRequest;
import br.com.srv.models.requests.TokenRequest;
import br.com.srv.models.responses.LoginResponse;
import br.com.srv.services.LoginService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
	
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;
	
	@PostMapping("/validar-login")
	public ResponseEntity<LoginResponse> validarLogin(@RequestBody @Valid LoginRequest login){
		logger.info("Executando a LoginService/validarLogin");		
		LoginResponse response = loginService.validarLogin(login);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
	
	@PostMapping("/validar-token")
	public ResponseEntity<Boolean> validarToken(@RequestBody @Valid TokenRequest tokenRequest){		
		logger.info("Executando a LoginService/validarToken");		
		return ResponseEntity.ok(loginService.validarToken(tokenRequest));
		
	}
}
