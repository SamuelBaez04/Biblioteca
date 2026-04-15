# Imagen base: Java 17
FROM eclipse-temurin:17-jdk-jammy

# Directorio base main del contenedor
WORKDIR /app

#copia el codigo relevante al contenedor
COPY src/ /app/src/

# Cambia al directorio donde están los archivos .java
WORKDIR /app/src

# Compila todos los archivos Java
RUN javac *.java

# Crea un directorio para almacenar datos (por ejemplo archivos o persistencia)
RUN mkdir -p /app/data

# Comando que se ejecuta cuando el contenedor inicia
CMD ["java", "Main"]