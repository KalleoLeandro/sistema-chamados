package br.com.srv.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class UsuarioEntity {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "cpf")
	private String cpf;

	@Column(name = "data_nascimento")
	private String data_nascimento;

	@Column(name = "sexo")
	private char sexo;

	// Relacionamento OneToOne com LoginEntity
    @OneToOne(optional = false) // optional = false garante que não pode ser nulo
    @JoinColumn(name = "id_dados_login", referencedColumnName = "id", nullable = false)
    private LoginEntity loginEntity;

    // Relacionamento OneToOne com ContatoEntity
    @OneToOne(optional = false)
    @JoinColumn(name = "id_contato", referencedColumnName = "id", nullable = false)
    private ContatoEntity contatoEntity;

    // Relacionamento OneToOne com EnderecoEntity
    @OneToOne(optional = false)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false)
    private EnderecoEntity enderecoEntity;

}
