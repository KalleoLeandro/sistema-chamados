package br.com.srv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.srv.entities.ContatoEntity;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {

}
