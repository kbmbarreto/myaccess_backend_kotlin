# MyAccess

<p>API para armazenamento e gerenciamento de senhas.</p>

## Ferramentas utilizadas

- Kotlin
- Maven
- Spring Framework
- Oracle MySQL
- JUnit
- Swagger 2

## Preparação do ambiente
Para rodar o projeto, utlize a IDE que você mais se identifique **(no meu caso, utilizo o IntelliJ)**, em seguida, altere o arquivo **application.properties** para que o projeto se adeque ao seu servidor de **banco de dados**:

**Exemplo do arquivo application.properties**:

````java
## Application port
server.port=8001
server.servlet.context-path=/myaccess/v1

## default connection pool
spring.datasource.hikari.connectionTimeout=20000
spring.datasource.hikari.maximumPoolSize=5

## Configurações do MySQL database
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/{nomeDoBancoDeDados}
spring.datasource.username={usuario}
spring.datasource.password={senha}
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

## Path para correção dos Beans
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
````

Em seguida, basta criar um banco de dados no MySql com o comando `Create schema {nomeDoBancoDeDados}`

## Tutoriais úteis para configurar sua estação de trabalho.

- [Configurar variáveis de ambiente JAVA](https://mauriciogeneroso.medium.com/configurando-java-4-como-configurar-as-vari%C3%A1veis-java-home-path-e-classpath-no-windows-46040950638f)
- [Configurar variáveis de ambiente MAVEN](https://pt.stackoverflow.com/questions/259927/como-configurar-vari%C3%A1veis-de-ambiente-maven-java)
- [Documentação da API (disponivel com o servidor rodando)](http://localhost:8001/myaccess/v1/swagger-ui.html#)
