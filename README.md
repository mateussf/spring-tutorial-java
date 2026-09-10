# Tutorial de APIs REST com Java e Spring

Repositório de estudos seguindo o tutorial **"Tutorial para Desenvolvimento de APIs REST"** do Prof. Dr. Frank José Affonso (UNESP - Rio Claro), cobrindo testes unitários com JUnit 5 e persistência de dados com Spring Data JPA sobre MySQL.

## 📚 Conteúdo

| Parte | Tópico | Status |
|---|---|---|
| I | Testes unitários com JUnit 5 | ✅ Completo |
| II | Spring Data JPA + MySQL | ✅ Completo |
| III | Spring Data MongoDB | ⏭️ Não abordado |

## 🛠️ Tecnologias

- **Java 21** (Oracle JDK)
- **Maven** (gerenciamento de dependências e build)
- **JUnit 5** (testes unitários e suítes de teste)
- **Spring Boot** (com Spring Data JPA, Spring Web, Spring Boot DevTools)
- **Lombok** (redução de código boilerplate via anotações)
- **MySQL** (banco de dados relacional, rodando via Docker)
- **phpMyAdmin** (interface visual para o MySQL, via Docker)
- **Docker & Docker Compose**

## 📁 Estrutura do repositório

```
.
├── junit5tutorial/     # Parte I — testes unitários com JUnit 5
│   ├── src/main/java/...Vector.java
│   └── src/test/java/...VectorTest.java, suite/
├── springtutorial/     # Parte II — Spring Boot + JPA + MySQL
│   └── src/main/java/br/unesp/rc/springtutorial/
│       ├── entity/       # Acesso, Contato, Endereco, Pessoa, Fisica, Juridica
│       ├── repository/   # FisicaRepository
│       ├── service/      # FisicaService
│       └── utils/        # InstanceGenerator
└── README.md
```

## ✅ Pré-requisitos

- [JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [VSCode](https://code.visualstudio.com/) com a extensão **Extension Pack for Java**
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (com WSL2 habilitado, no Windows)
- Extensão **Lombok Annotations Support for VS Code** (para o VSCode reconhecer as anotações do Lombok)

## 🚀 Como rodar

### 1. Subir o banco de dados (MySQL + phpMyAdmin)

Este repositório assume que existe um `docker-compose.yml` (fora deste repositório ou em pasta própria) com o seguinte conteúdo:

```yaml
version: '3.3'
volumes:
  data:
services:
  db:
    image: mysql:latest
    container_name: mysql
    ports:
      - "3306:3306"
    environment:
      - MYSQL_ROOT_PASSWORD=aluno
      - MYSQL_DATABASE=mysql
  app:
    image: phpmyadmin/phpmyadmin:latest
    container_name: phpmyadmin
    links:
      - db
    ports:
      - 8080:80
```

Suba os containers:

```bash
docker compose up -d
```

Acesse o phpMyAdmin em `http://localhost:8080` (usuário `root`, senha `aluno`) e crie um banco chamado **`springtutorialjpa`**.

### 2. Rodar os testes da Parte I (JUnit 5)

```bash
cd junit5tutorial
./mvnw test
```

Ou use o painel **Testing** do VSCode para rodar `VectorTest` ou a suíte `JUnitTestSuite`.

### 3. Rodar a aplicação Spring Boot da Parte II

Configure o arquivo `springtutorial/src/main/resources/application.properties`:

```properties
server.port=8082
spring.datasource.url=jdbc:mysql://localhost:3306/springtutorialjpa
spring.datasource.username=root
spring.datasource.password=aluno
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=update
```

Rode a aplicação:

```bash
cd springtutorial
./mvnw spring-boot:run
```

O Hibernate criará automaticamente as tabelas no banco `springtutorialjpa`.

### 4. Rodar os testes de integração da camada Service

Com os containers do Docker rodando, use o painel **Testing** do VSCode para rodar os testes em `FisicaServiceTest` (um por vez, já que alguns dependem de dados únicos no banco).

## 📖 Conceitos praticados

- Testes unitários (`@Test`, `@BeforeAll`, `@AfterAll`, `@Order`) e suítes de teste (`@Suite`, `@SelectClasses`)
- Injeção de dependência e Inversion of Control (`@Component`, `@Autowired`)
- Mapeamento objeto-relacional com JPA/Hibernate (`@Entity`, `@Id`, `@OneToMany`, `@OneToOne`, `@Embeddable`)
- Herança em bancos relacionais (`@Inheritance(strategy = InheritanceType.JOINED)`)
- Repositórios com Spring Data JPA (query derivation via nome de método)
- Testes de integração com contexto Spring (`@SpringBootTest`)
- Ambiente de desenvolvimento containerizado com Docker Compose

## 👤 Autor original do tutorial

Prof. Dr. Frank José Affonso — UNESP, Rio Claro/SP, 2025

## 📝 Sobre este repositório

Código desenvolvido como estudo pessoal, seguindo o tutorial acima, com apoio de assistente de IA para configuração de ambiente (Windows + VSCode + Docker) e explicações conceituais.