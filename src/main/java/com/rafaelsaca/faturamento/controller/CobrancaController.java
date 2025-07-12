package com.rafaelsaca.faturamento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelsaca.faturamento.dto.CobrancaRequest;
import com.rafaelsaca.faturamento.dto.CobrancaResponse;
import com.rafaelsaca.faturamento.service.CobrancaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cobranca")
public class CobrancaController {

    private final CobrancaService service;


    public CobrancaController(CobrancaService service) {
        this.service = service;
        
    }

    @PostMapping()
    public ResponseEntity<CobrancaResponse> gerarCobranca(@RequestBody @Valid CobrancaRequest request) {

        CobrancaResponse cobranca = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(cobranca);

    }

    @GetMapping()
    public ResponseEntity<List<CobrancaResponse>> getAll() {
        List<CobrancaResponse> lista = service.findAll();

        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<CobrancaResponse>> findByClienteId(@PathVariable Long clienteId) {
        List<CobrancaResponse> cobrancas = service.findByClienteId(clienteId);

        return ResponseEntity.ok().body(cobrancas);
    }
}
