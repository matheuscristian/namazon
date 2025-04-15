package com.nozama.api_nozama.model;

public class Categoria {
    private int categoriaID;
    private String nome;
    public Categoria(int categoriaID, String nome) {
        this.categoriaID = categoriaID;
        this.nome = nome;
    }
    public Categoria() {
    }
    public int getCategoriaID() {
        return categoriaID;
    }
    public String getNome() {
        return nome;
    }

    
}
