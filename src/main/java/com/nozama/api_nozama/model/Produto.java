package com.nozama.api_nozama.model;

import java.math.BigDecimal;

public class Produto {
    private int produtoID;
    private Integer categoriaID;
    private String nomeProduto;
    private String descricao;
    private BigDecimal preco;
    private String dimensaoDaEmbalagem;
    private String certificacao;
    private String codigoDoProduto;
    private String paisDeOrigem;
    
    public Produto(int produtoID, Integer categoriaID, String nomeProduto, String descricao, BigDecimal preco,
            String dimensaoDaEmbalagem, String certificacao, String codigoDoProduto, String paisDeOrigem) {
        this.produtoID = produtoID;
        this.categoriaID = categoriaID;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
        this.dimensaoDaEmbalagem = dimensaoDaEmbalagem;
        this.certificacao = certificacao;
        this.codigoDoProduto = codigoDoProduto;
        this.paisDeOrigem = paisDeOrigem;
    }
    public Produto() {
    }
    public int getProdutoID() {
        return produtoID;
    }
    public Integer getCategoriaID() {
        return categoriaID;
    }
    public String getNomeProduto() {
        return nomeProduto;
    }
    public String getDescricao() {
        return descricao;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public String getDimensaoDaEmbalagem() {
        return dimensaoDaEmbalagem;
    }
    public String getCertificacao() {
        return certificacao;
    }
    public String getCodigoDoProduto() {
        return codigoDoProduto;
    }
    public String getPaisDeOrigem() {
        return paisDeOrigem;
    }
    
    

    // Getters, Setters, Constructors
}

