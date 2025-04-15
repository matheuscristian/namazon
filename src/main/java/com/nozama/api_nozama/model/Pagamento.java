package com.nozama.api_nozama.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pagamento {
    private int pagamentoID;
    private int pedidoID;
    private BigDecimal valorPagamento;
    private String formaDePagamento;
    private LocalDate dataPagamento;
    public Pagamento(int pagamentoID, int pedidoID, BigDecimal valorPagamento, String formaDePagamento,
            LocalDate dataPagamento) {
        this.pagamentoID = pagamentoID;
        this.pedidoID = pedidoID;
        this.valorPagamento = valorPagamento;
        this.formaDePagamento = formaDePagamento;
        this.dataPagamento = dataPagamento;
    }
    public Pagamento() {
    }
    public int getPagamentoID() {
        return pagamentoID;
    }
    public int getPedidoID() {
        return pedidoID;
    }
    public BigDecimal getValorPagamento() {
        return valorPagamento;
    }
    public String getFormaDePagamento() {
        return formaDePagamento;
    }
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    
}
