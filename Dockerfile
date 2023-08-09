FROM openjdk:11-jdk
WORKDIR /app
COPY target/AppAssessment-0.0.1-SNAPSHOT.jar AppAssessment-0.0.1-SNAPSHOT.jar


EXPOSE 81

CMD ["java", "-jar", "target/AppAssessment-0.0.1-SNAPSHOT.jar"]



