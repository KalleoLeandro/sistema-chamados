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
@Table(name = "contato")
public class ContatoEntity {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne(mappedBy = "contatoEntity")
	@JsonBackReference // Evita a serialização recursiva
	private UsuarioEntity usuario;
	
}
