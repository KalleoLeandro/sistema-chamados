package br.com.srv.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.srv.entities.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long>{
	
	Optional<LoginEntity> findByLoginAndSenha(String login, String senha);


}
