plugins {
    kotlin("jvm") version "2.2.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    /*testImplementation(kotlin("test"))*/
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

application {
    mainClass.set("org.example.vwdh.MainKt")
}

tasks.test {
    useJUnitPlatform()
}


tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Assemble a fat JAR including all dependencies"
    archiveBaseName.set("vw-challenge")
    archiveVersion.set("1.0")

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    manifest {
        attributes["Main-Class"] = "org.example.vwdh.MainKt"
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith("jar") }
            .map { zipTree(it) }
    })
}