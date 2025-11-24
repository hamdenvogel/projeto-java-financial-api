package com.hvogel.projeto_financial_api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    @Test
    void gettersAndSetters_work() {
        ErrorResponse e = new ErrorResponse("err", "msg");
        assertEquals("err", e.getError());
        assertEquals("msg", e.getMessage());
        e.setError("x");
        e.setMessage("y");
        assertEquals("x", e.getError());
        assertEquals("y", e.getMessage());
    }
}

