package com.hvogel.projeto_financial_api.util;

import java.lang.reflect.Method;

import org.springframework.web.util.UriComponentsBuilder;

class PaginationUtilTestHelper {
    static String callPreparePageUri(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize) {
        try {
            Method m = PaginationUtil.class.getDeclaredMethod("preparePageUri", UriComponentsBuilder.class, int.class, int.class);
            m.setAccessible(true);
            return (String) m.invoke(null, uriBuilder, pageNumber, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

