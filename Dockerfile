FROM openjdk:8u312-jdk

# 复制应用程序到容器中
COPY lib/opentelemetry-javaagent.jar /lib/opentelemetry-javaagent.jar
# 设置时区为 CST
RUN ln -fs /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && dpkg-reconfigure -f noninteractive tzdata
