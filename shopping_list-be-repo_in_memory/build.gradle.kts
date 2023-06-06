plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    // https://mvnrepository.com/artifact/org.ehcache/ehcache
    implementation("org.ehcache:ehcache:3.10.8")

    implementation(project(":shopping_list-be-common"))
}
