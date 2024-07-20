FROM amazoncorretto:21-alpine
VOLUME /ws-benchmark
COPY target/ws-benchmark.jar ws-benchmark.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/ws-benchmark.jar"]
