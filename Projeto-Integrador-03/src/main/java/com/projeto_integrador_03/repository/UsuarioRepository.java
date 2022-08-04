package com.projeto_integrador_03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto_integrador_03.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
