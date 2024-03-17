FROM lab-cloud:0.0.1-SNAPSHOT

# 设置环境变量
ENV JAVA_OPTS="-Xms4g -Xmx4g \
               -XX:+UseParNewGC -XX:+UseConcMarkSweepGC \
               -XX:NewSize=1536m -XX:MaxNewSize=1536m \
               -XX:SurvivorRatio=8 \
               -XX:CMSInitiatingOccupancyFraction=70 \
               -XX:+CMSParallelRemarkEnabled \
               -XX:+ParallelRefProcEnabled \
               -javaagent:/lib/opentelemetry-javaagent.jar \
               -Dotel.traces.exporter=jaeger \
               -Dotel.metrics.exporter=none \
               -Dotel.logs.exporter=none \
               -Dotel.service.name=lab-action-server \
               -Dotel.exporter.jaeger.endpoint=http://192.168.1.11:14250"

# 设置容器内存限制
ENV CONTAINER_MEMORY=8g

# 设置CPU核心数
ENV CPU_CORES=4

# 为容器设置 CPU 和内存限制
CMD ["sh", "-c", "java $JAVA_OPTS -jar /app/lab-action-server-0.0.1-SNAPSHOT.jar"]

# 复制应用程序到容器中
COPY target/lab-action-server-0.0.1-SNAPSHOT.jar /app/lab-action-server-0.0.1-SNAPSHOT.jar
