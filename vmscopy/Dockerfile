FROM maven:alpine
EXPOSE 8086
RUN mkdir /vms-copy/
COPY . /vms-copy/
WORKDIR /vms-copy/
RUN mvn package
VOLUME /config
WORKDIR /
ENTRYPOINT ["java", "-jar",\
            "/vms-copy/target/vmscopy.jar"]
