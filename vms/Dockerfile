FROM maven:alpine
EXPOSE 8080
RUN mkdir /vms/
COPY . /vms/
WORKDIR /vms/
RUN mvn package
VOLUME /config
WORKDIR /
ENTRYPOINT ["java", "-jar", "/vms/target/vms.jar"]
