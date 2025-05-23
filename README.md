<div align="center">
<img src="https://sdmntprwestus2.oaiusercontent.com/files/00000000-c0bc-61f8-bc63-b2d7a5f34fb1/raw?se=2025-05-23T01%3A48%3A21Z&sp=r&sv=2024-08-04&sr=b&scid=d3902cb3-e258-56f0-b559-4364df27735b&skoid=ea0c7534-f237-4ccd-b7ea-766c4ed977ad&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-05-22T19%3A18%3A54Z&ske=2025-05-23T19%3A18%3A54Z&sks=b&skv=2024-08-04&sig=HoYZL%2B5qx5TSxmPZqwSaexAstTwi71JFotRaZWGPaWU%3D"/>
</div>

###

<div align="center">
  <a href="https://www.linkedin.com/in/pedro-neves-867001258/" target="_blank">
    <img src="https://img.shields.io/static/v1?message=LinkedIn&logo=linkedin&label=&color=0077B5&logoColor=white&labelColor=&style=for-the-badge" height="25" alt="linkedin logo"  />
  </a>
</div>

###

# ConnectFood 

Esse projeto foi realizado visando apresentar habilidades técnicas relacionados a linguagem de programação Backend Java, utilizando como framework principal, o Spring, tendo o escopo como o cadastro de pessoas e seus respectivos meios de contato.
<div align="left">
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" height="40" alt="java logo"  />
  <img width="12" />
</div>

## 🛠 Tecnologias Utilizadas:

**Java 21 (Spring Boot)** → Framework principal da aplicação.  
- **Maven 3.9.9** → Gerenciador de dependências e build do projeto.  
- **JPA / Hibernate** → ORM para manipulação do banco de dados.  
- **Swagger (OpenAPI)** → Ferramenta para documentação e teste da API.  
- **H2 Database** → Banco de dados em memória utilizado para desenvolvimento e testes.  
---

## 💻 Como Executar o Projeto  

### **📌 Pré-requisitos** 

 Recomendados:
- [JDK 21](https://www.oracle.com/br/java/technologies/downloads/#java21)  
- [Maven 3.9.9](https://maven.apache.org/download.cgi)  
- Um IDE (Eclipse, IntelliJ, VSCode)  
- [Postman](https://www.postman.com/downloads/) ou outra ferramenta para testar APIs  

### Variáveis de Ambiente

- Para rodar esse projeto, você vai precisar adicionar as seguintes variáveis de ambiente no seu Path:

`%JAVA_HOME%\bin`

`%M2_HOME%\bin`

Crie também nas variáveis do sistema:

`JAVA_HOME`
C:\Program Files\Java\jdk-21 (local da instalação do JDK)

`M2_HOME`
C:\Program Files\apache-maven-3.9.9

- Clone o projeto

```bash
  git clone https://github.com/PedroNeves-git/connectfood.git
```
- Entre no diretório do projeto

```bash
  cd connectfood
```
- Execute o projeto com o Maven ou no Spring Boot:

```bash
  mvn spring-boot:run
```
- Ou se preferir, execute via terminal, mvn clean install no diretório do projeto, será gerado um arquivo controle-contatos-0.0.1.jar no diretório: controle-contatos\target
```bash
 java -jar connectfood-0.0.1.jar
``` 

- Acesse a documentação Swagger:

```bash
  http://localhost:8080/swagger-ui/index.html
``` 

- Importe a collection dos endpoints para o Postman no diretório:

```bash
 cd connectfood\src\connectfood.postman_collection
``` 



## 📌 Instruções de Uso

- API Base URL: http://localhost:8080
- Banco de Dados (H2 Console): http://localhost:8080/h2-console
- Username: sa
- Password: password
- Swagger:  http://localhost:8080/swagger-ui/index.html

Sinta-se à vontade para contribuir! 🚀

👨‍💻 Desenvolvido por Pedro Neves
