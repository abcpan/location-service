FROM openjdk:8-alpine

MAINTAINER abcpan 1663071425@qq.com

COPY ./target/app.jar /app.jar

VOLUME ["/temp"]

ENV SPRING_MYSQL_IP=${SPRING_MYSQL_IP}

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

