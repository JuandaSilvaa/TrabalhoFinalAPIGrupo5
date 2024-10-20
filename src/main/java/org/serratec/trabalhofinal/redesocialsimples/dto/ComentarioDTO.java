package org.serratec.trabalhofinal.redesocialsimples.dto;

import java.time.LocalDate;

import org.serratec.trabalhofinal.redesocialsimples.entity.Comentario;

public class ComentarioDTO {

    private Long id;
    private String conteudo;
    private LocalDate dataCriacao;
    
  
    public ComentarioDTO(Long id, String conteudo, LocalDate dataCriacao) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.dataCriacao = dataCriacao;
	}

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.conteudo = comentario.getConteudo();
        this.dataCriacao = comentario.getDateCriacao();
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
}
