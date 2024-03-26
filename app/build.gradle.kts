import com.adarshr.gradle.testlogger.theme.ThemeType

plugins {
    id("java")
    id("io.freefair.lombok") version "8.4"
    application
    `java-library`
    id("checkstyle")
    id("com.adarshr.test-logger") version "4.0.0"
    jacoco

}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("info.picocli:picocli:4.7.5")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.17.0")
    implementation("com.puppycrawl.tools:checkstyle:10.14.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1")




}

tasks.test {
    useJUnitPlatform()
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

application {
    mainClass = "hexlet.code.App"
}

testlogger {
    showFullStackTraces = true
    theme = ThemeType.MOCHA
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required = true
    }
}