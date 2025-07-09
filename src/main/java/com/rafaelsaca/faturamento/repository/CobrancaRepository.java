package com.rafaelsaca.faturamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelsaca.faturamento.model.Cobranca;

public interface CobrancaRepository extends JpaRepository<Cobranca,Long>{

   Optional<Cobranca> findByClienteId (Long clienteId);
}
