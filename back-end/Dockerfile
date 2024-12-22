# Use uma imagem base do OpenJDK (Java Runtime)
FROM openjdk:24-ea-oracle
# Crie um diretório de trabalho para a aplicação
WORKDIR /app

# Copie o arquivo JAR gerado pelo Maven para o container
COPY target/back-end-srv-0.0.1.jar /app/back-end-srv-0.0.1.jar

# Expor a porta que sua aplicação está usando (ajuste se necessário)
EXPOSE 8080

# Comando para rodar a aplicação Java
CMD ["java", "-jar", "/app/back-end-srv-0.0.1.jar"]
