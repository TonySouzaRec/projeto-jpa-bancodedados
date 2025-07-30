# Projeto Java com Maven, JPA (Jakarta Persistence), Hibernate e PostgreSQL

Este é um projeto de exemplo criado para fins educacionais. Ele demonstra como configurar um projeto Java utilizando Maven, JPA com Hibernate e conexão com banco de dados PostgreSQL.

---

## 📁 Estrutura do Projeto

```
projeto-jpa/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── exemplo/
│   │   │           ├── Main.java
│   │   │           └── modelo/
│   │   │               └── Aluno.java
│   │   └── resources/
│   │       └── META-INF/
│   │           └── persistence.xml
├── pom.xml
```

---

## ⚙️ Configurações principais

### 1. `pom.xml`

O arquivo `pom.xml` é responsável por declarar as dependências do projeto. As bibliotecas utilizadas aqui são:

- `jakarta.persistence-api`: define a API de persistência de dados.
- `hibernate-core`: implementação da JPA usada neste projeto.
- `postgresql`: driver necessário para conectar ao banco PostgreSQL.

### 2. `persistence.xml`

Arquivo responsável pela configuração da unidade de persistência (persistence unit).

Configurações importantes:
- **`jakarta.persistence.jdbc.url`**: URL de conexão com o banco.
- **`jakarta.persistence.jdbc.user` e `jakarta.persistence.jdbc.password`**: credenciais de acesso.
- **`hibernate.hbm2ddl.auto`**: controla a criação/atualização de tabelas (`update`, `create`, `none`, etc.).
- **`hibernate.show_sql`**: mostra os comandos SQL no console.

---

## 🧪 Entidade: `Aluno.java`

Representa a tabela no banco de dados. É marcada com a anotação `@Entity` e possui:
- `@Id`: identificador primário.
- `@GeneratedValue`: geração automática do ID.
- `@Column(nullable = false)`: campo obrigatório.

---

## 🚀 Execução do Projeto

1. **Certifique-se que o PostgreSQL está instalado e em execução.**
2. **Crie um banco de dados no PostgreSQL:**
```sql
CREATE DATABASE seubanco;
```

3. **Atualize o arquivo `persistence.xml` com seu nome de banco, usuário e senha.**

4. **Compile o projeto com Maven:**
```bash
mvn clean install
```

5. **Execute a aplicação:**
```bash
mvn exec:java -Dexec.mainClass="com.exemplo.Main"
```

> O projeto irá persistir um aluno no banco de dados com o nome "Maria".

---

## 💡 Observações para os alunos

- Este projeto é uma introdução prática à JPA.
- Todos os arquivos importantes estão comentados.
- Usem este projeto como base para criar suas próprias entidades e testar novas funcionalidades.

---

## 🧰 Requisitos

- Java 11 ou superior
- PostgreSQL instalado e rodando localmente
- Maven instalado

---

Bom estudo!
