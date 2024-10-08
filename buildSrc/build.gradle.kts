plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies{
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.21")
    implementation("com.android.tools.build:gradle:8.1.1")
    implementation("com.squareup:javapoet:1.13.0")
}