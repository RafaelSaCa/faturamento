package com.rafaelsaca.faturamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafaelsaca.faturamento.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCpfCnpj(String cpfCnpj);

}
