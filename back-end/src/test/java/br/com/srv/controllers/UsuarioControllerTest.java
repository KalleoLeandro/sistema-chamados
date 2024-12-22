package br.com.srv.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.srv.models.requests.UsuarioRequest;
import br.com.srv.models.responses.UsuarioResponse;
import br.com.srv.services.impl.UsuarioServiceImpl;

@SpringBootTest
@ActiveProfiles("test")
public class UsuarioControllerTest {
	
	@InjectMocks
	private UsuarioController controller;
	
	@Mock
	private UsuarioServiceImpl service;
	
	@Test
	public void testGravarUsuarioOk() {
		Mockito.doNothing().when(service).gravarUsuario(any());
		
		ResponseEntity<Void> response = controller.gravarUsuario(new UsuarioRequest());
		
	    Mockito.verify(service, Mockito.times(1)).gravarUsuario(new UsuarioRequest());
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);	
	}	
	
	@Test
	public void testListarUsuarioOk() {
		List<UsuarioResponse> lista = new ArrayList<UsuarioResponse>();
		lista.add(new UsuarioResponse());
		lista.add(new UsuarioResponse());
		Mockito.when(service.listarUsuarios()).thenReturn(lista);
		
		ResponseEntity<List<UsuarioResponse>> response = controller.listarUsuarios();
		
	    Mockito.verify(service, Mockito.times(1)).listarUsuarios();
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);	
	}
	
	@Test
	public void testBuscarUsuarioPorIdOk() {
		Mockito.when(service.buscarUsuarioPorId(anyLong())).thenReturn(new UsuarioResponse());
		ResponseEntity<UsuarioResponse> response = controller.buscarUsuarioPorId(1L);
		
		Mockito.verify(service, Mockito.times(1)).buscarUsuarioPorId(anyLong());
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testExcluirUsuarioPorIdOK() {
		Mockito.doNothing().when(service).excluirUsuarioPorId(anyLong());
				
		ResponseEntity<Void> response = controller.excluirUsuarioPorId(1L);
		
		Mockito.verify(service, Mockito.times(1)).excluirUsuarioPorId(1L);
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
}
