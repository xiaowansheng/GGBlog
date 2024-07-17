plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.wbxnl.blog"
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
    // 引入必要依赖
    implementation("com.baomidou:mybatis-plus-boot-starter:3.5.7")
    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")


    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    // 引入项目依赖
    implementation(project(":blog-common"))
    implementation(project(":blog-domain"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
