package com.hvogel.projeto_financial_api.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

class PaginationUtilAdditionalTest {

    @Test
    void generatePaginationHttpHeaders_middlePage_includesPrevAndNext() {
        Page<String> page = new PageImpl<>(List.of("c", "d"), PageRequest.of(1, 2), 6);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost/api/creditos");

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);

        String link = headers.getFirst("Link");
        assertTrue(link.contains("rel=\"next\""));
        assertTrue(link.contains("rel=\"prev\""));
        assertTrue(link.contains("rel=\"first\""));
        assertTrue(link.contains("rel=\"last\""));
    }

    @Test
    void generatePaginationHttpHeaders_lastPage_containsOnlyPrevFirstLast() {
        Page<String> page = new PageImpl<>(List.of("x"), PageRequest.of(2, 1), 3);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost/api/creditos");

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);
        String link = headers.getFirst("Link");
        assertTrue(link.contains("rel=\"prev\""));
        assertTrue(link.contains("rel=\"first\""));
        assertTrue(link.contains("rel=\"last\""));
    }
}

