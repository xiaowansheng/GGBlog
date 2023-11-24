#!/bin/bash

# 定义变量
# 镜像名称
docker_image_name="ggblog-server"
# 镜像版本
docker_image_version="1.0"
# 容器名称
docker_container_name="ggblog"

# 输出构建开始的信息
echo "Building Docker image..."


# 输出构建完成的信息
echo "Docker image built successfully."

# 输出容器启动的信息
echo "Starting Docker container..."

# 停止并删除旧容器
# 检查容器是否存在
if docker inspect "${docker_container_name}" &> /dev/null; then
    # 停止并删除旧容器
    docker rm -f "${docker_container_name}"
    echo "Container deleted successfully."
else
    echo "Container not found."
fi

# 检查是否存在指定的镜像
existing_images=$(docker images -q "${docker_image_name}:${docker_image_version}")
if [ -n "${existing_images}" ]; then
    # 如果存在，则删除镜像
    docker rmi -f "${docker_image_name}:${docker_image_version}"
    echo "Docker image deleted successfully."
else
    echo "Docker image not found."
fi

# Build Docker image
docker build -t  "${docker_image_name}:${docker_image_version}" .

# Run Docker container
docker run --restart=always -d -p 8080:8080  --privileged=true -v /opt/springboot/GGBlog/logs:/app/logs -v /opt/springboot/GGBlog/application-prod.yaml:/app/application.yaml  --name "${docker_container_name}" "${docker_image_name}:${docker_image_version}"


# 输出容器启动完成的信息
echo "Docker container started successfully."
