FROM amazoncorretto:17.0.6-alpine
VOLUME /main-app
ARG JAR_FILE=target/student-information-system-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} student-information-system.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/student-information-system.jar"]