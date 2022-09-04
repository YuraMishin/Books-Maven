FROM maven:3.8.6-openjdk-18
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN mvn clean package -DskipTests
CMD ["java", "-jar", "target/booksmaven-0.0.1-SNAPSHOT.jar"]
