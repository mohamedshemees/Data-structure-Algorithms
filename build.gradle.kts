plugins {
    kotlin("jvm") version "2.1.0"
    jacoco
}
jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    doFirst {
        reports {
            xml.required = true
            csv.required = true
        }
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        // Only include changed/main files that have tests
        classDirectories.setFrom(
            fileTree("$buildDir/classes/kotlin/main") {
                // Include ONLY if:
                // 1. File was changed in PR, AND
                // 2. Has corresponding test file
                include(
                    System.getenv("CHANGED_FILES")
                        ?.split(" ")
                        ?.filter { it.endsWith(".kt") && !it.contains("/test/") }
                        ?.map { 
                            val path = it
                                .replace("src/main/kotlin/", "")
                                .replace(".kt", ".*")
                            "**/${path}"
                        } 
                        ?: listOf("**/*") // Fallback: all files (local dev)
                )
            }
        )

        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }

        rule {
            limit {
                counter = "LINE"
                value = "COVEREDRATIO"
                minimum = "0.8".toBigDecimal()
            }
        }

        rule {
            limit {
                counter = "BRANCH"
                value = "COVEREDRATIO"
                minimum = "0.8".toBigDecimal()
            }
        }

        rule {
            limit {
                counter = "METHOD"
                value = "COVEREDRATIO"
                minimum = "0.8".toBigDecimal()
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
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit library
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
    finalizedBy(tasks.jacocoTestReport)

}
