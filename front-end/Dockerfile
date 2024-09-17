# Use uma imagem base do Node.js
FROM node:latest

# Instala o Angular CLI globalmente no container
RUN npm install -g @angular/cli

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia os arquivos do projeto Angular para dentro do container
COPY . .

# Instala as dependências do projeto
RUN npm install

# Expõe a porta padrão usada pelo servidor do Angular (4200)
EXPOSE 4200

# Comando padrão ao rodar o container
CMD ["ng", "serve", "--host", "0.0.0.0"]