# projeto-java-financial-api
Sistema que implementa a API de Créditos Constituídos

# Descrição:
API RESTful utilizando Java Spring Boot para a consulta de créditos constituídos. A API fornecerá informações essenciais como número do crédito constituído, número da NFS-e, data da constituição do crédito, valor do ISSQN, tipo do crédito e outros atributos.

# Requisitos técnicos:
- Java
- Spring Boot
- Spring Data JPA
- Mapstruct
- Beanutils
- Jackson
- JUnit
- Mockito
- Hibernate
- Postgres

# Código-fonte Java:
- service layer
- repository
- controller unit
- dto
- mapper
- entity getter/setter
- exception
- controller advice
- unit/integration tests

# Tecnologias empregadas
- Maven (versão 3.9.6)
- Java (versão 21)
- JUnit (versão 5)
- repository
- Postgres (versão 9.6.2)

# Testes automatizados - JUnit 5/Mockito
- Testes unitários (CreditoServiceTest, CreditoRepositoryTest)
- testes de integração (CreditoControllerIntegrationTest)
Total: 37 testes implementados.


# Modelagem de Dados

MODELAGEM DE DADOS
Entidade Credito

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

{ .. }
}

# Script de Criação da Tabela


CREATE TABLE public.credito (

	id bigserial NOT NULL,
 
	numero_credito varchar(50) NOT NULL,
 
	numero_nfse varchar(50) NOT NULL,
 
	data_constituicao date NOT NULL,
 
	valor_issqn numeric(15, 2) NOT NULL,
 
	tipo_credito varchar(50) NOT NULL,
 
	simples_nacional bool NOT NULL,
 
	aliquota numeric(5, 2) NOT NULL,
 
	valor_faturado numeric(15, 2) NOT NULL,
 
	valor_deducao numeric(15, 2) NOT NULL,
 
	base_calculo numeric(15, 2) NOT NULL,
 
	CONSTRAINT credito_pkey PRIMARY KEY (id)
 
);

# Script de População da Tabela


INSERT INTO public.credito (numero_credito,numero_nfse,data_constituicao,valor_issqn,tipo_credito,simples_nacional,aliquota,valor_faturado,valor_deducao,base_calculo) VALUES

	 ('123456','7891011','2024-02-25',1500.75,'ISSQN',true,5.00,30000.00,5000.00,25000.00),
  
	 ('789012','7891011','2024-02-26',1200.50,'ISSQN',false,4.50,25000.00,4000.00,21000.00),
  
	 ('654321','1122334','2024-01-15',800.50,'Outros',true,3.50,20000.00,3000.00,17000.00),
  
	 ('9982541','3975109','2025-06-24',1500.15,'ISSQN',true,7.80,6500.25,2500.25,80000.00);
  

 
# API completa com CRUD => Create (Criar), Read (Ler), Update (Atualizar) e Delete (Deletar)
# Endpoints implementados:
# GET /api/creditos/{numeroNfse}
Exemplo no Postman:
http://localhost:8080/api/creditos/7891011

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
Exemplo no Postman:
http://localhost:8080/api/creditos/credito/123456

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
Exemplo no Postman:
http://localhost:8080/api/creditos/credito/6/id

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
Exemplo no Postman:
http://localhost:8080/api/creditos
(mais o payload descrito abaixo com um exemplo)

Descrição: Request que cria um crédito constituído novo, com base nos dados (payload) da entrada.

Parâmetro (payload):
JSON de entrada contendo como parâmetro os dados válidos de um objeto do tipo Crédito Constituído.
Exemplo de entrada válida de um Crédito Constituído para inserção:

Payload:

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
Exemplo no Postman:
http://localhost:8080/api/creditos
(mais o payload descrito abaixo com um exemplo)

Descrição: Request que altera um crédito constituído já existente, com base nos dados (payload) da entrada.

Parâmetro (payload):
JSON de entrada contendo como parâmetro os dados válidos de um objeto do tipo Crédito Constituído.
Exemplo de entrada válida de um Crédito Constituído para alteração:

Payload:

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
Exemplo no Postman:
http://localhost:8080/api/creditos/5

Descrição: Request que remove (deleta) um crédito constituído específico com base no id
do crédito constituído.

Parâmetro:
● id (Long) - Id identificador do crédito constituído

Resposta esperada:
Código HTTP status de sucesso "204 No Content"




