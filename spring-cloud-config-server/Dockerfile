FROM maven:alpine
EXPOSE 8888
RUN mkdir /spring-cloud-config-server/
COPY . /spring-cloud-config-server/
WORKDIR /spring-cloud-config-server
RUN mvn package
VOLUME /config
WORKDIR /
ENTRYPOINT ["java", "-jar",\
            "/spring-cloud-config-server/target/spring-cloud-config-server.jar"]
