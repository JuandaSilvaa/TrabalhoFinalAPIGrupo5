package org.serratec.trabalhofinal.redesocialsimples.domain;


import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column
	private Long id;
	private String conteudo;
	private Date dateCriacao;
	
	/*
	@ManyToOne
	@JoinColumn(name = "id_postagem")
	private Postagem postagem;
	
	@ManyToOne
	@JoinColumn(name ="id_usuario")
	private Usuario usuario;
	
	  
	@ManyToOne
	@JoinColumn(name ="")
	private Relacionamento relacionamento;*/
	
	public comentario() {}
	
	public comentario(Long id, String conteudo, Date dateCriacao) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dateCriacao = dateCriacao;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public Date getDateCriacao() {
		return dateCriacao;
	}
	public void setDateCriacao(Date dateCriacao) {
		this.dateCriacao = dateCriacao;
	}
	
	
	
}
