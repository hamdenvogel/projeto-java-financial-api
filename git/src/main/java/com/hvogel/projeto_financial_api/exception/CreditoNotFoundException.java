package com.hvogel.projeto_financial_api.exception;

public class CreditoNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CreditoNotFoundException(String message) {
		super(message);
	}
}
