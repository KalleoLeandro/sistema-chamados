package br.com.srv.models.responses;

import br.com.srv.entities.CategoriaEntity;
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
public class CategoriaResponse {

	private Long id;	
	private String descricao;
	
	public CategoriaResponse(CategoriaEntity entity) {
		this.id = entity.getId();
		this.descricao = entity.getDescricao();
	}
}
