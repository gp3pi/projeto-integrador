package com.projeto_integrador_03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.projeto_integrador_03.model.Postagens;

public interface PostagensRepository extends JpaRepository<Postagens, Long> {
	
	public List <Postagens>  findAllByTituloContainingIgnoreCase(@Param("Titulo	") String titulo);
	
	public List <Postagens> findAllByTextoContainingIgnoreCase(@Param("Texto") String texto);
	
	
	
}
