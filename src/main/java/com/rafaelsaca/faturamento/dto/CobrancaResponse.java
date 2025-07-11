package com.rafaelsaca.faturamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rafaelsaca.faturamento.model.Cliente;
import com.rafaelsaca.faturamento.model.Cobranca.Status;
import com.rafaelsaca.faturamento.model.Cobranca.Tipo;

public class CobrancaResponse {

    private Long id;
    private BigDecimal valor;
    private String descricao;
    private LocalDate dataVencimento;
    private Tipo tipo;
    private Status status;
    private Cliente cliente;
    private String pdfUrl;

    public CobrancaResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

}