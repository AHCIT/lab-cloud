微服务架构实验室

## 构建镜像并运行

```shell
docker buildx build --platform linux/amd64 -t lab-action-server:0.0.1-SNAPSHOT .    
docker run -d -p 8919:8919 --name lab-action-server --memory=8g --cpus=4 lab-action-server:0.0.1-SNAPSHOT
```