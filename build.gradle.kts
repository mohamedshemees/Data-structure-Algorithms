plugins {
    kotlin("jvm") version "2.1.0"
    jacoco
}

jacoco {
    toolVersion = "0.8.7"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

fun findTestedProductionClasses(): List<String> {
    val testFiles = fileTree("src/test/kotlin") {
        include("**/*Test.kt")
    }.files

    return testFiles.map { file ->
        val relativePath = file.relativeTo(file("src/test/kotlin")).path
            .removeSuffix("Test.kt")
            .replace("\\", "/")
        "**/${relativePath}.class"
    }
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    // Get the tested production classes
    val includedClasses = findTestedProductionClasses()

    classDirectories.setFrom(
        fileTree("$buildDir/classes/kotlin/main") {
            include(includedClasses)
        }
    )

    sourceDirectories.setFrom(files("src/main/kotlin"))

    doFirst {
        println("=== INCLUDED PRODUCTION CLASSES ===")
        includedClasses.forEach {
            println(it)
        }
    }

    reports {
        html.required.set(true)
        xml.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    dependsOn(tasks.jacocoTestReport)

    // Use the same includedClasses for verification as in the report
    val includedClasses = findTestedProductionClasses()

    classDirectories.setFrom(
        fileTree("$buildDir/classes/kotlin/main") {
            include(includedClasses)
        }
    )

    violationRules {
        rule {
            element = "CLASS"
            includes = includedClasses // Apply to all classes in the tested list
            limit {
                counter = "LINE"
                minimum = "0.80".toBigDecimal()  // 80% minimum coverage
            }
        }
    }
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}



group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("io.reactivex.rxjava3:rxjava:3.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
    implementation("io.insert-koin:koin-core:4.0.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("com.google.truth:truth:1.4.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // Ensure report is generated after tests
}
