package com.hvogel.projeto_financial_api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.entity.Credito;
import com.hvogel.projeto_financial_api.exception.CreditoNotFoundException;
import com.hvogel.projeto_financial_api.mapper.CreditoMapper;
import com.hvogel.projeto_financial_api.repository.CreditoRepository;
import com.hvogel.projeto_financial_api.service.impl.CreditoServiceImpl;
import com.hvogel.projeto_financial_api.util.CreditoTestHelper;

class CreditoServiceTest {

	@InjectMocks
	CreditoServiceImpl creditoService;

	@Mock
	CreditoRepository creditoRepository;

	@Spy
	CreditoMapper creditoMapper = Mappers.getMapper(CreditoMapper.class);

	Credito credito;

	CreditoDTO creditoDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		// Cenário
		credito = CreditoTestHelper.createCreditoPadrao();
		creditoDTO = creditoMapper.toDto(credito);

		when(creditoRepository.save(any(Credito.class))).thenReturn(credito);
	}

	private void assertCreditoDTOMatchesPadrao(CreditoDTO dto) {
		assertEquals("123456", dto.getNumeroCredito());
		assertEquals("7891011", dto.getNumeroNfse());
		assertEquals(LocalDate.of(2024, Month.FEBRUARY, 25), dto.getDataConstituicao());
		assertEquals(BigDecimal.valueOf(1500.75), dto.getValorIssqn());
		assertEquals(Boolean.valueOf(true), dto.isSimplesNacional());
		assertEquals(BigDecimal.valueOf(5.0), dto.getAliquota());
		assertEquals(BigDecimal.valueOf(30000.00), dto.getValorFaturado());
		assertEquals(BigDecimal.valueOf(5000.00), dto.getValorDeducao());
		assertEquals(BigDecimal.valueOf(25000.00), dto.getBaseCalculo());
	}

	@Test
	void deveCriarUmCredito() {
		// Cenário
		CreditoDTO creditoCriado = creditoService.save(creditoDTO);

		// Verificação
		assertNotNull(creditoCriado);
		assertCreditoDTOMatchesPadrao(creditoCriado);

		// Verificar se o repositório foi chamado
		verify(creditoRepository, times(1)).save(credito);
	}

	@Test
	void deveDeletarUmCredito() {
		// Cenário
		CreditoDTO creditoSalvoDTO = creditoService.save(creditoDTO);

		Credito creditoSalvo = creditoMapper.toEntity(creditoSalvoDTO);

		when(creditoRepository.findById(creditoSalvo.getId())).thenReturn(Optional.of(creditoSalvo));

		// Execução
		creditoService.delete(creditoSalvoDTO.getId());

		// Verificação
		Mockito.verify(creditoRepository).delete(credito);
	}

	@Test
	void deveNaoDeletarUmCreditoQuandoOCreditoNaoExistir() {
		// Cenário
		Long idInexistente = 100L;
		credito.setId(idInexistente);

		Mockito.doThrow(CreditoNotFoundException.class).when(creditoRepository).findById(idInexistente);

		// Execução
		Long id = credito.getId();
		org.junit.jupiter.api.Assertions
				.assertThrows(CreditoNotFoundException.class, () -> creditoService.delete(id));

		// Verificação
		Mockito.verify(creditoRepository, Mockito.never()).delete(credito);
	}

	@Test
	void deveObterUmCreditoPorId() {
		// Cenário
		Mockito.when(creditoRepository.findById(credito.getId())).thenReturn(Optional.of(credito));

		// Execução
		Optional<CreditoDTO> resultado = creditoService.findById(credito.getId());

		// Verificação
		Assertions.assertThat(resultado).isPresent();
	}

	@Test
	void deveLancarErroQuandoUmCreditoNaoExistir() {
		// Cenário
		Long idInexistente = 100L;
		credito.setId(idInexistente);

		// Execução
		when(creditoRepository.findById(idInexistente)).thenReturn(Optional.empty());

		// Verificação
		assertThrows(CreditoNotFoundException.class, () -> creditoService.findById(idInexistente));
	}

	@Test
	void deveRetornarCreditoExistentePorId() {
		// Cenário
		when(creditoRepository.findById(credito.getId())).thenReturn(Optional.of(credito));

		// Execução
		Optional<CreditoDTO> result = creditoService.findById(credito.getId());

		// Verificação
		Assertions.assertThat(result).isPresent();
	}

	@Test
	void deveAtualizarUmCredito() {
		// Cenário
		Mockito.when(creditoRepository.findById(credito.getId())).thenReturn(Optional.of(credito));

		// Execução
		Optional<CreditoDTO> resultado = creditoService.findById(credito.getId());

		credito.setAliquota(BigDecimal.valueOf(9.9));

		creditoDTO = creditoMapper.toDto(credito);

		CreditoDTO creditoAlteradoDTO = creditoService.update(creditoDTO);

		Credito creditoAlterado = creditoMapper.toEntity(creditoAlteradoDTO);

		assertFalse(resultado.isEmpty());
		assertNotNull(resultado);
		assertEquals(creditoAlterado.getNumeroCredito(), credito.getNumeroCredito());
		assertEquals(creditoAlterado.getNumeroNfse(), credito.getNumeroNfse());
		assertEquals(creditoAlterado.getDataConstituicao(), credito.getDataConstituicao());
		assertEquals(creditoAlterado.getValorIssqn(), credito.getValorIssqn());
		assertEquals(creditoAlterado.getAliquota(), credito.getAliquota());
		assertEquals(creditoAlterado.getValorFaturado(), credito.getValorFaturado());
		assertEquals(creditoAlterado.getValorDeducao(), credito.getValorDeducao());
		assertEquals(creditoAlterado.getBaseCalculo(), credito.getBaseCalculo());

		// Verificação
		Mockito.verify(creditoRepository, Mockito.times(1)).save(creditoAlterado);
	}

	@Test
	void deveNaoAtualizarUmCreditoQuandoOCreditoNaoExistir() {
		// Cenário
		Long idInexistente = 100L;
		credito.setId(idInexistente);

		CreditoDTO dto = creditoMapper.toDto(credito);

		Mockito.doThrow(CreditoNotFoundException.class).when(creditoRepository).findById(idInexistente);

		// Execução
		org.junit.jupiter.api.Assertions
				.assertThrows(CreditoNotFoundException.class, () -> creditoService.update(dto));

		// Verificação
		Mockito.verify(creditoRepository, Mockito.never()).save(credito);
	}

	@Test
	void deveObterUmCreditoPorNumeroDeCredito() {
		// Cenário
		Mockito.when(creditoRepository.findByNumeroCredito(credito.getNumeroCredito()))
				.thenReturn(Optional.of(credito));

		// Execução
		Optional<CreditoDTO> resultado = creditoService.findByNumeroCredito(credito.getNumeroCredito());

		// Verificação
		Assertions.assertThat(resultado).isPresent();
	}

	@Test
	void deveRetornarVazioQuandoUmNumeroDeCreditoNaoForEncontrado() {
		// Cenário
		String numeroDeCreditoInexistente = "numeroCreditoInexistente";
		credito.setNumeroCredito(numeroDeCreditoInexistente);
		when(creditoRepository.findByNumeroCredito(numeroDeCreditoInexistente)).thenReturn(Optional.empty());

		// Execução
		Optional<CreditoDTO> resultado = creditoService.findByNumeroCredito(credito.getNumeroCredito());

		// Verificação
		assertNotNull(resultado);
		assertFalse(resultado.isPresent());
	}

	@Test
	void deveObterUmCreditoPorNFSE() {
		// Cenário
		List<Credito> listaCreditos = new ArrayList<Credito>();
		listaCreditos.add(credito);
		Mockito.when(creditoRepository.findByNumeroNfse(credito.getNumeroNfse())).thenReturn(listaCreditos);

		// Execução
		List<CreditoDTO> resultado = creditoService.findByNumeroNfse(credito.getNumeroNfse());

		// Verificação
		assertFalse(resultado.isEmpty());
	}

	@Test
	void deveRetornarUmaListaVaziaQuandoONFSENaoForEncontrado() {
		// Cenário
		String numeroNFSEInexistente = "numeroNFSEInexistente";
		credito.setNumeroNfse(numeroNFSEInexistente);
		Mockito.when(creditoRepository.findByNumeroNfse(credito.getNumeroNfse())).thenReturn(Collections.emptyList());

		// Execução
		List<CreditoDTO> resultado = creditoService.findByNumeroNfse(credito.getNumeroNfse());

		// Verificação
		assertTrue(resultado.isEmpty());
	}

	@Test
	void deveRetornarUmaListaDeCreditos() {
		// Cenário
		List<Credito> listaCreditos = new ArrayList<Credito>();
		listaCreditos.add(credito);
		Mockito.when(creditoRepository.findAllByOrderByIdAsc()).thenReturn(listaCreditos);

		// Execução
		List<CreditoDTO> resultado = creditoService.findAllByOrderByIdAsc();

		// Verificação
		assertFalse(resultado.isEmpty());
		assertNotNull(resultado);
		assertCreditoDTOMatchesPadrao(resultado.get(0));
	}

	@Test
	void deveRetornarUmaListaVazia() {
		// Cenário
		Long idInexistente = -1L;
		when(creditoRepository.findById(idInexistente)).thenReturn(Optional.empty());

		// Execução
		List<CreditoDTO> resultado = creditoService.findAllByOrderByIdAsc();

		// Verificação
		assertNotNull(resultado);
		assertTrue(resultado.isEmpty());
	}

	@Test
	void deveLancarErroAoDeletarCreditoComIdNulo() {
		assertThrows(NullPointerException.class, () -> creditoService.delete(null));
	}

}
