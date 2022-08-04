package com.projeto_integrador_03.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_temas")
public class Temas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo título é obrigatório.")
	@Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
	private String titulo;
	
	@NotBlank(message = "O atributo descrição é obrigatório.")
	@Size(min = 10, max = 1000, message = "O atributo descrição deve ter entre 10 e 1000 caracteres")
	private String descricao;
	
	@OneToMany(mappedBy = "Temas", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("Temas")
	private List <Postagens> postagens;
	
	
	public List<Postagens> getPostagens() {
		return postagens;
	}

	public void setPostagens(List<Postagens> postagens) {
		this.postagens = postagens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
