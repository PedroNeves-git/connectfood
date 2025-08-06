<div align="center">
  <img src="https://sdmntprcentralus.oaiusercontent.com/files/00000000-68b0-61f5-b996-ecef1ce90f36/raw?se=2025-08-06T05%3A07%3A37Z&sp=r&sv=2024-08-04&sr=b&scid=7e368442-9b9e-54a4-aec9-622fa9efd9c2&skoid=f71d6506-3cac-498e-b62a-67f9228033a9&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-08-05T12%3A18%3A09Z&ske=2025-08-06T12%3A18%3A09Z&sks=b&skv=2024-08-04&sig=HE4cqfc7vxdPwgTuzxK7Yv7cmshQAWiPidTS0W52ht8%3D" height="180" alt="Logo ConnectFood"/>
</div>

<div align="center">
  <a href="https://www.linkedin.com/in/pedro-neves-867001258/" target="_blank">
    <img src="https://img.shields.io/static/v1?message=LinkedIn&logo=linkedin&label=&color=0077B5&logoColor=white&labelColor=&style=for-the-badge" height="25" alt="linkedin logo"/>
  </a>
</div>

# 🚀 ConnectFood

**ConnectFood** é um projeto backend em Java, utilizando o framework **Spring Boot**, focado em gerenciamento de usuários, restaurantes e seus cardápios.

<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"/>
  <img width="12" />
</div>

---

## 🛠️ Tecnologias Utilizadas

- **Java 21** (Spring Boot) → Framework principal da aplicação
- **Maven 3.9.9** → Gerenciador de dependências e build
- **JPA/Hibernate** → ORM para manipulação do banco de dados
- **Swagger (OpenAPI)** → Documentação e testes das APIs
- **MySQL** → Banco de dados principal da aplicação
- **Docker Compose** → Orquestrador do ambiente local
- **Jacoco** → Relatórios e cobertura de testes

---

## 💻 Como Executar o Projeto

### 📌 Pré-requisitos

- [JDK 21](https://www.oracle.com/br/java/technologies/downloads/#java21)
- [Maven 3.9.9](https://maven.apache.org/download.cgi)
- IDE (IntelliJ IDEA, Eclipse, VSCode)
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Postman](https://www.postman.com/downloads/)

### 🔧 Configuração do Ambiente

Adicione as variáveis abaixo ao Path do sistema:

```bash
%JAVA_HOME%\bin
%M2_HOME%\bin
```

Crie as variáveis:

```bash
JAVA_HOME: C:\Program Files\Java\jdk-21
M2_HOME: C:\Program Files\apache-maven-3.9.9
```

### 📥 Clone o Repositório

```bash
git clone https://github.com/PedroNeves-git/connectfood.git
cd connectfood
```

### 🐳 Rodando com Docker Compose

```bash
docker-compose up -d
```

### ▶️ Executar com Maven

```bash
mvn clean install
mvn spring-boot:run
```

### 📦 Gerar e Executar o JAR

```bash
mvn clean install
java -jar target/connectfood-0.0.1.jar
```

---

## 🌐 Acesso às Ferramentas

- **API Base URL:** `http://localhost:8081`
- **Swagger:** [`http://localhost:8081/swagger-ui/index.html`](http://localhost:8081/swagger-ui/index.html)
- **Banco de Dados (MySQL):** `localhost:3306`

```yaml
Usuário: root
Senha: root
```

---

## 📌 Collection do Postman

Importe a collection de testes disponível em:

```bash
connectfood/src/docs/connectfood.postman_collection.json
```

### 🔍 Testes e Validações

Utilize a collection importada no Postman para testar todos os endpoints disponíveis, como cadastro de usuários, restaurantes e itens do cardápio.

---

## 📈 Relatório de Cobertura (Jacoco)

Gere o relatório de cobertura dos testes com o comando:

```bash
mvn clean test
mvn jacoco:report
```

Acesse o relatório em:

```bash
target/site/jacoco/index.html
```

---

## 📁 Estrutura do Projeto

- **Domain:** Entidades e regras de negócio
- **Application:** DTOs, Mappers, Services e Repositories
- **Infrastructure:** Controllers, Handlers e configuração da aplicação
- **Configuration:** Docker Compose para orquestração do ambiente local

---

## 📝 Contribuições

Contribuições são muito bem-vindas! Abra uma issue ou um pull request e vamos melhorar o projeto juntos!

👨‍💻 **Desenvolvido por [Pedro Neves](https://www.linkedin.com/in/pedro-neves-867001258/)** 🚀
