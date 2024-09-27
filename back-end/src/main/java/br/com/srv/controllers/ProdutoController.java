package br.com.srv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srv.models.requests.ProdutoRequest;
import br.com.srv.models.responses.ProdutoResponse;
import br.com.srv.services.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@PostMapping("/gravar-produto")
	public ResponseEntity<Void> gravarProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
		service.gravarProduto(produtoRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/listar-produtos")
	public ResponseEntity<List<ProdutoResponse>> listarProdutos(){
		return ResponseEntity.ok(service.listarProdutos());
	}
	
	@GetMapping("/buscar-produto-por-id/{id}")
	public ResponseEntity<ProdutoResponse> buscarProdutoPorId(@PathVariable Long id){
		return ResponseEntity.ok(service.buscarProdutoPorId(id));
	}
	
	@DeleteMapping("/excluir-produto-por-id/{id}")
	public ResponseEntity<Void> excluirProdutoPorId(@PathVariable Long id){
		service.excluirProdutoPorId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}	

}
