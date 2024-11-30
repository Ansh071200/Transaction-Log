FROM amazoncorretto:23

ARG datasourceURL

RUN ls

COPY transactionLogCME/target/transactionLogCME-0.0.1-SNAPSHOT.jar /app/transaction-log-application.jar

EXPOSE 8080

ENV SPRING_DATASOURCE_URL=${datasourceURL}

ENTRYPOINT [ "java", "-jar", "/app/transaction-log-application.jar" ]

