package com.nozama.api_nozama.model;

public class Fornecedor {
    private int fornecedorID;
    private String nome;
    private String telefone;
    public Fornecedor(int fornecedorID, String nome, String telefone) {
        this.fornecedorID = fornecedorID;
        this.nome = nome;
        this.telefone = telefone;
    }
    public Fornecedor() {
    }
    public int getFornecedorID() {
        return fornecedorID;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }

    
}
