package com.nozama.api_nozama.model;

import java.math.BigDecimal;

public class Pedido {
    private int pedidoID;
    private int usuarioID;
    private String dataPedido;
    private String statusPedido;
    private BigDecimal valorTotal;
    private String metodoDePagamento;
    
    public Pedido(int pedidoID, int usuarioID, String dataPedido, String statusPedido, BigDecimal valorTotal,
            String metodoDePagamento) {
        this.pedidoID = pedidoID;
        this.usuarioID = usuarioID;
        this.dataPedido = dataPedido;
        this.statusPedido = statusPedido;
        this.valorTotal = valorTotal;
        this.metodoDePagamento = metodoDePagamento;
    }

    public Pedido() {
    }

    public int getPedidoID() {
        return pedidoID;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getMetodoDePagamento() {
        return metodoDePagamento;
    }
    
    

    // Getters, Setters, Constructors
}
