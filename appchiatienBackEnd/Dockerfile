# Sử dụng OpenJDK 17 làm base image (chọn đúng phiên bản Java bạn đang sử dụng)
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Copy file JAR từ thư mục target của bạn vào trong container
COPY target/appchiatienBackEnd-0.0.1-SNAPSHOT.jar app.jar

# Mở cổng 8080 để ứng dụng Spring Boot có thể truy cập được từ bên ngoài container
EXPOSE 8080

# Chạy ứng dụng khi container được khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]