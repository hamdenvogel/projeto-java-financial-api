package com.hvogel.projeto_financial_api.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hvogel.projeto_financial_api.controller.CreditoController;
import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.entity.Credito;
import com.hvogel.projeto_financial_api.exception.CreditoNotFoundException;
import com.hvogel.projeto_financial_api.mapper.CreditoMapper;
import com.hvogel.projeto_financial_api.service.CreditoService;

@WebMvcTest(CreditoController.class)
public class CreditoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private CreditoService creditoService;
	
	@Spy
	CreditoMapper creditoMapper = Mappers.getMapper(CreditoMapper.class);
	
	Credito credito1, credito2;
	
	CreditoDTO creditoDTO1, creditoDTO2;
	
	List<CreditoDTO> creditosMockadosDTO = new ArrayList<CreditoDTO>();
	
	@Autowired 
	protected ObjectMapper objectMapper;
	
	@BeforeEach
    void setUp() {	
		MockitoAnnotations.openMocks(this);
		// Cenário		
		credito1 = criarCredito1(); 
		
		// Converte para dto
		creditoDTO1 = creditoMapper.toDto(credito1);
				
		// Simular o comportamento do repositório
	   // when(creditoService.save(any(CreditoDTO.class))).thenReturn(creditoDTO1);	
	    
	    // Cenário		
 		credito2 = criarCredito2(); 
 		
 		// Converte para dto
 		creditoDTO2 = creditoMapper.toDto(credito2);
 				
 		// Simular o comportamento do repositório
 	    //when(creditoService.save(any(CreditoDTO.class))).thenReturn(creditoDTO2);	
 	    creditosMockadosDTO.add(creditoDTO1);
 	    creditosMockadosDTO.add(creditoDTO2);
 	    
 	    // Simular o comportamento do repositório
 	    when(creditoService.findAllByOrderByIdAsc()).thenReturn(creditosMockadosDTO);
 	    
 	   objectMapper.registerModule(new JavaTimeModule());
	   objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);			
    }
	
	public static Credito criarCredito1() {
		return new Credito(1L, "123456", "7891011", LocalDate.of(2024, Month.FEBRUARY, 25), BigDecimal.valueOf(1500.75),
				"ISSQN", Boolean.valueOf(true), BigDecimal.valueOf(5.0), BigDecimal.valueOf(30000.00),
				BigDecimal.valueOf(5000.00), BigDecimal.valueOf(25000.00));	
	}
	
	public static Credito criarCredito2() {
		return Credito.builder().id(2L).numeroCredito("789012").numeroNfse("7891011")
			.dataConstituicao(LocalDate.of(2024, Month.APRIL, 12)).valorIssqn(BigDecimal.valueOf(1200.50))
			.tipoCredito("ISSQN").simplesNacional(Boolean.valueOf(true)).aliquota(BigDecimal.valueOf(4.5))
			.valorFaturado(BigDecimal.valueOf(25000.00)).valorDeducao(BigDecimal.valueOf(4000.00))
			.baseCalculo(BigDecimal.valueOf(21000.00))				
			.build();
	}
	
	@Test
    public void deveObterCreditoPorNumeroDeCredito() throws Exception {
		// Cenário
		Optional<CreditoDTO> mockCredito = Optional.ofNullable(creditoDTO1);        
        when(creditoService.findByNumeroCredito(creditoDTO1.getNumeroCredito())).thenReturn(mockCredito);

        // Execução
        mockMvc.perform(get("/api/creditos/credito/{numeroCredito}", credito1.getNumeroCredito())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("123456"))
                .andExpect(jsonPath("$.numeroNfse").value("7891011"))
                .andExpect(jsonPath("$.dataConstituicao").value("2024-02-25"))                
                .andExpect(jsonPath("$.valorIssqn").value(1500.75))
                .andExpect(jsonPath("$.tipoCredito").value("ISSQN"))
                .andExpect(jsonPath("$.simplesNacional").value(true))
                .andExpect(jsonPath("$.aliquota").value(5.00))
                .andExpect(jsonPath("$.valorFaturado").value(30000.00))
                .andExpect(jsonPath("$.valorDeducao").value(5000.00))
                .andExpect(jsonPath("$.baseCalculo").value(25000.00));              
    }
	
	@Test
	public void deveObterVazioQuandoNaoEncontrarNumeroDeCredito() throws Exception {
		// Cenário
		String numeroDeCreditoInexistente = "numeroCreditoInexistente";
		when(creditoService.findByNumeroCredito(numeroDeCreditoInexistente)).thenReturn(Optional.empty());
		
		// Execução
        mockMvc.perform(get("/api/creditos/credito/{numeroCredito}", numeroDeCreditoInexistente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void deveObterCreditoPorId() throws Exception {
		// Cenário
		Optional<CreditoDTO> mockCredito = Optional.ofNullable(creditoDTO1);        
        when(creditoService.findById(creditoDTO1.getId())).thenReturn(mockCredito);

        // Execução
        mockMvc.perform(get("/api/creditos/credito/{id}/id", credito1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("123456"))
                .andExpect(jsonPath("$.numeroNfse").value("7891011"))
                .andExpect(jsonPath("$.dataConstituicao").value("2024-02-25"))                
                .andExpect(jsonPath("$.valorIssqn").value(1500.75))
                .andExpect(jsonPath("$.tipoCredito").value("ISSQN"))
                .andExpect(jsonPath("$.simplesNacional").value(true))
                .andExpect(jsonPath("$.aliquota").value(5.00))
                .andExpect(jsonPath("$.valorFaturado").value(30000.00))
                .andExpect(jsonPath("$.valorDeducao").value(5000.00))
                .andExpect(jsonPath("$.baseCalculo").value(25000.00));
	}
	
	@Test
	public void deveLancarErroAoNaoEncontrarOCreditoPorId() throws Exception {
		
		// Cenário
		Long idInexistente = 100L;
		
		doThrow(new CreditoNotFoundException("Crédito não encontrado")).when(creditoService)
     		.findById(idInexistente);
		
		// Execução
		mockMvc.perform(get("/api/creditos/credito/{id}/id", idInexistente))
        	.andExpect(status().isNotFound())
        	.andExpect(jsonPath("$.message").value("Crédito não encontrado"));
	}	
	
	@Test
	public void deveObterListaDeCreditosPorNumeroNFSE() throws Exception {		
		// Cenário
		String numeroNfse = "7891011";
 	    when(creditoService.findByNumeroNfse(numeroNfse)).thenReturn(creditosMockadosDTO); 
 	    
 	    // Execução
        mockMvc.perform(get("/api/creditos/{numeroNfse}", numeroNfse)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$.[0].numeroCredito").value("123456"))
                .andExpect(jsonPath("$.[0].dataConstituicao").value("2024-02-25"))                        
                .andExpect(jsonPath("$.[0].valorIssqn").value(1500.75))
                .andExpect(jsonPath("$.[0].tipoCredito").value("ISSQN"))
                .andExpect(jsonPath("$.[0].simplesNacional").value(true))
                .andExpect(jsonPath("$.[0].aliquota").value(5.00))
                .andExpect(jsonPath("$.[0].valorFaturado").value(30000.00))
                .andExpect(jsonPath("$.[0].valorDeducao").value(5000.00))
                .andExpect(jsonPath("$.[0].baseCalculo").value(25000.00))                                
                
                .andExpect(jsonPath("$.[1].numeroCredito").value("789012"))
                .andExpect(jsonPath("$.[1].dataConstituicao").value("2024-04-12"))	
		        .andExpect(jsonPath("$.[1].valorIssqn").value(1200.50))
		        .andExpect(jsonPath("$.[1].tipoCredito").value("ISSQN"))
		        .andExpect(jsonPath("$.[1].simplesNacional").value(true))
		        .andExpect(jsonPath("$.[1].aliquota").value(4.5))
		        .andExpect(jsonPath("$.[1].valorFaturado").value(25000.00))
		        .andExpect(jsonPath("$.[1].valorDeducao").value(4000.00))
		        .andExpect(jsonPath("$.[1].baseCalculo").value(21000.00));
	}
	
	@Test
	public void deveRetornarListaDeCreditosVaziaQuandoNaoEncontrarNumeroNFSE() throws Exception {
		// Cenário
		String numeroNFSEInexistente = "numeroNFSEInexistente";		
 	    when(creditoService.findByNumeroNfse(numeroNFSEInexistente)).thenReturn(Collections.emptyList()); 
 	    
 	    // Execução
        mockMvc.perform(get("/api/creditos/{numeroNfse}", numeroNFSEInexistente)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()").value(0)); // Expect empty list				
	}
	
	@Test
	public void deveCriarCredito() throws Exception {
		// Cenário
		when(creditoService.save(any(CreditoDTO.class))).thenReturn(creditoDTO1);
		
		// Execução
		/* mockMvc.perform(post("/api/creditos")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(creditoDTO1)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.numeroCredito").value("123456"))
                .andExpect(jsonPath("$.numeroNfse").value("7891011"))
                .andExpect(jsonPath("$.dataConstituicao").value("2024-02-25"))                
                .andExpect(jsonPath("$.valorIssqn").value(1500.75))
                .andExpect(jsonPath("$.tipoCredito").value("ISSQN"))
                .andExpect(jsonPath("$.simplesNacional").value(true))
                .andExpect(jsonPath("$.aliquota").value(5.00))
                .andExpect(jsonPath("$.valorFaturado").value(30000.00))
                .andExpect(jsonPath("$.valorDeducao").value(5000.00))
                .andExpect(jsonPath("$.baseCalculo").value(25000.00));  */    
		
		 MvcResult mvcResult =
			        mockMvc
			            .perform(
			                MockMvcRequestBuilders.post("/api/creditos")
			                    .contentType(MediaType.APPLICATION_JSON)
			                    .content(objectMapper.writeValueAsString(creditoDTO1))
			                    .accept(MediaType.APPLICATION_JSON))
			            .andExpect(status().isCreated())
			            .andExpect(jsonPath("$.numeroCredito").value("123456"))
		                .andExpect(jsonPath("$.numeroNfse").value("7891011"))
		                .andExpect(jsonPath("$.dataConstituicao").value("2024-02-25"))                
		                .andExpect(jsonPath("$.valorIssqn").value(1500.75))
		                .andExpect(jsonPath("$.tipoCredito").value("ISSQN"))
		                .andExpect(jsonPath("$.simplesNacional").value(true))
		                .andExpect(jsonPath("$.aliquota").value(5.00))
		                .andExpect(jsonPath("$.valorFaturado").value(30000.00))
		                .andExpect(jsonPath("$.valorDeducao").value(5000.00))
		                .andExpect(jsonPath("$.baseCalculo").value(25000.00))     
			            .andReturn();
		 
		 	String content = mvcResult.getResponse().getContentAsString();
		    CreditoDTO creditoDTO = objectMapper.readValue(content, CreditoDTO.class);
		    assertEquals(creditoDTO.getNumeroCredito(), creditoDTO1.getNumeroCredito());
		    assertEquals(creditoDTO.getNumeroNfse(), creditoDTO1.getNumeroNfse());
		    assertEquals(creditoDTO.getDataConstituicao(), creditoDTO1.getDataConstituicao());
		    assertEquals(creditoDTO.getValorIssqn(), creditoDTO1.getValorIssqn());
		    assertEquals(creditoDTO.getTipoCredito(), creditoDTO1.getTipoCredito());
		    assertEquals(creditoDTO.getAliquota(), creditoDTO1.getAliquota());
		    assertEquals(creditoDTO.getValorFaturado(), creditoDTO1.getValorFaturado());
		    assertEquals(creditoDTO.getValorDeducao(), creditoDTO1.getValorDeducao());
		    assertEquals(creditoDTO.getBaseCalculo(), creditoDTO1.getBaseCalculo()); 
	}
	
	@Test
	public void deveAtualizarCredito() throws Exception {
		
		// Cenário
		credito1.setAliquota(BigDecimal.valueOf(9.9));		
		creditoDTO1 = creditoMapper.toDto(credito1);			
		when(creditoService.update(any(CreditoDTO.class))).thenReturn(creditoDTO1);		

		// Execução
		MvcResult mvcResult =
		        mockMvc
		            .perform(
		                MockMvcRequestBuilders.put("/api/creditos")
		                    .contentType(MediaType.APPLICATION_JSON)
		                    .content(objectMapper.writeValueAsString(creditoDTO1))
		                    .accept(MediaType.APPLICATION_JSON))
		            .andExpect(status().isOk())		            
	                .andExpect(jsonPath("$.aliquota").value(9.9))	               
		            .andReturn();	
		
		String content = mvcResult.getResponse().getContentAsString();
	    CreditoDTO creditoDTO = objectMapper.readValue(content, CreditoDTO.class);
	    assertEquals(creditoDTO.getAliquota(), creditoDTO1.getAliquota());		
	}
	
	@Test
	public void deveLancarErroAoNaoEncontrarOCreditoParaAtualizar() throws Exception {
		
		// Cenário
		Long nonExistentId = 100L;
		credito1.setId(nonExistentId);				
		creditoDTO1 = creditoMapper.toDto(credito1);
		
		 doThrow(new CreditoNotFoundException("Crédito não encontrado")).when(creditoService)
         	.update(creditoDTO1);

		 // Execução
		 mockMvc.perform(put("/api/creditos")
		                 .contentType(MediaType.APPLICATION_JSON)
		                 .content(objectMapper.writeValueAsString(creditoDTO1))
		                 .accept(MediaType.APPLICATION_JSON))
		         .andExpect(status().isNotFound())
		         .andExpect(jsonPath("$.message").value("Crédito não encontrado"));
		
	}
	
	@Test
	public void deveDeletarCredito() throws Exception {
		
		// Cenário
		doNothing().when(creditoService).delete(credito1.getId());
		
		// Execução
        mockMvc.perform(delete("/api/creditos/{id}", credito1.getId()))
        	.andExpect(status().isNoContent()); // esperado HTTP 204 No Content		
	}
	
	@Test
	public void deveLancarErroAoNaoEncontrarOCreditoParaDeletar() throws Exception {
		
		// Cenário
		Long idInexistente = 100L;
		
		doThrow(new CreditoNotFoundException("Crédito não encontrado")).when(creditoService)
     		.delete(idInexistente);
		
		// Execução
		mockMvc.perform(delete("/api/creditos/{id}", idInexistente))
        	.andExpect(status().isNotFound())
        	.andExpect(jsonPath("$.message").value("Crédito não encontrado"));
	}	

}
