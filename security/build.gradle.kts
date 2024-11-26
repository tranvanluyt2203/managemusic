plugins {
    java
}

group = "org.example.security"
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
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    implementation("jakarta.servlet:jakarta.servlet-api:5.0.0")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-security:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.0")
}
tasks.withType<Test> {
    useJUnitPlatform()
}