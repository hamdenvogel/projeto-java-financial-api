package com.hvogel.projeto_financial_api.service;

import java.util.List;
import java.util.Optional;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;

public interface CreditoService {
	
	Optional<CreditoDTO> findByNumeroCredito(String numeroCredito);
	List<CreditoDTO> findByNumeroNfse(String numeroNfse);
	CreditoDTO save(CreditoDTO creditoDTO);
	CreditoDTO update(CreditoDTO creditoDTO);
	void delete(Long id);
	Optional<CreditoDTO> findById(Long id);
	List<CreditoDTO> findAllByOrderByIdAsc();
}
