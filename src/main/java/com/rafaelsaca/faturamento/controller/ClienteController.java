package com.rafaelsaca.faturamento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelsaca.faturamento.dto.ClienteRequest;
import com.rafaelsaca.faturamento.dto.ClienteResponse;
import com.rafaelsaca.faturamento.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest request) {
        ClienteResponse cliente = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteResponse>> getAll() {
        List<ClienteResponse> lista = service.getAll();

        return ResponseEntity.ok().body(lista);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteResponse> update(@PathVariable Long clienteId, @Valid @RequestBody ClienteRequest request) {
        ClienteResponse cliente = service.update(clienteId, request);

        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
        service.delete(clienteId);
        return ResponseEntity.noContent().build();
    }
}
