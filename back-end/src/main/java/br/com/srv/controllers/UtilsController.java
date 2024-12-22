package br.com.srv.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srv.services.UtilsService;


@RestController
@RequestMapping("/utils")
public class UtilsController {
	
	private static final Logger logger = LoggerFactory.getLogger(UtilsController.class);
	
	@Autowired
	private UtilsService utilsService;

	
	@PostMapping("/validar-cpf")
	public ResponseEntity<Boolean> validarCpf(@RequestBody String cpf){
		logger.info("Executando a service validarCpf");		
		return ResponseEntity.ok(utilsService.validarCpf(cpf));
	}	
}
