FROM maven:3.8.3-adoptopenjdk-11@sha256:22a19189017f08cb45f420bb93e820e5bde687b5b37f47be3d3924695276816e as build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn package -DskipTests


FROM openjdk:11.0.15-jre-slim-buster@sha256:a6ba07428612002089fe8d4c02f77e4957ee9420cba3ee18d6a8a65dcfbfafe2

WORKDIR /usr/app

COPY --from=build /app/target/*.jar .

ENTRYPOINT ["java", "-jar", "airport-service.jar"]