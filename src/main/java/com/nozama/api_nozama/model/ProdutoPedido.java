package com.nozama.api_nozama.model;

public class ProdutoPedido {
    private int produtoPedidoID;
    private int produtoID;
    private int pedidoID;
    public ProdutoPedido(int produtoPedidoID, int produtoID, int pedidoID) {
        this.produtoPedidoID = produtoPedidoID;
        this.produtoID = produtoID;
        this.pedidoID = pedidoID;
    }
    public ProdutoPedido() {
    }
    public int getProdutoPedidoID() {
        return produtoPedidoID;
    }
    public int getProdutoID() {
        return produtoID;
    }
    public int getPedidoID() {
        return pedidoID;
    }

    
}
