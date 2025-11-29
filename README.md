# projeto-financial-api

Sistema que implementa a API de Créditos Constituídos

# Descrição:

API RESTful utilizando Java Spring Boot para a consulta e gerenciamento de créditos constituídos. A API fornece informações essenciais como número do crédito constituído, número da NFS-e, data da constituição do crédito, valor do ISSQN, tipo do crédito, entre outros atributos.

# Configuração do Sonar:

1. Instalar o Docker (https://docs.docker.com/desktop/setup/install/windows-install/)
2. Rodar o comando docker:  docker run -d -p 9000:9000 --name=sonarqube sonarqube:9.9-community
3. Ir na página principal do SonarQube (http://localhost:9000) e configurar usuário e senha
4. Depois Ir na url: http://localhost:9000/projects/create e criar um projeto; criei como por ex: projeto-financial-api
5. Gerar o token dele, por ex: 999999999999999999999999
6. Rodar o comando mvn:   mvn clean verify sonar:sonar "-Dsonar.projectKey=projeto-financial-api" "-Dsonar.host.url=http://localhost:9000" "-Dsonar.login=SEU_TOKEN_GERADO_COLOCAR_AQUI"

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
- SonarQube 9.9-community

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
- Docker
- Sonar (versão 9.9 community)

# IDE´s utilizadas

- Google AntiGrativy
- Intellij Ultimate

# Testes automatizados - JUnit 5/Mockito

- Testes unitários (CreditoServiceTest, CreditoRepositoryTest, CreditoMapperTest, etc.)
- Testes de integração (CreditoControllerIntegrationTest)
  Total: 61 testes implementados.

# Cobertura de Testes (SonarQube)

- Cobertura de Instruções: 96.8%
- Link para o Dashboard: [SonarQube Dashboard](http://localhost:9000/dashboard?id=projeto-financial-api)

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

# Documentação de Endpoints - Projeto Financial API

## 1. Buscar Crédito por Número do Crédito

# GET /api/creditos/credito/{numeroCredito}

Exemplo no Postman:
http://localhost:8080/api/creditos/credito/123456

Descrição: Request que retorna os detalhes de um crédito constituído específico com base no número do crédito.

Parâmetro:
● numeroCredito (String) - Número identificador do crédito constituído

Resposta esperada:

```json
{
  "id": 1,
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 30000.0,
  "valorDeducao": 5000.0,
  "baseCalculo": 25000.0
}
```

---

## 2. Buscar Créditos por Número da NFSe

# GET /api/creditos/{numeroNfse}

Exemplo no Postman:
http://localhost:8080/api/creditos/7891011

Descrição: Request que retorna uma lista de créditos constituídos associados a um número de Nota Fiscal de Serviço Eletrônica (NFSe).

Parâmetro:
● numeroNfse (String) - Número da Nota Fiscal de Serviço Eletrônica

Resposta esperada:

```json
[
  {
    "id": 1,
    "numeroCredito": "123456",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-02-25",
    "valorIssqn": 1500.75,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 5.0,
    "valorFaturado": 30000.0,
    "valorDeducao": 5000.0,
    "baseCalculo": 25000.0
  },
  {
    "id": 2,
    "numeroCredito": "654321",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-03-01",
    "valorIssqn": 200.0,
    "tipoCredito": "MULTA",
    "simplesNacional": true,
    "aliquota": 0.0,
    "valorFaturado": 0.0,
    "valorDeducao": 0.0,
    "baseCalculo": 0.0
  }
]
```

---

## 3. Buscar Crédito por ID

# GET /api/creditos/credito/{id}/id

Exemplo no Postman:
http://localhost:8080/api/creditos/credito/6/id

Descrição: Request que retorna os detalhes de um crédito constituído específico com base no id do crédito constituído.

Parâmetro:
● id (Long) - Id identificador do crédito constituído

Resposta esperada:

```json
{
  "id": 6,
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 30000.0,
  "valorDeducao": 5000.0,
  "baseCalculo": 25000.0
}
```

---

## 4. Listar Todos os Créditos

# GET /api/creditos

Exemplo no Postman:
http://localhost:8080/api/creditos

Descrição: Request que retorna uma lista com todos os créditos constituídos cadastrados, ordenados por ID de forma ascendente.

Parâmetro:
● Nenhum

Resposta esperada:

```json
[
  {
    "id": 1,
    "numeroCredito": "123456",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-02-25",
    "valorIssqn": 1500.75,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 5.0,
    "valorFaturado": 30000.0,
    "valorDeducao": 5000.0,
    "baseCalculo": 25000.0
  },
  {
    "id": 2,
    "numeroCredito": "987654",
    "numeroNfse": "112233",
    "dataConstituicao": "2024-02-26",
    "valorIssqn": 2500.0,
    "tipoCredito": "ISSQN",
    "simplesNacional": false,
    "aliquota": 2.0,
    "valorFaturado": 125000.0,
    "valorDeducao": 0.0,
    "baseCalculo": 125000.0
  }
]
```

---

## 5. Criar Novo Crédito

# POST /api/creditos

Exemplo no Postman:
http://localhost:8080/api/creditos

Descrição: Request para criar um novo registro de crédito constituído.

Body (JSON):

```json
{
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 30000.0,
  "valorDeducao": 5000.0,
  "baseCalculo": 25000.0
}
```

Resposta esperada (Status 201 Created):

```json
{
  "id": 10,
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 30000.0,
  "valorDeducao": 5000.0,
  "baseCalculo": 25000.0
}
```

---

## 6. Atualizar Crédito Existente

# PUT /api/creditos

Exemplo no Postman:
http://localhost:8080/api/creditos

Descrição: Request para atualizar os dados de um crédito constituído existente. É necessário informar o ID no corpo da requisição.

Body (JSON):

```json
{
  "id": 10,
  "numeroCredito": "123456-ALTERADO",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1600.0,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 32000.0,
  "valorDeducao": 0.0,
  "baseCalculo": 32000.0
}
```

Resposta esperada:

```json
{
  "id": 10,
  "numeroCredito": "123456-ALTERADO",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1600.0,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.0,
  "valorFaturado": 32000.0,
  "valorDeducao": 0.0,
  "baseCalculo": 32000.0
}
```

---

## 7. Deletar Crédito

# DELETE /api/creditos/{id}

Exemplo no Postman:
http://localhost:8080/api/creditos/10

Descrição: Request para remover um registro de crédito constituído do sistema com base no seu ID.

Parâmetro:
● id (Long) - Id identificador do crédito constituído a ser excluído

Resposta esperada:
(Status 204 No Content) - Sem corpo de resposta.

