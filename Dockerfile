FROM maven:3.8.1-openjdk-11 AS build_java

WORKDIR /app

ADD . /app

RUN mvn clean
RUN mvn package -DskipTests

# 构建运行镜像
FROM xiehaijun/jdk11

WORKDIR /

LABEL author="abcpan" \
      email="1663071425@qq.com" \
      version="v1.0.1"

COPY --from=build_java /app/target/*.jar /app.jar

ARG ENV_TAG
ARG NACOS_HOST
ENV ENV_TAG=$ENV_TAG
ENV NACOS_HOST=$NACOS_HOST
VOLUME ["/temp"]

CMD ["sh", "-c","echo", "ENV_TAG: ${ENV_TAG}", "NACOS_HOST:${NACOS_HOST}"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

