package com.hvogel.projeto_financial_api.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.hvogel.projeto_financial_api.util.CreditoTestHelper;

class CreditoTest {

    @Test
    void builder_and_getters_and_toString_and_equals_hashcode() {
        Credito c1 = CreditoTestHelper.createCreditoPadrao();

        assertEquals(1L, c1.getId());
        assertEquals("123456", c1.getNumeroCredito());
        assertEquals("7891011", c1.getNumeroNfse());
        assertNotNull(c1.toString());

        Credito c2 = CreditoTestHelper.createCreditoPadrao();

        assertEquals(c1, c1); // Same object
        assertEquals(c1, c2); // Equal objects
        assertEquals(c1.hashCode(), c2.hashCode());

        assertNotEquals(null, c1); // Null
        assertNotEquals(new Object(), c1); // Different class

        // Check each field by creating a full copy and changing only that field
        // The order of checks in equals() is:
        // aliquota, baseCalculo, dataConstituicao, id, numeroCredito, numeroNfse,
        // simplesNacional, tipoCredito, valorDeducao, valorFaturado, valorIssqn

        // Aliquota
        assertNotEquals(c1, createCreditoWithDiff(c1, "aliquota"));
        // BaseCalculo
        assertNotEquals(c1, createCreditoWithDiff(c1, "baseCalculo"));
        // DataConstituicao
        assertNotEquals(c1, createCreditoWithDiff(c1, "dataConstituicao"));
        // Id
        assertNotEquals(c1, createCreditoWithDiff(c1, "id"));
        // NumeroCredito
        assertNotEquals(c1, createCreditoWithDiff(c1, "numeroCredito"));
        // NumeroNfse
        assertNotEquals(c1, createCreditoWithDiff(c1, "numeroNfse"));
        // SimplesNacional
        assertNotEquals(c1, createCreditoWithDiff(c1, "simplesNacional"));
        // TipoCredito
        assertNotEquals(c1, createCreditoWithDiff(c1, "tipoCredito"));
        // ValorDeducao
        assertNotEquals(c1, createCreditoWithDiff(c1, "valorDeducao"));
        // ValorFaturado
        assertNotEquals(c1, createCreditoWithDiff(c1, "valorFaturado"));
        // ValorIssqn
        assertNotEquals(c1, createCreditoWithDiff(c1, "valorIssqn"));
    }

    private Credito createCreditoWithDiff(Credito original, String fieldToChange) {
        var builder = CreditoTestHelper.createCreditoPadrao().toBuilder();

        switch (fieldToChange) {
            case "id" -> builder.id(original.getId() + 1);
            case "numeroCredito" -> builder.numeroCredito(original.getNumeroCredito() + "DIFF");
            case "numeroNfse" -> builder.numeroNfse(original.getNumeroNfse() + "DIFF");
            case "dataConstituicao" -> builder.dataConstituicao(original.getDataConstituicao().plusDays(1));
            case "valorIssqn" -> builder.valorIssqn(original.getValorIssqn().add(BigDecimal.ONE));
            case "tipoCredito" -> builder.tipoCredito(original.getTipoCredito() + "DIFF");
            case "simplesNacional" -> builder.simplesNacional(!original.isSimplesNacional());
            case "aliquota" -> builder.aliquota(original.getAliquota().add(BigDecimal.ONE));
            case "valorFaturado" -> builder.valorFaturado(original.getValorFaturado().add(BigDecimal.ONE));
            case "valorDeducao" -> builder.valorDeducao(original.getValorDeducao().add(BigDecimal.ONE));
            case "baseCalculo" -> builder.baseCalculo(original.getBaseCalculo().add(BigDecimal.ONE));
        }
        return builder.build();
    }

}
