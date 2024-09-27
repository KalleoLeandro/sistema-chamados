package br.com.srv.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.srv.entities.MedidaEntity;

@Repository
public interface MedidaRepository extends JpaRepository<MedidaEntity, Long>{

}
