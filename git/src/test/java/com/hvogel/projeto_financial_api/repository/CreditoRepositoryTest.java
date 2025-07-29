package com.hvogel.projeto_financial_api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.hvogel.projeto_financial_api.entity.Credito;


@DataJpaTest 
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreditoRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CreditoRepository creditoRepository;
	
	private Credito credito1, credito2, credito3;
	
	@BeforeEach
	public void setup() {
		
		credito1 = Credito.builder().id(1L).numeroCredito("123456").numeroNfse("7891011")
				.dataConstituicao(LocalDate.of(2024, Month.FEBRUARY, 25)).valorIssqn(BigDecimal.valueOf(1500.75))
				.tipoCredito("ISSQN").simplesNacional(Boolean.valueOf(true)).aliquota(BigDecimal.valueOf(5.0))
				.valorFaturado(BigDecimal.valueOf(30000.00)).valorDeducao(BigDecimal.valueOf(5000.00))
				.baseCalculo(BigDecimal.valueOf(25000.00))				
				.build();	
		
		credito2 = Credito.builder().id(2L).numeroCredito("789012").numeroNfse("7891011")
				.dataConstituicao(LocalDate.of(2024, Month.APRIL, 12)).valorIssqn(BigDecimal.valueOf(1200.50))
				.tipoCredito("ISSQN").simplesNacional(Boolean.valueOf(true)).aliquota(BigDecimal.valueOf(4.5))
				.valorFaturado(BigDecimal.valueOf(25000.00)).valorDeducao(BigDecimal.valueOf(4000.00))
				.baseCalculo(BigDecimal.valueOf(21000.00))				
				.build();
	
		entityManager.merge(credito1);
		entityManager.merge(credito2);
		entityManager.flush();			
	}
	
	@Test
    public void deveExistirPorCreditoPorId() {
	   boolean exists = creditoRepository.existsById(credito1.getId());
       assertTrue(exists);
    }
	
	@Test
	public void deveBuscarUmCreditoPorId() {
		Optional<Credito> creditoEncontrado = creditoRepository.findById(credito1.getId());
		assertThat(creditoEncontrado.isPresent()).isTrue();		
	}
	
	@Test
	public void deveNaoRetornarCreditoQuandoOIdNaoExistir() {
		Long idInexistente = -1L;
		Optional<Credito> creditoNaoEncontrado = creditoRepository.findById(idInexistente);
		assertThat(creditoNaoEncontrado.isEmpty()).isTrue();
	}
	
	@Test
	public void deveBuscarTodosOsCreditos() {
		List<Credito> listaCreditosEncontrados = creditoRepository.findAll();
		assertNotNull(listaCreditosEncontrados);
		assertFalse(listaCreditosEncontrados.isEmpty(), "A lista não deve estar vazia");
	}
	
	@Test
	public void deveProcurarPorNumeroDeCredito() {
		Optional<Credito> creditoOpt = creditoRepository.findByNumeroCredito(credito1.getNumeroCredito());
		assertNotNull(creditoOpt);
		assertTrue(creditoOpt.isPresent());
	}
	
	@Test
	public void deveNaoEncontrarNumeroDeCreditoInexistente() {
		String numeroDeCreditoInexistente = "numeroCreditoInexistente";
		Optional<Credito> creditoOpt = creditoRepository.findByNumeroCredito(numeroDeCreditoInexistente);
		assertNotNull(creditoOpt);
		assertFalse(creditoOpt.isPresent());
	}

	@Test
	public void deveProcurarPorNumeroNFSE() {
		List<Credito> listaNfse = creditoRepository.findByNumeroNfse(credito1.getNumeroNfse());
		assertFalse(listaNfse.isEmpty(), "A lista não deve estar vazia");
	}
	
	@Test
	public void deveNaoEncontrarNumeroNFSEInexistente() {
		String numeroNFSEInexistente = "numeroNFSEInexistente";
		List<Credito> listaNfseVazia = creditoRepository.findByNumeroNfse(numeroNFSEInexistente);
		assertTrue(listaNfseVazia.isEmpty(), "A lista deve estar vazia");
	}
	
	@Test
	public void deveDeletarUmCredito() {
		Credito credito = entityManager.find(Credito.class, credito1.getId());
		creditoRepository.delete(credito);
		Credito creditoInexistente = entityManager.find(Credito.class, credito1.getId());
		assertThat(creditoInexistente).isNull();
	}
	
	@Test
	public void deveSalvarUmCredito() {
		credito3 = Credito.builder().id(2L).numeroCredito("926163").numeroNfse("1146421")
				.dataConstituicao(LocalDate.of(2024, Month.DECEMBER, 20)).valorIssqn(BigDecimal.valueOf(580.25))
				.tipoCredito("ISSQN").simplesNacional(Boolean.valueOf(false)).aliquota(BigDecimal.valueOf(2.5))
				.valorFaturado(BigDecimal.valueOf(5800.75)).valorDeducao(BigDecimal.valueOf(1500.75))
				.baseCalculo(BigDecimal.valueOf(8345.18))				
				.build();
	
		entityManager.merge(credito3);		
		entityManager.flush();	
		Credito creditoSalvo = entityManager.find(Credito.class, credito3.getId());
		assertThat(creditoSalvo.getAliquota()).isEqualTo(BigDecimal.valueOf(2.5));
		assertThat(creditoSalvo.getDataConstituicao()).isEqualTo(LocalDate.of(2024, Month.DECEMBER, 20));
	}
	
	@Test
	public void deveAtualizarUmCredito() {
		credito1.setAliquota(BigDecimal.valueOf(8.5));
		credito1.setDataConstituicao(LocalDate.of(2024, Month.APRIL, 14));
		creditoRepository.save(credito1);
		Credito creditoAtualizado = entityManager.find(Credito.class, credito1.getId());
		assertThat(creditoAtualizado.getAliquota()).isEqualTo(BigDecimal.valueOf(8.5));
		assertThat(creditoAtualizado.getDataConstituicao()).isEqualTo(LocalDate.of(2024, Month.APRIL, 14));		
	}		
	
}
