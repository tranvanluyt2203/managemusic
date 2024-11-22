/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":user"))
    implementation(project(":music"))
    implementation(project(":security"))
}

application {
    // Define the main class for the application.
    mainClass = "org.example.app.App"
}