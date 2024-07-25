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

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
