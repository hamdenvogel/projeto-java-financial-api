package com.hvogel.projeto_financial_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hvogel.projeto_financial_api.entity.Credito;

public interface CreditoRepository extends JpaRepository<Credito, Long>{
	
	Optional<Credito> findByNumeroCredito(String numeroCredito);
	List<Credito> findByNumeroNfse(String numeroNfse);
	List<Credito> findAllByOrderByIdAsc();
}
