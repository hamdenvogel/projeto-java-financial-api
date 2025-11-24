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
