package com.nozama.api_nozama.model;

public class Endereco {
    private int enderecoID;
    private String rua;
    private String cep;
    private String numero;
    private String cidade;
    private String estado;
    private String complemento;
    public Endereco(int enderecoID, String rua, String cep, String numero, String cidade, String estado,
            String complemento) {
        this.enderecoID = enderecoID;
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.complemento = complemento;
    }
    public Endereco() {
    }
    public int getEnderecoID() {
        return enderecoID;
    }
    public String getRua() {
        return rua;
    }
    public String getCep() {
        return cep;
    }
    public String getNumero() {
        return numero;
    }
    public String getCidade() {
        return cidade;
    }
    public String getEstado() {
        return estado;
    }
    public String getComplemento() {
        return complemento;
    }

    
}
