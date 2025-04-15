package com.nozama.api_nozama.model;

public class Estoque {
    private int estoqueID;
    private int produtoID;
    private int quantidade;
    public Estoque(int estoqueID, int produtoID, int quantidade) {
        this.estoqueID = estoqueID;
        this.produtoID = produtoID;
        this.quantidade = quantidade;
    }
    public Estoque() {
    }
    public int getEstoqueID() {
        return estoqueID;
    }
    public int getProdutoID() {
        return produtoID;
    }
    public int getQuantidade() {
        return quantidade;
    }

    
}
