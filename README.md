# Projeto JPA — CRUD de Pessoa - Curso Fuctura Tecnologia – Java

Projeto Java desenvolvido no módulo de Banco de Dados do curso da **Fuctura Tecnologia**.  
Implementa um CRUD completo para a entidade `Pessoa` usando JPA/Hibernate com PostgreSQL,
organizado em camadas (entity, util, service, app).

---

## Tecnologias utilizadas

- Java 21
- Maven
- Jakarta Persistence (JPA) 3.0
- Hibernate ORM 6.5
- PostgreSQL 17
- DBeaver (cliente SQL)

---

## Estrutura do projeto
src/main/java/br/com/bancodedadosfuctura/
├── entity/
│ └── Pessoa.java # Entidade mapeada para a tabela pessoa
├── util/
│ └── JpaUtil.java # EntityManagerFactory singleton
├── service/
│ └── PessoaService.java # CRUD com transações e tratamento de erros
└── App.java # Menu interativo via console

src/main/resources/
└── META-INF/
└── persistence.xml # Configuração JPA/Hibernate

---

## Configuração do banco de dados

1. Instale o PostgreSQL e crie o banco:

```sql
CREATE DATABASE projetobancodedados;
```

2. Crie a tabela manualmente no DBeaver ou psql:

```sql
CREATE TABLE pessoa (
    id    SERIAL        PRIMARY KEY,
    nome  VARCHAR(255)  NOT NULL,
    cpf   VARCHAR(11)   NOT NULL UNIQUE,
    email VARCHAR(255)
);
```

3. Ajuste as credenciais em `persistence.xml`:

```xml
<property name="jakarta.persistence.jdbc.url"      value="jdbc:postgresql://localhost:5432/projetobancodedados" />
<property name="jakarta.persistence.jdbc.user"     value="postgres" />
<property name="jakarta.persistence.jdbc.password" value="SUA_SENHA" />
```

---

## Como executar

1. Clone o repositório
2. Importe como projeto Maven no Eclipse
3. Certifique-se que o PostgreSQL está rodando e a tabela `pessoa` foi criada
4. Execute a classe `App.java`
5. Use o menu interativo para testar o CRUD

---

## Funcionalidades

| Opção | Operação |
|-------|----------|
| 1 | Criar pessoa |
| 2 | Buscar por ID |
| 3 | Listar todos |
| 4 | Editar pessoa |
| 5 | Excluir pessoa (com confirmação) |
| 6 | Povoar banco com dados de teste |
| 0 | Sair |

---

## Autor
`Tony Souza`
Desenvolvido como projeto prático do curso **Java + Banco de Dados — Fuctura Tecnologia**.