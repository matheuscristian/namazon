package com.nozama.api_nozama.model;

import java.time.LocalDate;

public class CadastroUsuario {
    private int usuarioID;
    private String cpf;
    private String nome;
    private String telefone;
    private LocalDate dataNascimento;
    private String senha;
    public CadastroUsuario(int usuarioID, String cpf, String nome, String telefone, LocalDate dataNascimento,
            String senha) {
        this.usuarioID = usuarioID;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }
    public CadastroUsuario() {
    }
    public int getUsuarioID() {
        return usuarioID;
    }
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public String getSenha() {
        return senha;
    }
    

    
    // Getters, Setters, Constructors
}
