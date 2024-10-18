package org.serratec.trabalhofinal.redesocialsimples.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.serratec.trabalhofinal.redesocialsimples.entity.Comentario;

public class PostagemInserirDTO {

	private String usuario;
	private String conteudo;
	private LocalDate datacriacao;
	private Set<Comentario> comentarios;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public LocalDate getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDate datacriacao) {
		this.datacriacao = datacriacao;
	}

	public Set<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(Set<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

}
