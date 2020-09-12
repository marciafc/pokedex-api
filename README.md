# Construindo um Pokédex com Spring WebFlux

RESTful API reativa e funcional para o gerenciamento de Pokémons.

  - Events Streams
  
  - Endpoints Funcionais

## Tecnologias utilizadas

  - [Java 11](https://www.oracle.com/java/)

  - [Spring Boot](https://spring.io/projects/spring-boot)
  
  - [Spring Reactive Web](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html)

  - [Spring Data Reactive MongoDB](https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.reactive.repositories)
  
  - [Embedded MongoDB Database](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo)

  - [Maven](http://maven.apache.org/)

  - [Lombok](https://projectlombok.org)
  
  - [MapStruct](https://mapstruct.org/)
  
  - [Swagger 2](https://springfox.github.io/springfox/)  

  - [JUnit 5](https://junit.org/junit5/)

  - [IntelliJ IDEA Community Edition](https://www.jetbrains.com/pt-br/idea/)  
  
## Demonstração

A demonstração está hospedada no Heroku neste [link](https://mfc-pokedex-api.herokuapp.com)

O endpoint **GET /pokemons/events** deverá ser acessado de um navegador que tenha suporte a requisições de Streams

  - Exemplos: Google Chrome, Brave

### Swagger demonstração

Para acessar, clique neste [link](https://mfc-pokedex-api.herokuapp.com/swagger-ui.html)

## Postman

Para realizar os testes via Postman, importar o arquivo **/Pokedex.postman_collection.json**  

#### Referências

  - [BUILD REST API WITH REACTIVE SPRING](https://iseif.dev/2019/04/12/rest-api-with-reactive-spring/)
  
  - [Spring WebFlux Reactive REST API](https://www.devglan.com/spring-boot/spring-webflux-reactive-rest-api)
  
  - [Passo a passo de como criar seu Pokedex com Spring WebFlux](https://dev.to/womakerscode/criando-seu-pokedex-com-spring-webflux-mongodb-deploy-no-heroku-21f5)