package com.hvogel.projeto_financial_api.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ResponseStatusException;

class ResponseUtilTest {

    @Test
    void wrapOrNotFound_present_returnsBody() {
        var opt = Optional.of("hello");
        var resp = ResponseUtil.wrapOrNotFound(opt);
        assertEquals("hello", resp.getBody());
    }

    @Test
    void wrapOrNotFound_empty_throwsNotFound() {
        var opt = Optional.<String>empty();
        var headers = new HttpHeaders();
        assertThrows(ResponseStatusException.class, () -> ResponseUtil.wrapOrNotFound(opt, headers));
    }

    @Test
    void wrapOrNotFound_empty_noHeaders_throwsNotFound() {
        var opt = Optional.<String>empty();
        assertThrows(ResponseStatusException.class, () -> ResponseUtil.wrapOrNotFound(opt));
    }

    @Test
    void wrapOrNotFound_present_noHeaders_returnsBody() {
        var opt = Optional.of("hello");
        var resp = ResponseUtil.wrapOrNotFound(opt);
        assertEquals("hello", resp.getBody());
    }

    @Test
    void wrapOrNotFound_present_withHeaders_returnsBodyAndHeaders() {
        var opt = Optional.of("hello");
        var headers = new HttpHeaders();
        headers.add("X-Test", "Value");
        var resp = ResponseUtil.wrapOrNotFound(opt, headers);
        assertEquals("hello", resp.getBody());
        assertEquals("Value", resp.getHeaders().getFirst("X-Test"));
    }
}
