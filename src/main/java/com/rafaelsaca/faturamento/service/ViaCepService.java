package com.rafaelsaca.faturamento.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rafaelsaca.faturamento.model.Endereco;

@Service
public class ViaCepService {

    public Endereco buscarEnderecoPorCep(String cep) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ResponseEntity<Endereco> response = restTemplate.getForEntity(url, Endereco.class);
        
        return response.getBody();
    }

}
