package com.rafaelsaca.faturamento.dto;

import com.rafaelsaca.faturamento.model.Endereco;

public class ClienteResponse {

    public Long id;
    public String nome;
    public String cpfCnpj;
    public String email;
    public String telefone;
    public Endereco endereco;

    public ClienteResponse() {
    }

    public ClienteResponse(Long id, String nome, String cpfCnpj, String email, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

}
