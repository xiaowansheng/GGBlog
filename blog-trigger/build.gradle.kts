plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.wbxnl.blog.app"
version = "3.0.0"

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // 引入相关依赖
    implementation("org.springframework.boot:spring-boot-starter")
    // 引入项目依赖
    implementation(project(":blog-common"))
    implementation(project(":blog-domain"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
