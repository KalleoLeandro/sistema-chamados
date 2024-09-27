package br.com.srv.models.responses;

import java.math.BigDecimal;

import br.com.srv.entities.ProdutoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter 
@Setter
public class ProdutoResponse {
	
	private Long id;
	
	private String nome;
	
	private BigDecimal preco_compra;
	
	private BigDecimal preco_venda;
	
	private Integer quantidade;
	
	private Long categoria;
	
	private Long medida;
	
	public ProdutoResponse(ProdutoEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.preco_compra = entity.getPreco_compra();
		this.preco_venda = entity.getPreco_venda();
		this.quantidade = entity.getQuantidade();
		this.categoria = entity.getCategoria().getId();
		this.medida = entity.getMedida().getId();
	}
}
