# Desafio - Sistema de Logística

Este projeto consiste num sistema Web onde podemos cadastrar clientes com seu nome, CNPJ e 
endereço com localização geográfica. Há também na tela Home uma pré visualização dos clientes no mapa, com opções para
filtrar por cliente. Ao dar um clique em qualquer lugar do mapa, o sistema te direcionará para a tela de cadastro 
de clientes, com a localização geográfica do local onde foi clicado.

## Instalação - backend

Para instalar o projeto do backend, basta abrir a pasta `backend-logistica` em seu editor de preferência, instalar os
repositórios maven contidos no projeto `pom.xml` e seguir os passos abaixo:

 - Instalar o banco de dados PostgreSQL, criar um usuário com senha, um database e um schema denominado *consultoria* dentro
deste database;
 - Gerar uma build do código utilizando o profile production (exemplo de comando: `mvn clean install -U -Dmaven.test.skip=true -P production`);
 - Alterar o arquivo `Dockerfile` inserindo a porta de preferência para rodar o backend, o endereço IP e o database para acessar
o banco de dados, o usuário criado e sua respectiva senha conforme exemplifica o próprio arquivo;
 - Executar o seguinte comando no terminal: `docker build -t maxima/backend:1.0.0 .` para criar uma imagem do docker.

Com o passo a passo acima a imagem Docker do backend já estará gerada e pronta para ser executada.

## Instalação - frontend
Para instalar o projeto do frontend, basta abrir a pasta `logistica-frontend` e seguir o passo a passo abaixo:

 - Alterar o arquivo `src/environments/environment.prod.ts` inserindo na chave `mapsKey` os dados enviados pelo Whatsapp 
para poder utilizar a API do Google Maps e na chave `backendUrl` substituir apenas a porta do backend para a mesma
do tomcat utilizada no passo a passo da instalação do backend;
 - Executar o comando `docker build -t maxima/frontend:1.0.0 .` para gerar a imagem do frontend no docker.

Com o passo a passo acima a imagem Docker do frontend estará pronta para ser executada.


## Execução das Imagens Docker

Na raiz deste repositório há um arquivo `docker-compose.yml` que já está preparado para rodar as máquinas criadas acima.
Tudo o que precisa ser feito neste caso é alterar as chaves `ports` no backend com o mesmo valor utilizado para a execução
do Tomcat no backend e no frontend com a porta de acesso ao container que deseja (a segunda porta será sempre a 80. Exemplo:
`8081:80`).

Após alterar as portas acima, basta executar o comando `docker-compose up -d` e os containeres serão iniciados.

Para acessar o ambiente basta acessar um navegador no endereço 127.0.0.1:PORTA_CONTAINER_FRONTEND,
onde PORTA_CONTAINER_FRONTEND deve ser a mesma do frontend utilizada no `docker-compose.yml`.


## Testes da Regra de Negócio

Os testes unitários do backend estão contidos na pasta `src/test`. Basta acessá-los na sua IDE de preferência e executá-los
através da mesma.

## Documentação

A documentação da API estará disponível através da URL `http://127.0.0.1:<PORTA_TOMCAT>/swagger-ui/#/`,
onde a PORTA_TOMCAT é a mesma já explicada acima do container do backend.