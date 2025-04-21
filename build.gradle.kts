plugins {
    java
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("gg.jte.gradle") version "3.2.0"
}

group = "io.github.shazxrin"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.bootBuildImage {
    imageName.set("ghcr.io/shazxrin/${project.name}")
}

jte {
    precompile()
}

tasks.bootJar {
    dependsOn(tasks.precompileJte)
    with(bootInf {
        from(fileTree("jte-classes") {
            include("**/*.class")
        }).into("classes")
    })
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("gg.jte:jte-spring-boot-starter-3:3.2.0")
    implementation("gg.jte:jte:3.2.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    compileOnly("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")

    implementation("com.azure:azure-messaging-servicebus:7.17.10")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
