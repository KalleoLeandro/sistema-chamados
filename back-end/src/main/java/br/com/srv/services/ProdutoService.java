package br.com.srv.services;

import java.util.List;

import br.com.srv.models.requests.ProdutoRequest;
import br.com.srv.models.responses.CategoriaResponse;
import br.com.srv.models.responses.MedidaResponse;
import br.com.srv.models.responses.ProdutoResponse;

public interface ProdutoService {
	
	public void gravarProduto(ProdutoRequest produtoRequest);
	
	public List<ProdutoResponse> listarProdutos();
	
	public ProdutoResponse buscarProdutoPorId(Long id);
	
	public void excluirProdutoPorId(Long id);
	
	public List<CategoriaResponse> listarCategorias();
	
	public List<MedidaResponse> listarMedidas();

}
