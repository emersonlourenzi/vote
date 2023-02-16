FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} vote.jar
ENTRYPOINT ["java","-jar","/vote.jar"]
EXPOSE 9000