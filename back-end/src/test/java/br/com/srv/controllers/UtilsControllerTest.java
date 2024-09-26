package br.com.srv.controllers;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.srv.services.impl.UtilsServiceImpl;

@SpringBootTest
public class UtilsControllerTest {
	
	@InjectMocks
	UtilsController controller;
	
	@Mock
	UtilsServiceImpl service;
	
	@Test
	public void testRequisicaoOk() {
		
		Mockito.when(service.validarCpf(anyString())).thenReturn(true);
		
		ResponseEntity<Boolean> response = controller.validarCpf("22233344405");
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertEquals(true, response.getBody());
	}

}
