package com.rafaelsaca.faturamento.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rafaelsaca.faturamento.dto.ClienteRequest;
import com.rafaelsaca.faturamento.dto.ClienteResponse;
import com.rafaelsaca.faturamento.mapper.ClienteMapper;
import com.rafaelsaca.faturamento.model.Cliente;
import com.rafaelsaca.faturamento.model.Endereco;
import com.rafaelsaca.faturamento.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ViaCepService viaCepService;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ViaCepService viaCepService, ClienteMapper mapper) {
        this.repository = repository;
        this.viaCepService = viaCepService;
        this.mapper = mapper;
    }

    public ClienteResponse create(ClienteRequest request) {

        if (repository.findByCpfCnpj(request.getCpfCnpj()).isPresent()) {
            throw new RuntimeException("Cliente já cadastrado com o CPF/CNPJ: " + request.getCpfCnpj());
        }

        Endereco endereco = viaCepService.buscarEnderecoPorCep(request.getCep());

        if (endereco == null) {
            throw new RuntimeException("Endereço não encontrado para o CEP: " + request.getCep());
        }

        endereco.setNumero(request.getNumero());

        Cliente cliente = mapper.toEntity(request);
        cliente.setEndereco(endereco);

        Cliente novoCliente = repository.save(cliente);

        return mapper.toResponse(novoCliente);

    }

    public List<ClienteResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    public ClienteResponse update(Long clienteId, ClienteRequest request) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + clienteId));

        if (request.getNome() != null && !request.getNome().isBlank()) {
            cliente.setNome(request.getNome());
        }

        if (request.getCpfCnpj() != null && !request.getCpfCnpj().isBlank()) {
            if (repository.findByCpfCnpj(request.getCpfCnpj()).isPresent()
                    && !repository.findByCpfCnpj(request.getCpfCnpj()).get().getId().equals(clienteId)) {
                throw new RuntimeException("Já existe um cliente cadastrado com o CPF/CNPJ: " + request.getCpfCnpj());
            }

            cliente.setCpfCnpj(request.getCpfCnpj());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            cliente.setEmail(request.getEmail());
        }

        if (request.getTelefone() != null && !request.getTelefone().isBlank()) {
            cliente.setTelefone(request.getTelefone());
        }

        if (request.getCep() != null && !request.getCep().isBlank()) {

            Endereco endereco = viaCepService.buscarEnderecoPorCep(request.getCep());

            if (endereco == null) {
                throw new RuntimeException("Endereço não encontrado para o CEP: " + request.getCep());
            }
            cliente.setEndereco(endereco);
        }

        if (request.getNumero() != null && !request.getNumero().isBlank()) {
           cliente.getEndereco().setNumero(request.getNumero());
        }

        Cliente clienteAtualizado = repository.save(cliente);
        return mapper.toResponse(clienteAtualizado);

    }

    public void delete(Long clienteId) {

        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Cliente com o id: %d não encontrado!", clienteId)));

        repository.delete(cliente);
    }

}
