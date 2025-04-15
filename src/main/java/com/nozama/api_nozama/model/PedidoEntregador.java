package com.nozama.api_nozama.model;

public class PedidoEntregador {
    private int pedidoVendedorID;
    private int pedidoID;
    private int entregadorID;
    public PedidoEntregador(int pedidoVendedorID, int pedidoID, int vendedorID) {
        this.pedidoVendedorID = pedidoVendedorID;
        this.pedidoID = pedidoID;
        this.entregadorID = vendedorID;
    }
    public PedidoEntregador() {
    }
    public int getPedidoVendedorID() {
        return pedidoVendedorID;
    }
    public int getPedidoID() {
        return pedidoID;
    }
    public int getEntregadorID() {
        return entregadorID;
    }

    
}
