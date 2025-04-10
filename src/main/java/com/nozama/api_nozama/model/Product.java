package com.nozama.api_nozama.model;

public class Product {
    public Integer produtoID;
    public Integer categoriaID;
    public String nomeProduto;
    public String descricao;
    public Double preco;
    public String dimensaoDaEmbalagem;
    public String certificacao;
    public String codigoDoProduto;
    public String paisDeOrigem;

    public Product(Integer produtoID, Integer categoriaID, String nomeProduto, String descricao, Double preco,
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

    public Product() {}
}
