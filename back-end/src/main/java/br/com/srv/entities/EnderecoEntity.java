package br.com.srv.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class EnderecoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cep")
	private String cep;

	@Column(name = "rua")
	private String rua;

	@Column(name = "numero")
	private int numero;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "bairro")
	private String bairro;

	@Column(name = "cidade")
	private String cidade;

	@Column(name = "uf")
	private String uf;

	@OneToOne(mappedBy = "enderecoEntity")
	@JsonBackReference // Evita a serialização recursiva
	private UsuarioEntity usuario;

}
