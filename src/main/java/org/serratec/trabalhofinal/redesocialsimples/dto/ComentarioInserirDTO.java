package org.serratec.trabalhofinal.redesocialsimples.dto;

import java.time.LocalDate;

public class ComentarioInserirDTO {

    private String conteudo;
    private LocalDate dataCriacao;
    private Long postagemId;

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

    public Long getPostagemId() {
        return postagemId;
    }

    public void setPostagemId(Long postagemId) {
        this.postagemId = postagemId;
    }
}
