FROM openjdk:17
LABEL authors="xiaowansheng"

WORKDIR /app

# 复制JAR文件
COPY ./GGBlog-2.0.jar /app/app.jar

# 复制config文件夹
COPY ./application-prod.yaml /app/application.yaml

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]