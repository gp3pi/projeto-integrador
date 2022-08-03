package com.projeto_integrador_03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projeto_integrador_03.model.Temas;

@Repository
public interface TemasRepository extends JpaRepository<Temas, Long> {

	public List <Temas> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
	
	public List <Temas> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
	
}
