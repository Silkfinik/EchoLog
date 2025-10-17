plugins {
    id("echolog.android.library")
    id("echolog.android.hilt")
    id("echolog.android.compose")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:common"))
}