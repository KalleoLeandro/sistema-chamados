package br.com.srv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srv.entities.requests.LoginRequest;
import br.com.srv.models.responses.LoginResponse;
import br.com.srv.services.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private LoginService loginService;
	
	@PostMapping
	@RequestMapping("/validar-login")
	public ResponseEntity<LoginResponse> validarLogin(@RequestBody LoginRequest login){
		logger.info("Executando a LoginService/ValidarLogin");		
		logger.info(login.getLogin());
		LoginResponse response = loginService.ValidarLogin(login);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
