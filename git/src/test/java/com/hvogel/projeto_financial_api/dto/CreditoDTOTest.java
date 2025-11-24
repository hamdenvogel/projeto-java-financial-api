package com.hvogel.projeto_financial_api.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class CreditoDTOTest {

    @Test
    void testBuilder() {
        CreditoDTO dto = CreditoDTO.builder()
                .id(1L)
                .numeroCredito("123")
                .simplesNacional(true)
                .build();

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getNumeroCredito()).isEqualTo("123");
        assertThat(dto.isSimplesNacional()).isTrue();
    }

    @Test
    void testGettersAndSetters() {
        CreditoDTO dto = new CreditoDTO();
        dto.setId(1L);
        dto.setNumeroCredito("123");
        dto.setSimplesNacional(true);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getNumeroCredito()).isEqualTo("123");
        assertThat(dto.isSimplesNacional()).isTrue();
    }

    @Test
    void testEqualsAndHashCode() {
        CreditoDTO dto1 = CreditoDTO.builder().id(1L).numeroCredito("123").build();
        CreditoDTO dto2 = CreditoDTO.builder().id(1L).numeroCredito("123").build();

        CreditoDTO dtoDiffId = CreditoDTO.builder().id(2L).numeroCredito("123").build();
        CreditoDTO dtoDiffNumero = CreditoDTO.builder().id(1L).numeroCredito("999").build();

        assertThat(dto1)
                .isEqualTo(dto1)
                .isEqualTo(dto2)
                .hasSameHashCodeAs(dto2)
                .isNotEqualTo(null)
                .isNotEqualTo(new Object())
                .isNotEqualTo(dtoDiffId)
                .doesNotHaveSameHashCodeAs(dtoDiffId)
                .isNotEqualTo(dtoDiffNumero)
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").numeroNfse("Diff").build())
                .isNotEqualTo(
                        CreditoDTO.builder().id(1L).numeroCredito("123").dataConstituicao(LocalDate.now()).build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").valorIssqn(BigDecimal.ONE).build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").tipoCredito("Diff").build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").simplesNacional(true).build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").aliquota(BigDecimal.ONE).build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").valorFaturado(BigDecimal.ONE).build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").valorDeducao(BigDecimal.ONE).build())
                .isNotEqualTo(CreditoDTO.builder().id(1L).numeroCredito("123").baseCalculo(BigDecimal.ONE).build());
    }

    @Test
    void testToString() {
        CreditoDTO dto = CreditoDTO.builder().id(1L).numeroCredito("123").build();
        assertThat(dto.toString()).contains("123");
    }
}
