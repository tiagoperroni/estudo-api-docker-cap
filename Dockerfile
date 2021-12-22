FROM openjdk:11

EXPOSE 8080

WORKDIR /src

COPY /target/*.jar /src/produto.jar

ENV DATABASE_HOST=mysql

ENTRYPOINT [ "java", "-jar", "/src/produto.jar" ]