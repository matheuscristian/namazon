package com.nozama.api_nozama.model;

import java.time.LocalDate;

public class AvaliacaoProduto {
    private int avaliacaoProdutoID;
    private int produtoID;
    private int usuarioID;
    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;
    public AvaliacaoProduto(int avaliacaoProdutoID, int produtoID, int usuarioID, Integer nota, String comentario,
            LocalDate dataAvaliacao) {
        this.avaliacaoProdutoID = avaliacaoProdutoID;
        this.produtoID = produtoID;
        this.usuarioID = usuarioID;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }
    public AvaliacaoProduto() {
    }
    public int getAvaliacaoProdutoID() {
        return avaliacaoProdutoID;
    }
    public int getProdutoID() {
        return produtoID;
    }
    public int getUsuarioID() {
        return usuarioID;
    }
    public Integer getNota() {
        return nota;
    }
    public String getComentario() {
        return comentario;
    }
    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    
}
