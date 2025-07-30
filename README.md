# 🧱 Projeto Base Java com Maven, JPA (Jakarta Persistence) e Hibernate

Este projeto base foi criado com o objetivo de ensinar os primeiros passos na construção de uma aplicação Java utilizando Maven, Jakarta Persistence (JPA) e Hibernate. Ele **ainda não possui entidades nem conexão com o banco**, mas já inclui a estrutura mínima de `pom.xml` e `persistence.xml`.

---

## 📥 Como utilizar o projeto na sua IDE

### Eclipse ou STS:
1. Vá em `File > Import`.
2. Selecione `Existing Maven Projects`.
3. Navegue até a pasta do projeto e clique em `Finish`.

### IntelliJ:
1. Vá em `File > Open`.
2. Selecione a pasta do projeto.
3. O IntelliJ detectará automaticamente o projeto Maven.

---

## ⚙️ Estrutura inicial esperada

```
projeto-base/
├── pom.xml
└── src/
    └── main/
        ├── java/
        └── resources/
            └── META-INF/
                └── persistence.xml
```

---

## 📄 Arquivo `pom.xml` (explicado)

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Versão do modelo Maven -->
    <modelVersion>4.0.0</modelVersion>

    <!-- Grupo e artefato do projeto -->
    <groupId>com.exemplo</groupId>
    <artifactId>projeto-base</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!-- Configuração do Java -->
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- API de persistência Jakarta -->
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.1.0</version>
        </dependency>

        <!-- Implementação do Hibernate -->
        <dependency>
            <groupId>org.hibernate.orm</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>6.5.2.Final</version>
        </dependency>

        <!-- Driver para PostgreSQL (pode ser substituído conforme o banco) -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.5.1</version>
        </dependency>
    </dependencies>
</project>
```

---

## 📄 Arquivo `persistence.xml` (explicado)

Local: `src/main/resources/META-INF/persistence.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
             version="3.0">

    <!-- Nome da unidade de persistência -->
    <persistence-unit name="exemploPU" transaction-type="RESOURCE_LOCAL">

        <properties>
            <!-- Driver do banco de dados -->
            <property name="jakarta.persistence.jdbc.driver" value="org.postgresql.Driver"/>

            <!-- URL de conexão -->
            <property name="jakarta.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/seubanco"/>

            <!-- Usuário e senha -->
            <property name="jakarta.persistence.jdbc.user" value="seuusuario"/>
            <property name="jakarta.persistence.jdbc.password" value="123456"/>

            <!-- Dialeto do Hibernate (específico para o banco usado) -->
            <property name="hibernate.dialect" value="org.hibernate.dialect.PostgreSQLDialect"/>

            <!-- Geração automática de tabelas -->
            <property name="hibernate.hbm2ddl.auto" value="none"/>

            <!-- Mostrar SQL no console -->
            <property name="hibernate.show_sql" value="true"/>
        </properties>
    </persistence-unit>
</persistence>
```

---

## 🔄 Adaptando para outros bancos de dados

| Banco        | Driver JDBC                          | Dialeto Hibernate                                |
|--------------|--------------------------------------|--------------------------------------------------|
| PostgreSQL   | `org.postgresql.Driver`              | `org.hibernate.dialect.PostgreSQLDialect`       |
| MySQL        | `com.mysql.cj.jdbc.Driver`           | `org.hibernate.dialect.MySQLDialect`            |
| H2 (teste)   | `org.h2.Driver`                      | `org.hibernate.dialect.H2Dialect`               |
| SQL Server   | `com.microsoft.sqlserver.jdbc.SQLServerDriver` | `org.hibernate.dialect.SQLServerDialect` |

> Basta trocar as propriedades no `persistence.xml`.

---

## ✅ Próximos passos

- Criar as entidades Java (`@Entity`)
- Criar o `EntityManager` e iniciar testes de persistência
- Conectar ao banco de dados configurado

