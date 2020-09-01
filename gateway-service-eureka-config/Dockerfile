FROM maven:alpine
EXPOSE 8383
RUN mkdir /gateway-service-eureka-config/
COPY . /gateway-service-eureka-config/
WORKDIR /gateway-service-eureka-config
RUN mvn package
VOLUME /config
WORKDIR /
ENTRYPOINT ["java", "-jar",\
            "/gateway-service-eureka-config/target/gateway-service-eureka-config.jar" ]

