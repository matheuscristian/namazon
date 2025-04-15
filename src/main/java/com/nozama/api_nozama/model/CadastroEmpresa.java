package com.nozama.api_nozama.model;

public class CadastroEmpresa {
    private int empresaID;
    private String cnpj;
    private String email;
    private String nomeRepresentante;
    private String endereco;
    private String senha;
    public CadastroEmpresa(int empresaID, String cnpj, String email, String nomeRepresentante, String endereco,
            String senha) {
        this.empresaID = empresaID;
        this.cnpj = cnpj;
        this.email = email;
        this.nomeRepresentante = nomeRepresentante;
        this.endereco = endereco;
        this.senha = senha;
    }
    public CadastroEmpresa() {
    }
    public int getEmpresaID() {
        return empresaID;
    }
    public String getCnpj() {
        return cnpj;
    }
    public String getEmail() {
        return email;
    }
    public String getNomeRepresentante() {
        return nomeRepresentante;
    }
    public String getEndereco() {
        return endereco;
    }
    public String getSenha() {
        return senha;
    }

    
}
