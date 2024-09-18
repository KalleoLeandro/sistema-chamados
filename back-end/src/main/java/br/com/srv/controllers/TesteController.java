package br.com.srv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	private static final Logger logger = LoggerFactory.getLogger(TesteController.class);
	
	@GetMapping
	public ResponseEntity<String> teste(){
		logger.info("Executando teste de request");
		return ResponseEntity.ok("Teste Request OK");
	}

}
