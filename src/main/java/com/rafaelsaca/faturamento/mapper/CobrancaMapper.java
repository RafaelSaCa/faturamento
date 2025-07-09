package com.rafaelsaca.faturamento.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.rafaelsaca.faturamento.dto.CobrancaRequest;
import com.rafaelsaca.faturamento.dto.CobrancaResponse;
import com.rafaelsaca.faturamento.model.Cobranca;

@Mapper(componentModel = "spring")
public interface CobrancaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "status", ignore = true)
    Cobranca toEntity(CobrancaRequest request);

    CobrancaResponse toResponse(Cobranca cobranca);

}
