package com.rafaelsaca.faturamento.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.rafaelsaca.faturamento.service.PDFService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cobranca")
public class CobrancaController {

    private final CobrancaService service;
    private final PDFService pdfService;

    public CobrancaController(CobrancaService service, PDFService pdfService) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @PostMapping()
    public ResponseEntity<byte[]> gerarCobranca(@RequestBody @Valid CobrancaRequest request) {

        CobrancaResponse cobranca = service.create(request);
        byte[] pdf;

        try {

            pdf = pdfService.gerarCobrancaPDF(cobranca);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=cobranca_" + cobranca.getId() + ".pdf")
                .body(pdf);
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
