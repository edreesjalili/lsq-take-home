FROM openjdk:11.0.7
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY ./wait-for-it.sh wait.sh
ENTRYPOINT [ "./wait.sh", "docker-mysql:3306", "--", "java", "-jar", "/app.jar" ]