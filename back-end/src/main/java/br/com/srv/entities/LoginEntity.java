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
@Table(name = "dados_login")
public class LoginEntity {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "senha")
	private String senha;
	
	@Column(name = "perfil")
	private String perfil;
	
	@OneToOne(mappedBy = "loginEntity")
	@JsonBackReference // Evita a serialização recursiva
	private UsuarioEntity usuario;
}
