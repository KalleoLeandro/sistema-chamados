package br.com.srv.models.responses;

import br.com.srv.entities.UsuarioEntity;
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
public class UsuarioResponse {

	private Long id;
		
	private String nome;
		
	private String cpf;
		
	private String data_nascimento;
		
	private char sexo;	
		
	private String cep;
		
	private String rua;
		
	private int numero;
		
	private String complemento;
		
	private String bairro;
	
	private String cidade;

	private String uf;	

	private String telefone;

	private String celular;
		
	private String email;
		
	private String perfil;
	
	public UsuarioResponse(UsuarioEntity usuarioEntity) {
		this.id = usuarioEntity.getId();
        this.nome = usuarioEntity.getNome();
        this.cpf = usuarioEntity.getCpf();
        this.data_nascimento = usuarioEntity.getData_nascimento();
        this.sexo = usuarioEntity.getSexo();
        this.cep = usuarioEntity.getEnderecoEntity().getCep();
        this.rua = usuarioEntity.getEnderecoEntity().getRua();
        this.numero = usuarioEntity.getEnderecoEntity().getNumero();
        this.complemento = usuarioEntity.getEnderecoEntity().getComplemento();
        this.bairro = usuarioEntity.getEnderecoEntity().getBairro();
        this.cidade = usuarioEntity.getEnderecoEntity().getCidade();
        this.uf = usuarioEntity.getEnderecoEntity().getUf();
        this.telefone = usuarioEntity.getContatoEntity().getTelefone();
        this.celular = usuarioEntity.getContatoEntity().getCelular();
        this.email = usuarioEntity.getContatoEntity().getEmail();        
        this.perfil = usuarioEntity.getLoginEntity().getPerfil();
	}
}
