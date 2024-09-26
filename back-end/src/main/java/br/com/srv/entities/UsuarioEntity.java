package br.com.srv.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false) // optional = false garante que não pode ser nulo
    @JoinColumn(name = "id_dados_login", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private LoginEntity loginEntity;

    // Relacionamento OneToOne com ContatoEntity
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "id_contato", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private ContatoEntity contatoEntity;

    // Relacionamento OneToOne com EnderecoEntity
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private EnderecoEntity enderecoEntity;

}
