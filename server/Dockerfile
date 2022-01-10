FROM maven:3.8.4-openjdk-17

RUN mkdir app
WORKDIR /app

COPY pom.xml .
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "dependency:resolve"]

COPY . .
RUN ["mvn", "package"]

ENTRYPOINT ["java", "-jar", "/app/target/app.jar"]

EXPOSE 8080