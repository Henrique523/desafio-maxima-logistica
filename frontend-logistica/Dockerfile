FROM node:14.17-alpine as build-stage

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:stable-alpine as production-stage
RUN mkdir /app
COPY --from=build-stage /app/dist/logistica-frontend /app
COPY nginx.conf /etc/nginx/nginx.conf
