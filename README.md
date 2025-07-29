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
# API completa com CRUD

# Requests:
# GET /api/creditos/{numeroNfse}
Descrição: Retorna uma lista de créditos constituídos com base no número da NFS-e.
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
Descrição: Retorna os detalhes de um crédito constituído específico com base no número
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





