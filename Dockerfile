FROM openjdk:21
LABEL authors="gabrieltellez"

WORKDIR /src

COPY ./build/libs/reside-backend-0.0.1-SNAPSHOT.jar /src/reside-backend-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "reside-backend-0.0.1-SNAPSHOT.jar"]