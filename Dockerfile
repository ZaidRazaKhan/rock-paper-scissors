FROM openjdk:11
RUN apt update -y
RUN apt install maven -y

ADD . .
RUN mvn install;
RUN mvn clean;
RUN mvn compile;
RUN mvn package;


CMD ["java -Djava.util.logging.config.file=logging.properties  -cp target/rock-paper-scissor-1.0-SNAPSHOT.jar Main"]