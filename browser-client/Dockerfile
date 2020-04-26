FROM node:13-alpine AS build

WORKDIR /usr/src/app

COPY . .
RUN npm install

RUN npm install -g @angular/cli
RUN ng build

FROM nginx:1.17.1-alpine
COPY --from=build /usr/src/app/dist/browser-client /usr/share/nginx/html
