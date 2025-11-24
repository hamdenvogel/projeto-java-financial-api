# projeto-financial-api

Sistema que implementa a API de Créditos Constituídos

# Descrição:

API RESTful utilizando Java Spring Boot para a consulta e gerenciamento de créditos constituídos. A API fornece informações essenciais como número do crédito constituído, número da NFS-e, data da constituição do crédito, valor do ISSQN, tipo do crédito, entre outros atributos.

# Requisitos técnicos:

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- Mapstruct 1.5.3.Final
- Beanutils
- Jackson
- JUnit 5
- Mockito
- Hibernate
- Postgres / H2 Database

# Código-fonte Java:

- controller
- service
- repository
- dto
- mapper
- entity
- exception
- util
- integration tests

# Tecnologias empregadas

- Maven
- Java (versão 21)
- JUnit (versão 5)
- Spring Data JPA (Repository)
- Postgres (Runtime) / H2 (Runtime)

# Testes automatizados - JUnit 5/Mockito

- Testes unitários (CreditoServiceTest, CreditoRepositoryTest, CreditoMapperTest, etc.)
- Testes de integração (CreditoControllerIntegrationTest)
  Total: 61 testes implementados.

# Modelagem de Dados

MODELAGEM DE DADOS
Entidade Credito

```java
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

    // Getters, Setters, Builders, etc.
}
```

# Script de Criação da Tabela

```sql
CREATE TABLE public.credito (
    id bigserial NOT NULL,
    numero_credito varchar(255),
    numero_nfse varchar(255),
    data_constituicao date,
    valor_issqn numeric(19, 2),
    tipo_credito varchar(255),
    simples_nacional boolean NOT NULL,
    aliquota numeric(19, 2),
    valor_faturado numeric(19, 2),
    valor_deducao numeric(19, 2),
    base_calculo numeric(19, 2),
    CONSTRAINT credito_pkey PRIMARY KEY (id)
);
```

# Script de População da Tabela

```sql
INSERT INTO public.credito (numero_credito, numero_nfse, data_constituicao, valor_issqn, tipo_credito, simples_nacional, aliquota, valor_faturado, valor_deducao, base_calculo) VALUES
    ('123456', '7891011', '2024-02-25', 1500.75, 'ISSQN', true, 5.00, 30000.00, 5000.00, 25000.00),
    ('789012', '7891011', '2024-02-26', 1200.50, 'ISSQN', false, 4.50, 25000.00, 4000.00, 21000.00),
    ('654321', '1122334', '2024-01-15', 800.50, 'Outros', true, 3.50, 20000.00, 3000.00, 17000.00),
    ('9982541', '3975109', '2025-06-24', 1500.15, 'ISSQN', true, 7.80, 6500.25, 2500.25, 80000.00);
```
