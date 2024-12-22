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

import br.com.srv.models.requests.ProdutoRequest;
import br.com.srv.models.responses.CategoriaResponse;
import br.com.srv.models.responses.MedidaResponse;
import br.com.srv.models.responses.ProdutoResponse;
import br.com.srv.services.ProdutoService;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoControllerTest {

	@InjectMocks
	private ProdutoController controller;
	
	@Mock
	private ProdutoService service;
	
	@Test
	public void testGravarProdutoOk() {
		Mockito.doNothing().when(service).gravarProduto(any());
		
		ResponseEntity<Void> respose = controller.gravarProduto(new ProdutoRequest());
	    Mockito.verify(service, Mockito.times(1)).gravarProduto(new ProdutoRequest());

		Assertions.assertEquals(respose.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test
	public void testListarProdutosOK() {
		List<ProdutoResponse> lista = new ArrayList<ProdutoResponse>();
		lista.add(new ProdutoResponse());
		lista.add(new ProdutoResponse());
		Mockito.when(service.listarProdutos()).thenReturn(lista);
		ResponseEntity<List<ProdutoResponse>> response = controller.listarProdutos();
		
	    Mockito.verify(service, Mockito.times(1)).listarProdutos();
	    
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testBuscarProdutoPorIdOk() {
		Mockito.when(service.buscarProdutoPorId(anyLong())).thenReturn(new ProdutoResponse());
		ResponseEntity<ProdutoResponse> response = controller.buscarProdutoPorId(1L);
		
		Mockito.verify(service, Mockito.times(1)).buscarProdutoPorId(anyLong());
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testExcluirProdutoPorId() {
		Mockito.doNothing().when(service).excluirProdutoPorId(any());
		
		ResponseEntity<Void> response = controller.excluirProdutoPorId(1L);
		
		Mockito.verify(service, Mockito.times(1)).excluirProdutoPorId(1L);
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
	}
	
	@Test
	public void testListaCategoriasOk() {
		List<CategoriaResponse> lista = new ArrayList<CategoriaResponse>();
		lista.add(new CategoriaResponse());
		lista.add(new CategoriaResponse());
		Mockito.when(service.listarCategorias()).thenReturn(lista);
		
		ResponseEntity<List<CategoriaResponse>> response = controller.listarCategorias();
		Mockito.verify(service, Mockito.times(1)).listarCategorias();
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testListaMedidasOk() {
		List<MedidaResponse> lista = new ArrayList<MedidaResponse>();
		lista.add(new MedidaResponse());
		lista.add(new MedidaResponse());
		Mockito.when(service.listarMedidas()).thenReturn(lista);
		
		ResponseEntity<List<MedidaResponse>> response = controller.listarMedidas();
		Mockito.verify(service, Mockito.times(1)).listarMedidas();
	    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	
}
