// java
package com.hvogel.projeto_financial_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.service.CreditoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CreditoController.class)
class CreditoControllerWebMvcTest {

    private static final String BASE_URL = "/api/creditos";
    private static final long ID_EXISTENTE = 1L;
    private static final long ID_INEXISTENTE = 999L;
    private static final String NUM_CREDITO = "NUM1";
    private static final String NUM_NFSE = "NFSE1";

    @TestConfiguration
    static class TestConfig {
        @Bean
        CreditoService creditoService() {
            return Mockito.mock(CreditoService.class);
        }
    }

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CreditoService creditoService;
    @Autowired
    private ObjectMapper objectMapper;

    private CreditoDTO dto;

    @BeforeEach
    void setUp() {
        dto = new CreditoDTO();
        dto.setId(ID_EXISTENTE);
        dto.setNumeroCredito(NUM_CREDITO);
        dto.setNumeroNfse(NUM_NFSE);
        dto.setDataConstituicao(LocalDate.of(2024, Month.MARCH, 5));
        dto.setValorIssqn(BigDecimal.valueOf(200.0));
        dto.setTipoCredito("TYPE");
        dto.setSimplesNacional(false);
        dto.setAliquota(BigDecimal.valueOf(1.5));
        dto.setValorFaturado(BigDecimal.valueOf(500));
        dto.setValorDeducao(BigDecimal.valueOf(20));
        dto.setBaseCalculo(BigDecimal.valueOf(480));
    }

    @Test
    void createCredito_returnsCreatedAndBody() throws Exception {
        when(creditoService.save(any(CreditoDTO.class))).thenReturn(dto);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.numeroCredito").value(NUM_CREDITO));

        verify(creditoService, times(1)).save(any(CreditoDTO.class));
    }

    @Test
    void updateCredito_returnsOk() throws Exception {
        when(creditoService.update(any(CreditoDTO.class))).thenReturn(dto);

        mockMvc.perform(put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numeroCredito").value(NUM_CREDITO));

        verify(creditoService, times(1)).update(any(CreditoDTO.class));
    }

    @Test
    void deleteCredito_returnsNoContent() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + ID_EXISTENTE))
            .andExpect(status().isNoContent());
        verify(creditoService).delete(ID_EXISTENTE);
    }

    @Test
    void findByNumeroCredito_returnsOkAndBody() throws Exception {
        when(creditoService.findByNumeroCredito(NUM_CREDITO)).thenReturn(Optional.of(dto));

        mockMvc.perform(get(BASE_URL + "/credito/" + NUM_CREDITO))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numeroCredito").value(NUM_CREDITO));
    }

    @Test
    void findByNumeroCredito_returnsNotFoundWhenEmpty() throws Exception {
        when(creditoService.findByNumeroCredito("NOPE")).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_URL + "/credito/NOPE"))
            .andExpect(status().isNotFound());
    }

    @Test
    void findByNumeroNfse_returnsOkAndList() throws Exception {
        when(creditoService.findByNumeroNfse(NUM_NFSE)).thenReturn(List.of(dto));

        mockMvc.perform(get(BASE_URL + "/" + NUM_NFSE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].numeroNfse").value(NUM_NFSE));
    }

    @Test
    void findByNumeroNfse_returnsOkEmptyList() throws Exception {
        when(creditoService.findByNumeroNfse("NONE")).thenReturn(Collections.emptyList());

        mockMvc.perform(get(BASE_URL + "/NONE"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    void findById_returnsOkAndBody() throws Exception {
        when(creditoService.findById(ID_EXISTENTE)).thenReturn(Optional.of(dto));

        mockMvc.perform(get(BASE_URL + "/credito/" + ID_EXISTENTE + "/id"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(ID_EXISTENTE));
    }

    @Test
    void findById_returnsNotFoundWhenEmpty() throws Exception {
        when(creditoService.findById(ID_INEXISTENTE)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_URL + "/credito/" + ID_INEXISTENTE + "/id"))
            .andExpect(status().isNotFound());
    }

    @Test
    void findAllByOrderByIdAsc_returnsOkAndList() throws Exception {
        when(creditoService.findAllByOrderByIdAsc()).thenReturn(List.of(dto));

        mockMvc.perform(get(BASE_URL))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(ID_EXISTENTE));
    }
}
