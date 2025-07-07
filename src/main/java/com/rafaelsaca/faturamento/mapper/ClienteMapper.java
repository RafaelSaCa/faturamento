package com.rafaelsaca.faturamento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rafaelsaca.faturamento.dto.ClienteRequest;
import com.rafaelsaca.faturamento.dto.ClienteResponse;
import com.rafaelsaca.faturamento.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "endereco", ignore = true) // endereço será setado separadamente
    @Mapping(target = "id", ignore = true) //id nao é enviado na request
    Cliente toEntity (ClienteRequest request);


    ClienteResponse toResponse (Cliente cliente);

}
