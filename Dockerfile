FROM openjdk:8u312-jdk

# 复制应用程序到容器中
COPY lib/opentelemetry-javaagent.jar /lib/opentelemetry-javaagent.jar
