package org.serratec.trabalhofinal.redesocialsimples.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "postagem")
public class Postagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_postagem")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@NotBlank(message = "Adicione um conteúdo")
	@Size(max = 255)
	@Column(name = "conteudo", nullable = false, length = 255)
	private String conteudo;

	@NotBlank(message = "Adicione a data de criação")
	@Column(name = "data_criacao", nullable = false)
	private LocalDate dataCriacao;

	@OneToMany(mappedBy = "postagem")
	private List<Comentario> postagem;

	public Postagem(Long id, String conteudo, LocalDate dataCriacao, Usuario usuario, List<Comentario> postagem) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
		this.usuario = usuario;
		this.postagem = postagem;
	}

	public Postagem() {
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

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Comentario> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Comentario> postagem) {
		this.postagem = postagem;
	}

}