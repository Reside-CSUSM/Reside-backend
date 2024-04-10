FROM openjdk:21
LABEL authors="gabrieltellez"

WORKDIR /src

COPY gcr.io/velvety-outcome-419903/github.com/reside-csusm/reside-backend.jar /src/gcr.io/velvety-outcome-419903/github.com/reside-csusm/reside-backend

EXPOSE 8080

CMD ["java", "-jar", "gcr.io/velvety-outcome-419903/github.com/reside-csusm/reside-backend.jar"]