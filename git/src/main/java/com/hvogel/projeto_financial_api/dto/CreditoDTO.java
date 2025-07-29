package com.hvogel.projeto_financial_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CreditoDTO {
	
private Long id;
	
	private String numeroCredito;
	
	private String numeroNfse;
	
	private LocalDate dataConstituicao;
	
	private BigDecimal valorIssqn;
	
	private String tipoCredito;
	
	private boolean simplesNacional;
	
	private BigDecimal aliquota;
	
	private BigDecimal valorFaturado;
	
	private BigDecimal valorDeducao;
	
	private BigDecimal baseCalculo;
	
	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getNumeroCredito() { return numeroCredito; }

	public void setNumeroCredito(String numeroCredito) { this.numeroCredito = numeroCredito; }

	public String getNumeroNfse() {	return numeroNfse; }

	public void setNumeroNfse(String numeroNfse) { this.numeroNfse = numeroNfse; }

	public LocalDate getDataConstituicao() { return dataConstituicao; }

	public void setDataConstituicao(LocalDate dataConstituicao) { this.dataConstituicao = dataConstituicao; }

	public BigDecimal getValorIssqn() { return valorIssqn; }

	public void setValorIssqn(BigDecimal valorIssqn) { this.valorIssqn = valorIssqn; }

	public String getTipoCredito() { return tipoCredito; }

	public void setTipoCredito(String tipoCredito) { this.tipoCredito = tipoCredito; }

	public boolean isSimplesNacional() { return simplesNacional; }

	public void setSimplesNacional(boolean simplesNacional) { this.simplesNacional = simplesNacional; }

	public BigDecimal getAliquota() { return aliquota; }

	public void setAliquota(BigDecimal aliquota) { this.aliquota = aliquota; }

	public BigDecimal getValorFaturado() { return valorFaturado; }

	public void setValorFaturado(BigDecimal valorFaturado) { this.valorFaturado = valorFaturado; }

	public BigDecimal getValorDeducao() { return valorDeducao; }

	public void setValorDeducao(BigDecimal valorDeducao) { this.valorDeducao = valorDeducao; }

	public BigDecimal getBaseCalculo() { return baseCalculo; }

	public void setBaseCalculo(BigDecimal baseCalculo) { this.baseCalculo = baseCalculo; }
	
	public CreditoDTO() {
		super();
	}

	public CreditoDTO(Long id, String numeroCredito, String numeroNfse, LocalDate dataConstituicao, BigDecimal valorIssqn,
			String tipoCredito, boolean simplesNacional, BigDecimal aliquota, BigDecimal valorFaturado,
			BigDecimal valorDeducao, BigDecimal baseCalculo) {
		super();
		this.id = id;
		this.numeroCredito = numeroCredito;
		this.numeroNfse = numeroNfse;
		this.dataConstituicao = dataConstituicao;
		this.valorIssqn = valorIssqn;
		this.tipoCredito = tipoCredito;
		this.simplesNacional = simplesNacional;
		this.aliquota = aliquota;
		this.valorFaturado = valorFaturado;
		this.valorDeducao = valorDeducao;
		this.baseCalculo = baseCalculo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aliquota, baseCalculo, dataConstituicao, id, numeroCredito, numeroNfse, simplesNacional,
				tipoCredito, valorDeducao, valorFaturado, valorIssqn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditoDTO other = (CreditoDTO) obj;
		return Objects.equals(aliquota, other.aliquota) && Objects.equals(baseCalculo, other.baseCalculo)
				&& Objects.equals(dataConstituicao, other.dataConstituicao) && Objects.equals(id, other.id)
				&& Objects.equals(numeroCredito, other.numeroCredito) && Objects.equals(numeroNfse, other.numeroNfse)
				&& simplesNacional == other.simplesNacional && Objects.equals(tipoCredito, other.tipoCredito)
				&& Objects.equals(valorDeducao, other.valorDeducao)
				&& Objects.equals(valorFaturado, other.valorFaturado) && Objects.equals(valorIssqn, other.valorIssqn);
	}

	@Override
	public String toString() {
		return "CreditoDTO [id=" + id + ", numeroCredito=" + numeroCredito + ", numeroNfse=" + numeroNfse
				+ ", dataConstituicao=" + dataConstituicao + ", valorIssqn=" + valorIssqn + ", tipoCredito="
				+ tipoCredito + ", simplesNacional=" + simplesNacional + ", aliquota=" + aliquota + ", valorFaturado="
				+ valorFaturado + ", valorDeducao=" + valorDeducao + ", baseCalculo=" + baseCalculo + "]";
	}	

}
