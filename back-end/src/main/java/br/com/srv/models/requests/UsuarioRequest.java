package br.com.srv.models.requests;

import jakarta.validation.constraints.NotBlank;
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
public class UsuarioRequest {
	
	private Long id;
	
	@NotBlank(message = "O nome não pode estar vazio")
	private String nome;
	
	@NotBlank(message = "O cpf não pode estar vazio")
	private String cpf;
	
	@NotBlank(message = "A data_nascimento não pode estar vazia")
	private String dataNascimento;
	
	@NotNull(message = "O sexo não pode estar vazio")
	private char sexo;	
	
	@NotBlank(message = "O cep não pode estar vazio")
	private String cep;
	
	@NotBlank(message = "A rua não pode estar vazia")
	private String rua;
	
	@NotNull(message = "O numero não pode estar vazio")
	private int numero;
		
	private String complemento;
	
	@NotBlank(message = "O bairro não pode estar vazio")
	private String bairro;

	@NotBlank(message = "A cidade não pode estar vazia")
	private String cidade;
	
	@NotBlank(message = "A uf não pode estar vazia")
	private String uf;	
	
	@NotBlank(message = "O telefone não pode estar vazio")
	private String telefone;
	
	@NotBlank(message = "O celular não pode estar vazio")
	private String celular;
		
	@NotBlank(message = "O email não pode estar vazio")
	private String email;
		
	@NotBlank(message = "O login não pode estar vazio")
	private String login;
		
	@NotBlank(message = "A senha não pode estar vazio")
	private String senha;
		
	@NotBlank(message = "O perfil não pode estar vazio")
	private String perfil;
}
