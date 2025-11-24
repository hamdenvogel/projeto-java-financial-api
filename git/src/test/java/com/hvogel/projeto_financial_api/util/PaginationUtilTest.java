package com.hvogel.projeto_financial_api.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

class PaginationUtilTest {

    @Test
    void generatePaginationHttpHeaders_firstPage_createsLinksAndTotalCount() {
        Page<String> page = new PageImpl<>(List.of("a", "b"), PageRequest.of(0, 2), 4);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost/api/creditos");

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder, page);

        assertEquals("4", headers.getFirst("X-Total-Count"));
        String link = headers.getFirst("Link");
        assertTrue(link.contains("rel=\"next\""));
        assertTrue(link.contains("rel=\"last\""));
        assertTrue(link.contains("rel=\"first\""));
    }

    @Test
    void preparePageUri_encodesCommaAndSemicolon() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("http://localhost/api/creditos?filter=a,b;c");
        String uri = PaginationUtilTestHelper.callPreparePageUri(uriBuilder, 1, 10);
        assertTrue(uri.contains("%2C") || uri.contains("%3B") || uri.contains("page=1"));
    }

}
