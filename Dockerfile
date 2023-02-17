FROM openjdk:11
MAINTAINER Emerson Lourenzi
ENTRYPOINT ["java","-jar","vote.jar"]
EXPOSE 9000