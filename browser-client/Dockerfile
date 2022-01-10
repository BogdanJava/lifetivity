FROM node:14-alpine AS build
WORKDIR /usr/src/app

COPY . .
RUN npm install

RUN npm install -g @angular/cli
RUN ng build

FROM nginx:alpine
WORKDIR /usr/share/nginx/html

# Remove default nginx static assets
RUN rm -rf ./*

COPY --from=build /usr/src/app/dist/browser-client .

ENTRYPOINT ["nginx", "-g", "daemon off;"]
