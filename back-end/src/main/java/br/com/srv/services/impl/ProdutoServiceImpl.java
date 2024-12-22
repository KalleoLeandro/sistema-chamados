package br.com.srv.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.srv.entities.CategoriaEntity;
import br.com.srv.entities.MedidaEntity;
import br.com.srv.entities.ProdutoEntity;
import br.com.srv.exceptions.DefaultErrorException;
import br.com.srv.models.requests.ProdutoRequest;
import br.com.srv.models.responses.CategoriaResponse;
import br.com.srv.models.responses.MedidaResponse;
import br.com.srv.models.responses.ProdutoResponse;
import br.com.srv.repositories.CategoriaRepository;
import br.com.srv.repositories.MedidaRepository;
import br.com.srv.repositories.ProdutoRepository;
import br.com.srv.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private MedidaRepository medidaRepository;

	@Override
	public void gravarProduto(ProdutoRequest produtoRequest) {
		try {
			ProdutoEntity entity = getProduto(produtoRequest);
			produtoRepository.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefaultErrorException("Erro na execução da gravação/atualização do produto: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private ProdutoEntity getProduto(ProdutoRequest produtoRequest) {
		ProdutoEntity produtoEntity = new ProdutoEntity(produtoRequest);

		CategoriaEntity categoriaEntity = findCategoriaById(produtoRequest.getCategoria());
		MedidaEntity medidaEntity = findMedidaById(produtoRequest.getMedida());
		produtoEntity.setCategoria(categoriaEntity);
		produtoEntity.setMedida(medidaEntity);
		return produtoEntity;
	}

	private CategoriaEntity findCategoriaById(Long id) {
		try {
			Optional<CategoriaEntity> cOptional = categoriaRepository.findById(1L);			
			if (cOptional.isPresent()) {
				CategoriaEntity categoria = cOptional.get();
				return categoria;
			} else {
				throw new DefaultErrorException("Erro na recuperação dos dados da categoria",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na recuperação dos dados da categoria: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private MedidaEntity findMedidaById(Long id) {
		try {
			Optional<MedidaEntity> mOptional = medidaRepository.findById(id);
			if (mOptional.isPresent()) {
				MedidaEntity medida = mOptional.get();
				return medida;
			} else {
				throw new DefaultErrorException("Erro na recuperação dos dados da medida",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na recuperação dos dados da medida: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<ProdutoResponse> listarProdutos() {
		try {
			List<ProdutoEntity> lista = produtoRepository.findAll();
			List<ProdutoResponse> response = new ArrayList<ProdutoResponse>();
			lista.forEach(produto -> response.add(new ProdutoResponse(produto)));
			return response;
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na recuperação da lista de produtos: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ProdutoResponse buscarProdutoPorId(Long id) {
		try{
			Optional<ProdutoEntity> pOptional = produtoRepository.findById(id);
			if(pOptional.isPresent()) {
				ProdutoResponse response = new ProdutoResponse(pOptional.get());
				return response;
			} else {
				throw new DefaultErrorException("Erro na recuperação dos dados do produto",
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch (Exception e) {
			throw new DefaultErrorException("Erro na recuperação dos dados do produto: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public void excluirProdutoPorId(Long id) {
		try {
			Optional<ProdutoEntity> product = produtoRepository.findById(id);
			if(product.isPresent()) {
				ProdutoEntity entity = product.get();
				produtoRepository.delete(entity);
			}
		} catch (Exception e) {
			throw new DefaultErrorException("Erro na exclusão do produto: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public List<CategoriaResponse> listarCategorias() {
		try {
		List<CategoriaEntity> lista = categoriaRepository.findAll();
		List<CategoriaResponse> response = new ArrayList<CategoriaResponse>();
		lista.forEach(categoria -> response.add(new CategoriaResponse(categoria)));
		return response;
		}catch (Exception e) {
			throw new DefaultErrorException("Erro na recuperação da lista de categorias: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MedidaResponse> listarMedidas() {
		try {
			List<MedidaEntity> lista = medidaRepository.findAll();
			List<MedidaResponse> response = new ArrayList<MedidaResponse>();
			lista.forEach(medida -> response.add(new MedidaResponse(medida)));
			return response;
		}catch (Exception e) {
			throw new DefaultErrorException("Erro na recuperação da lista de medidas: " + e,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
