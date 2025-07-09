package com.rafaelsaca.faturamento.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.rafaelsaca.faturamento.model.Cobranca.Tipo;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class CobrancaRequest {

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Valor é obrigatório")
    @DecimalMin(value = "1", message = "Valor deve ser maior que zero")
    private BigDecimal valor;

    private String descricao;

    @NotNull(message = "O tipo é obrigatório")
    private Tipo tipo;

    @Future
    @NotNull(message = "A data de vencimento é obrigatório")
    private LocalDate dataVencimento;

    public CobrancaRequest() {
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

}
