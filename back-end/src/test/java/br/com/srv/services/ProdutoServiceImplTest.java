package br.com.srv.services;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.srv.entities.CategoriaEntity;
import br.com.srv.entities.MedidaEntity;
import br.com.srv.entities.ProdutoEntity;
import br.com.srv.models.requests.ProdutoRequest;
import br.com.srv.repositories.CategoriaRepository;
import br.com.srv.repositories.MedidaRepository;
import br.com.srv.repositories.ProdutoRepository;
import br.com.srv.services.impl.ProdutoServiceImpl;

@SpringBootTest
public class ProdutoServiceImplTest {
	
	@InjectMocks
	private ProdutoServiceImpl service;
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private MedidaRepository medidaRepository;
	
	@Mock
	private CategoriaRepository categoriaRepository;
	
	ProdutoRequest request;
	
	@BeforeEach
	public void setUp() {
		
		request = new ProdutoRequest();
		request.setNome("Teste");
		request.setPreco_compra(new BigDecimal(1.0));
		request.setPreco_venda(new BigDecimal(2.0));
		request.setQuantidade(1);
		request.setMedida(1L);
		request.setCategoria(1L);
	}

	@Test
	public void gravarProdutoOk() {
		Mockito.when(produtoRepository.save(any())).thenReturn(new ProdutoEntity());
		
		service.gravarProduto(request);
		
		Mockito.verify(produtoRepository, Mockito.times(1)).save(any(ProdutoEntity.class));
	}
}
