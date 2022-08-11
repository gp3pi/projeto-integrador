package com.projeto_integrador_03.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nome é uma informação obrigatória.")
    private String nome;

    @NotNull(message = "Usuário é uma informação obrigatório!")
    @Email(message = "Usuário deve ser um email válido!")
    private String usuario;

    @NotBlank(message = "Senha é uma informação obrigatória.")
    @Size(min = 3, max =255)
    private String senha;

    @Size(max =5000 , message = "O link da foto pode ter até 5000 caracteres.")
    private String foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("usuario")
    private List<Postagens> postagem;
    
    public String getFoto() {
        return foto;
    }

    public List<Postagens> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagens> postagem) {
		this.postagem = postagem;
	}

	public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    

}
