package br.com.srv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.srv.entities.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Long>{
	
	Optional<LoginEntity> findByLoginAndSenha(String login, String senha);


}
