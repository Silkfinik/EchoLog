plugins {
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
}

dependencies {
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}