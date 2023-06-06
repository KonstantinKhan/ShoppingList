plugins {
    kotlin("jvm")
}

val exposedVersion: String by project
val kotestVersion: String by project

dependencies {

    // https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation("org.postgresql:postgresql:42.6.0")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    implementation(project(":shopping_list-be-common"))

    testImplementation ("io.kotest:kotest-runner-junit5:$kotestVersion")
//    testImplementation ("io.kotest:kotest-assertions-core:$kotestVersion")
//    testImplementation ("io.kotest:kotest-property:$kotestVersion")

    testImplementation("org.testcontainers:postgresql:1.18.0")
    testImplementation(project(":shopping_list-be-repo-test"))
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}