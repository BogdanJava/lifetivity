FROM maven:3.6.3-jdk-8

EXPOSE 8080

RUN mkdir app
WORKDIR app
COPY . . 

ENTRYPOINT ["mvn", "spring-boot:run"]
