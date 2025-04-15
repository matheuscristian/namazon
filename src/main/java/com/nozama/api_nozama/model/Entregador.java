package com.nozama.api_nozama.model;

public class Entregador {
    private int entregadorID;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    public Entregador(int vendedorID, String nome, String email, String telefone, String senha) {
        this.entregadorID = vendedorID;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }
    public Entregador() {
    }
    public int getEntregadorID() {
        return entregadorID;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getSenha() {
        return senha;
    }

    
}
