package com.rafaelsaca.faturamento.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private String descricao;
    private LocalDate dataVencimento;
    private Status status;
    private Tipo tipo;

    @ManyToOne
    private Cliente cliente;

    public Cobranca() {
    }

    public Cobranca(Long id, BigDecimal valor, String descricao, LocalDate dataVencimento, Status status, Tipo tipo,
            Cliente cliente) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.status = status;
        this.tipo = tipo;
        this.cliente = cliente;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public enum Status {
        PENDENTE,
        PAGO,
        CANCELADO

    }

    public enum Tipo {
        BOLETO,
        PIX,
        LINK_CARTAO
    }
}
