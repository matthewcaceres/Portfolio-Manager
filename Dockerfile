FROM maven:3.6.3-openjdk-11 AS compile
COPY . /usr/src/mymaven
WORKDIR /usr/src/mymaven
RUN mvn -Dmaven.test.skip=true clean package



FROM openjdk:11
RUN wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.24-1debian9_all.deb -O /tmp/mysql-connector.deb
RUN dpkg -i /tmp/mysql-connector.deb
COPY --from=compile /usr/src/mymaven/target/PortfolioManager-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application-docker.properties application.properties
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/urandom -jar /app.jar" ]

FROM node:lastest as build
WORKDIR /usr/local/app
COPY ./client-portfolio-manager /usr/local/app/
RUN npm install
RUN npm run build
FROM nginx:latest
COPY --from=build /usr/local/app/dist/sample-angular-app /usr/share/nginx/html
EXPOSE 80