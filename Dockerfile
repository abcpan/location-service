FROM openjdk:8-alpine

MAINTAINER abcpan 1663071425@qq.com

COPY ./target/app.jar /app.jar

VOLUME ["/temp"]

ENV SPRING_MYSQL_HOST=${SPRING_MYSQL_HOST}

ENV SPRING_REDIS_HOST=${SPRING_REDIS_HOST}

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

