plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.agp.artifact)
    implementation(libs.kotlin.artifact)
    implementation(libs.hilt.artifact)
}