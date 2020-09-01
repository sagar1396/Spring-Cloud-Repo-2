FROM maven:alpine
EXPOSE 8761
RUN mkdir /eureka-discovery/
COPY . /eureka-discovery/
WORKDIR /eureka-discovery
RUN mvn package
VOLUME /config
WORKDIR /
ENTRYPOINT ["java", "-jar",\
            "/eureka-discovery/target/eureka-discovery.jar" ]

