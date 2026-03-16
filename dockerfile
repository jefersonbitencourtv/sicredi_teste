FROM gradle:8.2.1-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon


FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]