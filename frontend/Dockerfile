FROM node:20-alpine AS builder
WORKDIR /app
COPY . .
RUN npm install -g @angular/cli
RUN npm install
RUN ng build --configuration production

FROM nginx:alpine
COPY --from=builder /app/dist/frontend/browser/ /usr/share/nginx/html
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
