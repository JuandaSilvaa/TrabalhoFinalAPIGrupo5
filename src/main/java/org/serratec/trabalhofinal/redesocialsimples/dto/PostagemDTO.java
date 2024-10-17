package org.serratec.trabalhofinal.redesocialsimples.dto;

import java.time.LocalDate;
import java.util.List;

import org.serratec.trabalhofinal.redesocialsimples.entity.Comentario;
import org.serratec.trabalhofinal.redesocialsimples.entity.Postagem;

public class PostagemDTO {

	private Long id;
	private String conteudo;
	private LocalDate dataCriacao;
	private List<Comentario> comentarios;

	public PostagemDTO() {
	}

	public PostagemDTO(Long id, String conteudo, LocalDate dataCriacao) {
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
	}

	public PostagemDTO(Postagem postagem) {
		this.id = postagem.getId();
		this.conteudo = postagem.getConteudo();
		this.dataCriacao = postagem.getDataCriacao();
		this.comentarios = postagem.getComentario();
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}
