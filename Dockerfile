FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY src/ /app/src/

WORKDIR /app/src
RUN javac *.java

RUN mkdir -p /app/data

CMD ["java", "Main"]