package com.hvogel.projeto_financial_api.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.entity.Credito;
import com.hvogel.projeto_financial_api.exception.CreditoNotFoundException;
import com.hvogel.projeto_financial_api.mapper.CreditoMapper;
import com.hvogel.projeto_financial_api.repository.CreditoRepository;
import com.hvogel.projeto_financial_api.service.CreditoService;

@Service
public class CreditoServiceImpl implements CreditoService {
	
	private final CreditoRepository creditoRepository;
	
	private final CreditoMapper creditoMapper;	

	public CreditoServiceImpl(CreditoRepository creditoRepository, CreditoMapper creditoMapper) {
		super();
		this.creditoRepository = creditoRepository;
		this.creditoMapper = creditoMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CreditoDTO> findByNumeroCredito(String numeroCredito) {
		return creditoRepository.findByNumeroCredito(numeroCredito).map(creditoMapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CreditoDTO> findByNumeroNfse(String numeroNfse) {
		return creditoRepository.findByNumeroNfse(numeroNfse).stream().map(creditoMapper::toDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CreditoDTO save(CreditoDTO creditoDTO) {
		Credito credito = creditoMapper.toEntity(creditoDTO);
		credito =  creditoRepository.save(credito);
		return creditoMapper.toDto(credito);
	}	

	@Override
	public Optional<CreditoDTO> findById(Long id) {
		return Optional.ofNullable(creditoRepository.findById(id).map(creditoMapper::toDto)
				.orElseThrow(() -> new CreditoNotFoundException("Crédito não encontrado")));
	}

	@Override
	@Transactional(readOnly = true)
	public List<CreditoDTO> findAllByOrderByIdAsc() {
		return creditoRepository.findAllByOrderByIdAsc().stream().map(creditoMapper::toDto).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public CreditoDTO update(CreditoDTO creditoDTO) {
		Optional<CreditoDTO> creditoExistente = findById(creditoDTO.getId());
		 creditoExistente.ifPresent(c -> {
			try {
				 BeanUtils.copyProperties(c, creditoDTO);
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}			
		});
		 
		if (creditoExistente.isEmpty()) {
			throw new CreditoNotFoundException("Crédito não encontrado");
		}
		CreditoDTO creditoAtualizado = creditoExistente.get();
		return save(creditoAtualizado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Objects.requireNonNull(id);
		Optional<CreditoDTO> creditoExistente = findById(id);
		creditoExistente.ifPresent(c -> {
			Credito credito = creditoMapper.toEntity(c);
			creditoRepository.delete(credito);		
		}); 		
	}

}
