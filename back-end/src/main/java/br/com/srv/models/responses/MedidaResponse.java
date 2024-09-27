package br.com.srv.models.responses;

import br.com.srv.entities.MedidaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedidaResponse {
	
	private Long id;
	
	private String sigla;
	
	private String descricao;
	
	public MedidaResponse(MedidaEntity entity) {
		this.id = entity.getId();
		this.sigla = entity.getSigla();
		this.descricao = entity.getDescricao();
		
	}

}
