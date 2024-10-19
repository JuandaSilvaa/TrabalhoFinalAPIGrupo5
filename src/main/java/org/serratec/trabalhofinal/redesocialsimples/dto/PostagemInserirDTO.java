package org.serratec.trabalhofinal.redesocialsimples.dto;

import java.time.LocalDate;
import java.util.Set;

import org.serratec.trabalhofinal.redesocialsimples.entity.Comentario;

public class PostagemInserirDTO {

	private Long usuarioId; 
	private String conteudo;
	private LocalDate datacriacao;
	private Set<Comentario> comentarios;
	
	public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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
