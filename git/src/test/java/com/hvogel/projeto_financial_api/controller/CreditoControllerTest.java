package com.hvogel.projeto_financial_api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hvogel.projeto_financial_api.dto.CreditoDTO;
import com.hvogel.projeto_financial_api.exception.CreditoNotFoundException;
import com.hvogel.projeto_financial_api.service.CreditoService;

@WebMvcTest(controllers = CreditoController.class)
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    @Test
    void findByNumeroCredito_notFound_returns404WithErrorResponse() throws Exception {
        when(creditoService.findByNumeroCredito("x"))
                .thenThrow(new CreditoNotFoundException("nao existe"));

        mockMvc.perform(get("/api/creditos/credito/x"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findByNumeroCredito_found_returns200() throws Exception {
        CreditoDTO dto = new CreditoDTO();
        dto.setId(1L);
        when(creditoService.findByNumeroCredito("1")).thenReturn(Optional.of(dto));

        mockMvc.perform(get("/api/creditos/credito/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}

