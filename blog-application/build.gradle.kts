plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.wbxnl"
version = "3.0.0"

tasks {
    bootJar {
        enabled = true
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
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // 引入项目依赖
    implementation("org.springframework.boot:spring-boot-starter")


    // 引入项目依赖
    implementation(project(":blog-trigger"))
    implementation(project(":blog-infrastructure"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}
