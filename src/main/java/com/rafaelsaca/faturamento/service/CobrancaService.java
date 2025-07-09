package com.rafaelsaca.faturamento.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.rafaelsaca.faturamento.dto.CobrancaRequest;
import com.rafaelsaca.faturamento.dto.CobrancaResponse;
import com.rafaelsaca.faturamento.mapper.CobrancaMapper;
import com.rafaelsaca.faturamento.model.Cliente;
import com.rafaelsaca.faturamento.model.Cobranca;
import com.rafaelsaca.faturamento.model.Cobranca.Status;
import com.rafaelsaca.faturamento.repository.ClienteRepository;
import com.rafaelsaca.faturamento.repository.CobrancaRepository;

@Service
public class CobrancaService {

    private final CobrancaRepository repository;
    private final ClienteRepository clienteRepository;
    private final CobrancaMapper mapper;

    public CobrancaService(CobrancaRepository repository, ClienteRepository clienteRepository, CobrancaMapper mapper) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
    }

    public CobrancaResponse create(CobrancaRequest request) {
        Cobranca cobranca = mapper.toEntity(request);

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o id:" + request.getClienteId()));

        cobranca.setCliente(cliente);
        cobranca.setStatus(Status.PENDENTE);

        Cobranca novaCobranca = repository.save(cobranca);

        return mapper.toResponse(novaCobranca);

    }


    public List<CobrancaResponse> findAll (){
        return repository.findAll()
                    .stream()
                    .map(mapper::toResponse)
                    .collect(Collectors.toList());
    }


}
