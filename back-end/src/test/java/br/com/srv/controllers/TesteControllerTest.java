package br.com.srv.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class TesteControllerTest {
	
	@InjectMocks
	TesteController controller;
	
	@Test
	public void testRequisicaoOk() {
		ResponseEntity<String> response = controller.teste();
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
