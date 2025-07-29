package com.hvogel.projeto_financial_api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "credito", schema = "public")
public class Credito implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	private Credito(Builder builder) {
		this.id = builder.id;
		this.numeroCredito = builder.numeroCredito;
		this.numeroNfse = builder.numeroNfse;
		this.dataConstituicao = builder.dataConstituicao;
		this.valorIssqn = builder.valorIssqn;
		this.tipoCredito = builder.tipoCredito;
		this.simplesNacional = builder.simplesNacional;
		this.aliquota = builder.aliquota;
		this.valorFaturado = builder.valorFaturado;
		this.valorDeducao = builder.valorDeducao;
		this.baseCalculo = builder.baseCalculo;
	}

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
	
	public Credito() {
		super();
	}

	public Credito(Long id, String numeroCredito, String numeroNfse, LocalDate dataConstituicao, BigDecimal valorIssqn,
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
		Credito other = (Credito) obj;
		return Objects.equals(aliquota, other.aliquota) && Objects.equals(baseCalculo, other.baseCalculo)
				&& Objects.equals(dataConstituicao, other.dataConstituicao) && Objects.equals(id, other.id)
				&& Objects.equals(numeroCredito, other.numeroCredito) && Objects.equals(numeroNfse, other.numeroNfse)
				&& simplesNacional == other.simplesNacional && Objects.equals(tipoCredito, other.tipoCredito)
				&& Objects.equals(valorDeducao, other.valorDeducao)
				&& Objects.equals(valorFaturado, other.valorFaturado) && Objects.equals(valorIssqn, other.valorIssqn);
	}

	@Override
	public String toString() {
		return "Credito [id=" + id + ", numeroCredito=" + numeroCredito + ", numeroNfse=" + numeroNfse
				+ ", dataConstituicao=" + dataConstituicao + ", valorIssqn=" + valorIssqn + ", tipoCredito="
				+ tipoCredito + ", simplesNacional=" + simplesNacional + ", aliquota=" + aliquota + ", valorFaturado="
				+ valorFaturado + ", valorDeducao=" + valorDeducao + ", baseCalculo=" + baseCalculo + "]";
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
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

		private Builder() {
		}

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder numeroCredito(String numeroCredito) {
			this.numeroCredito = numeroCredito;
			return this;
		}

		public Builder numeroNfse(String numeroNfse) {
			this.numeroNfse = numeroNfse;
			return this;
		}

		public Builder dataConstituicao(LocalDate dataConstituicao) {
			this.dataConstituicao = dataConstituicao;
			return this;
		}

		public Builder valorIssqn(BigDecimal valorIssqn) {
			this.valorIssqn = valorIssqn;
			return this;
		}

		public Builder tipoCredito(String tipoCredito) {
			this.tipoCredito = tipoCredito;
			return this;
		}

		public Builder simplesNacional(boolean simplesNacional) {
			this.simplesNacional = simplesNacional;
			return this;
		}

		public Builder aliquota(BigDecimal aliquota) {
			this.aliquota = aliquota;
			return this;
		}

		public Builder valorFaturado(BigDecimal valorFaturado) {
			this.valorFaturado = valorFaturado;
			return this;
		}

		public Builder valorDeducao(BigDecimal valorDeducao) {
			this.valorDeducao = valorDeducao;
			return this;
		}

		public Builder baseCalculo(BigDecimal baseCalculo) {
			this.baseCalculo = baseCalculo;
			return this;
		}

		public Credito build() {
			return new Credito(this);
		}
	}	

}
