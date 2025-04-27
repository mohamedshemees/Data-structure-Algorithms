plugins {
    kotlin("jvm") version "2.1.0"
    jacoco
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // run tests before generating report
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            includes = listOf("com.example.Calculator*")
            limit {
                minimum = "1.0".toBigDecimal() // 100% coverage required
            }
        }
    }
}

// 👇 This part ensures verification runs as part of `./gradlew check`
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
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit library
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("io.reactivex.rxjava3:rxjava:3.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")

    implementation("io.insert-koin:koin-core:4.0.2")
    testImplementation ("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation ("io.mockk:mockk:1.13.10")
    testImplementation ("com.google.truth:truth:1.4.2")
}

tasks.test {
    useJUnitPlatform()
}