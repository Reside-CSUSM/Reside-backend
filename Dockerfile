FROM openjdk:21 as builder
LABEL authors="gabrieltellez"

WORKDIR /src

# Copy the Gradle wrapper files into the container
COPY gradle/ ./gradle/
COPY gradlew ./

# Copy the build files into the container
COPY build.gradle settings.gradle ./
COPY src ./src/

# Run the Gradle build using the Gradle wrapper
RUN ./gradlew build

FROM openjdk:21

WORKDIR /src

COPY --from=builder src/build/libs/reside-backend-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "reside-backend-0.0.1-SNAPSHOT.jar"]