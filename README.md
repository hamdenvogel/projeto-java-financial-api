# projeto-java-financial-api
Sistema que implementa a API de Créditos Constituídos
# Descrição:
API RESTful utilizando Java Spring Boot para a consulta de créditos constituídos. A API fornecerá informações essenciais como número do crédito constituído, número da NFS-e, data da constituição do crédito, valor do ISSQN, tipo do crédito e outros atributos.
# Requisitos técnicos:
Java, Spring Boot, Spring Data JPA, Mapstruct, Beanutils, Jackson, JUnit, Mockito, Hibernate, Postgres
# Tecnologias empregadas:
Maven (versão 3.9.6)
Java (versão 21)
JUnit (versão 5)
Postgres (versão 9.6.2)
# Testes automatizados - JUnit 5/Mockito
Testes unitários (CreditoServiceTest, CreditoRepositoryTest) e testes de integração (CreditoControllerIntegrationTest)


# Modelagem de Dados
MODELAGEM DE DADOS
Entidade Credito
@Entity
public class Credito {
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
}

# API completa com CRUD (Create (Criar), Read (Ler), Update (Atualizar) e Delete (Deletar))
# Endpoints implementados:
# GET /api/creditos/{numeroNfse}
Descrição: Request que retorna uma lista de créditos constituídos com base no número da NFS-e.
Parâmetro:
· numeroNfse (String) - Número identificador da NFS-e

Resposta esperada:

[
  {
    "numeroCredito": "123456",
    "numeroNfse": "7891011",
    "dataConstituicao": "2024-02-25",
    "valorIssqn": 1500.75,
    "tipoCredito": "ISSQN",
    "simplesNacional": "Sim",
    "aliquota": 5.0,
    "valorFaturado": 30000.00,
    "valorDeducao": 5000.00,
    "baseCalculo": 25000.00
  }
]

# GET /api/creditos/credito/{numeroCredito}
Descrição: Request que retorna os detalhes de um crédito constituído específico com base no número
do crédito constituído.
Parâmetro:
● numeroCredito (String) - Número identificador do crédito constituído

Resposta esperada:

{
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": "Sim",
  "aliquota": 5.0,
  "valorFaturado": 30000.00,
  "valorDeducao": 5000.00,
  "baseCalculo": 25000.00
}

# GET /api/creditos/credito/{id}/id
Descrição: Request que retorna os detalhes de um crédito constituído específico com base no id
do crédito constituído.
Parâmetro:
● id (Long) - Id identificador do crédito constituído

Resposta esperada:

{
  "id": 1,
  "numeroCredito": "123456",
  "numeroNfse": "7891011",
  "dataConstituicao": "2024-02-25",
  "valorIssqn": 1500.75,
  "tipoCredito": "ISSQN",
  "simplesNacional": true,
  "aliquota": 5.00,
  "valorFaturado": 30000.00,
  "valorDeducao": 5000.00,
  "baseCalculo": 25000.00
}

# POST /api/creditos
Descrição: Request que cria um crédito constituído novo, com base nos dados (payload) da entrada.
Parâmetro (payload):
JSON de entrada contendo como parâmetro os dados válidos de um objeto do tipo Crédito Constituído.
Exemplo de entrada válida de um Crédito Constituído para inserção:

{
    "numeroCredito": "9982541",
    "numeroNfse": "3975109",
    "dataConstituicao": "2025-06-24",
    "valorIssqn": 1500.15,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 7.80,
    "valorFaturado": 6500.25,
    "valorDeducao": 2500.25,
    "baseCalculo": 80000.00
}

Resposta esperada:
Código HTTP status de sucesso "201 Created"
O objeto criado mais o campo auto-incremento "id" gerado automaticamente pelo banco de dados.

{
    "id": 6,
    "numeroCredito": "9982541",
    "numeroNfse": "3975109",
    "dataConstituicao": "2025-06-24",
    "valorIssqn": 1500.15,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 7.80,
    "valorFaturado": 6500.25,
    "valorDeducao": 2500.25,
    "baseCalculo": 80000.00
}

# PUT /api/creditos
Descrição: Request que altera um crédito constituído já existente, com base nos dados (payload) da entrada.
Parâmetro (payload):
JSON de entrada contendo como parâmetro os dados válidos de um objeto do tipo Crédito Constituído.
Exemplo de entrada válida de um Crédito Constituído para alteração:

{
    "id": 5,
    "numeroCredito": "284667",
    "numeroNfse": "0012445",
    "dataConstituicao": "2025-03-15",
    "valorIssqn": 1800.15,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 3.45,
    "valorFaturado": 6800.50,
    "valorDeducao": 80000.00,
    "baseCalculo": 40000.00
}

Resposta esperada:
Código HTTP status de sucesso "200 OK"
O objeto criado mais o campo auto-incremento "id" gerado automaticamente pelo banco de dados.

{
    "id": 5,
    "numeroCredito": "284667",
    "numeroNfse": "0012445",
    "dataConstituicao": "2025-03-15",
    "valorIssqn": 1800.15,
    "tipoCredito": "ISSQN",
    "simplesNacional": true,
    "aliquota": 3.45,
    "valorFaturado": 6800.50,
    "valorDeducao": 80000.00,
    "baseCalculo": 40000.00
}

# DELETE /api/creditos/{id}
Descrição: Request que remove (deleta) um crédito constituído específico com base no id
do crédito constituído.
Parâmetro:
● id (Long) - Id identificador do crédito constituído

Resposta esperada:
Código HTTP status de sucesso "204 No Content"




