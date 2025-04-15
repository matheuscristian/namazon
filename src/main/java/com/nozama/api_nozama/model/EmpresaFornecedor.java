package com.nozama.api_nozama.model;

public class EmpresaFornecedor {
    private int empresaFornecedorID;
    private int empresaID;
    private int fornecedorID;
    private int produtoID;
    public EmpresaFornecedor(int empresaFornecedorID, int empresaID, int fornecedorID, int produtoID) {
        this.empresaFornecedorID = empresaFornecedorID;
        this.empresaID = empresaID;
        this.fornecedorID = fornecedorID;
        this.produtoID = produtoID;
    }
    public EmpresaFornecedor() {
    }
    public int getEmpresaFornecedorID() {
        return empresaFornecedorID;
    }
    public int getEmpresaID() {
        return empresaID;
    }
    public int getFornecedorID() {
        return fornecedorID;
    }
    public int getProdutoID() {
        return produtoID;
    }

    
}
