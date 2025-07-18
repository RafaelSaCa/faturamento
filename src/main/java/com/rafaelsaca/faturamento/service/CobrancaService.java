package com.rafaelsaca.faturamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rafaelsaca.faturamento.dto.CobrancaRequest;
import com.rafaelsaca.faturamento.dto.CobrancaResponse;
import com.rafaelsaca.faturamento.exception.RecursoNaoEncontradoException;
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
    private final PDFService pdfService;
    private final EmailService emailService;

    public CobrancaService(CobrancaRepository repository, ClienteRepository clienteRepository,
            CobrancaMapper mapper, PDFService pdfService, EmailService emailService) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.mapper = mapper;
        this.pdfService = pdfService;
        this.emailService = emailService;
    }

    public CobrancaResponse create(CobrancaRequest request) {
        Cobranca cobranca = mapper.toEntity(request);

        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado para o id:" + request.getClienteId()));

        cobranca.setCliente(cliente);
        cobranca.setStatus(Status.PENDENTE);

        Cobranca novaCobranca = repository.save(cobranca);
        
        try{
            String pdfPath = pdfService.gerarCobrancaPDF(novaCobranca);
            novaCobranca.setPdfPath(pdfPath);
            repository.save(novaCobranca);

            emailService.enviarCobrancaPorEmail(cliente, pdfPath);
            
        }catch (Exception e){
            throw new RuntimeException("Erro ao gerar PDF:"+ e.getMessage());

        }
        return mapper.toResponse(novaCobranca);

    }

    public CobrancaResponse findById(Long cobrancaId) {
        Cobranca cobranca = repository.findById(cobrancaId)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException("Cobrança com id: " + cobrancaId + ",não encontrada!"));

        return mapper.toResponse(cobranca);
    }

    public List<CobrancaResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<CobrancaResponse> findByClienteId(Long clienteId) {
        clienteRepository.findById(clienteId)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException("Cliente com id " + clienteId + ", não encontrado!"));

        List<Cobranca> cobrancas = repository.findByClienteId(clienteId);

        return cobrancas.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

}
