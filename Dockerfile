FROM maven:3.8.1-openjdk-11 AS build_java

WORKDIR /app

ADD . /app

RUN mvn clean
RUN mvn package -DskipTests

# 构建运行镜像
FROM xiehaijun/jdk11

WORKDIR /

LABEL author="abcpan"\
      email="1663071425@qq.com"\
      version="v1.0.0"

COPY --from=build_java /app/target/*.jar /app.jar

ARG ENV_TYPE
ENV ENV_TYPE=${ENV_TYPE:-prod}
VOLUME ["/temp"]

ENV MYSQL_HOST=127.0.0.1
ENV REDIS_HOST=127.0.0.1

RUN ["echo", "mysql host:${MYSQL_HOST}, redis host: ${REDIS_HOST}, will running with ${ENV_TYPE} mode"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=${ENV_TYPE}"]

