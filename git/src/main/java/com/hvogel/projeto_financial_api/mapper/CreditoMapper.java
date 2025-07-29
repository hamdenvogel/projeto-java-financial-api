package com.hvogel.projeto_financial_api.mapper;

import org.mapstruct.Mapper;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.entity.Credito;

@Mapper(componentModel = "spring")
public interface CreditoMapper extends EntityMapper<CreditoDTO, Credito> {
	
	default Credito fromId (Long id) {
        if (id == null) {
            return null;
        }
        Credito credito = new Credito();
        credito.setId(id);
        return credito;
    }
	
	CreditoDTO toDto (Credito credito);

	Credito toEntity (CreditoDTO creditoDTO);

}
