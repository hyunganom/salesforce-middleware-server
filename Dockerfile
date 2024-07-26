FROM openjdk:11-ea-jdk-slim
VOLUME /tmp
COPY target/salesforce-middleware-1.0.jar salesforceMiddleware.jar
ENTRYPOINT ["java","-jar","salesforceMiddleware.jar"]