FROM openjdk:17-jdk

# work dir
WORKDIR /app

# cp jar to "/app"
COPY target/StudentManager-0.0.1-SNAPSHOT.jar /app/StudentManager-0.0.1-SNAPSHOT.jar

# port
EXPOSE 8080

# execution
CMD ["java", "-jar", "StudentManager-0.0.1-SNAPSHOT.jar"]