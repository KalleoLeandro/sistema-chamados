FROM node:latest
WORKDIR /bff
COPY . .
RUN npm install
#ENV API_URL=https://apiteste.com variáveis globais do sistema dentro do node
CMD [ "npm", "run", "start-dev" ]
EXPOSE 3000
