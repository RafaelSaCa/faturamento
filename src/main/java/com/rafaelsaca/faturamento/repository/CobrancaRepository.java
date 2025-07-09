package com.rafaelsaca.faturamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelsaca.faturamento.model.Cobranca;

public interface CobrancaRepository extends JpaRepository<Cobranca,Long>{

   List<Cobranca> findByClienteId (Long clienteId);
}
