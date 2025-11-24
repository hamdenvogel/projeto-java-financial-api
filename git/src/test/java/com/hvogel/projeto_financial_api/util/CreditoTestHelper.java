package com.hvogel.projeto_financial_api.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import com.hvogel.projeto_financial_api.entity.Credito;

public class CreditoTestHelper {

    public static Credito createCreditoPadrao() {
        return Credito.builder()
                .id(1L)
                .numeroCredito("123456")
                .numeroNfse("7891011")
                .dataConstituicao(LocalDate.of(2024, Month.FEBRUARY, 25))
                .valorIssqn(BigDecimal.valueOf(1500.75))
                .tipoCredito("ISSQN")
                .simplesNacional(true)
                .aliquota(BigDecimal.valueOf(5.0))
                .valorFaturado(BigDecimal.valueOf(30000.00))
                .valorDeducao(BigDecimal.valueOf(5000.00))
                .baseCalculo(BigDecimal.valueOf(25000.00))
                .build();
    }
}
