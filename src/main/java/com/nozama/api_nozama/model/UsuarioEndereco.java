package com.nozama.api_nozama.model;

public class UsuarioEndereco {
    private int usuarioEnderecoID;
    private int usuarioID;
    private int enderecoID;
    public UsuarioEndereco(int usuarioEnderecoID, int usuarioID, int enderecoID) {
        this.usuarioEnderecoID = usuarioEnderecoID;
        this.usuarioID = usuarioID;
        this.enderecoID = enderecoID;
    }
    public UsuarioEndereco() {
    }
    public int getUsuarioEnderecoID() {
        return usuarioEnderecoID;
    }
    public int getUsuarioID() {
        return usuarioID;
    }
    public int getEnderecoID() {
        return enderecoID;
    }

    
}
