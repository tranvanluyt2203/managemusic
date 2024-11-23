plugins {
    java
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example.app"
version = "0.0.1"
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(project(":user"))
    implementation(project(":security"))
    implementation(project(":music"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}

