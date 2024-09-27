package br.com.srv.models.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class ProdutoRequest {
	
	private Long id;
	
	@NotEmpty(message = "O nome não pode estar vazio")
	private String nome;
	
	@NotNull(message = "O preçoo de compra não pode estar vazio")
	private BigDecimal preco_compra;
	
	@NotNull(message = "O preço de venda não pode estar vazio")
	private BigDecimal preco_venda;
		
	@NotNull(message = "A quantidade não pode estar vazia")
	private Integer quantidade;
	
	@NotNull(message = "A categoria não pode estar vazia")
	private Long categoria;
	
	@NotNull(message = "A medida não pode estar vazia")
	private Long medida;
}
