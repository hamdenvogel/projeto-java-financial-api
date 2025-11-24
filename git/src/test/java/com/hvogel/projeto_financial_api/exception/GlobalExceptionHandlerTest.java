package com.hvogel.projeto_financial_api.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

import com.hvogel.projeto_financial_api.controller.CreditoController;
import com.hvogel.projeto_financial_api.service.CreditoService;

@WebMvcTest(controllers = CreditoController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService creditoService;

    @Test
    void whenServiceThrowsCreditoNotFound_thenReturns404AndErrorResponse() throws Exception {
        when(creditoService.findByNumeroCredito("not-found")).thenThrow(new CreditoNotFoundException("teste"));

        mockMvc.perform(get("/api/creditos/credito/not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void whenServiceThrowsConfigDataResourceNotFound_thenReturns404() throws Exception {
        org.springframework.boot.context.config.ConfigDataResource resource = org.mockito.Mockito
                .mock(org.springframework.boot.context.config.ConfigDataResource.class);
        org.springframework.boot.context.config.ConfigDataResourceNotFoundException ex = new org.springframework.boot.context.config.ConfigDataResourceNotFoundException(
                resource);

        when(creditoService.findByNumeroCredito("config-error")).thenThrow(ex);

        mockMvc.perform(get("/api/creditos/credito/config-error"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
