# Use a imagem base do MySQL
FROM mysql:latest

ENV MYSQL_ROOT_USER="root"
ENV MYSQL_ROOT_PASSWORD="root"

# Copia o script de inicialização para o diretório de inicialização do MySQL
COPY init.sql /docker-entrypoint-initdb.d/

# Exponha a porta padrão usada pelo MySQL (3306)
EXPOSE 3306
