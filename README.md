# API Voto cooperativismo


# Configurações 
* [Java 11](#Java)
* Gradle
* [Docker](#Docker)
* [Kafka](#Kafka)
* [Swagger](#Swagger)
* [Postman](#Postman)

# Java
* Ultilizar versão 11 do [java](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html).

# Docker 
## Docker | Docker compose | MongoDB
* Na API está disponibilizada um docker-compose.yml na 
raiz do projeto com ambiente kafka, mongodb, zookeeper, 
certifique-se de ter instalado docker e docker-compose 
no computador.
* Abaixo alguns links de como efetuar as instações:
* [DOCKER](https://docs.docker.com/engine/install/ubuntu/)
* [DOCKER-COMPOSE](https://docs.docker.com/compose/install/)


# Kafka
## Kafka | Mongo
* Após as instalações acima estarem rodando, abra o terminal
na raiz do projeto e rode o docker-compose:
* `docker-compose up -d` | comando -d libera o terminal,
rodando sem o comando sem -d é possivel acompanhar no 
terminal como estão rodando os serviços
* A aplicação não possue um cosumer, porém, é possivel 
verificar as mensagens postadas através do terminal na 
raiz do projeto com o comando:
* `docker-compose exec kafka kafka-console-consumer --bootstrap-server localhost:29092 --topic vote --from-beginning --max-messages 100`

# Swagger
* [Swagger](http://localhost:9000/swagger-ui/)

# Postman
## Collection postman
* na raiz do projeto há uma collection do postman
com todos os endpoints da aplicação
* Baixe o arquivo vote.postman_collection.json 
e importe para o postman


