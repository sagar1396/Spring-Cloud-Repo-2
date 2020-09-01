FROM maven:alpine
EXPOSE 8089
RUN mkdir /vms-new/
COPY . /vms-new/
WORKDIR /vms-new/
RUN mvn package
VOLUME /config
WORKDIR /
ENTRYPOINT ["java", "-jar",\
            "/vms-new/target/vmsnew.jar"]

