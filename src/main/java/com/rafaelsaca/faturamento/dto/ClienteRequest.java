package com.rafaelsaca.faturamento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClienteRequest {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    private String cpfCnpj;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    public ClienteRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
