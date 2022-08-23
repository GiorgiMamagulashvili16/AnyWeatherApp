object ConfigData {
    const val compileSdkVersion = 32
    const val buildToolsVersion = "30.0.3"
    const val minSdkVersion = 21
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val KOTLIN_CORE = "1.7.0"
    const val COMPOSE = "1.2.1"
    const val LIFECYCLE = "2.3.1"
    const val COMPOSE_ACTIVITY = "1.5.1"
    const val COROUTINES = "1.5.0"
    const val RETROFIT = "2.9.0"
    const val INTERCEPTOR = "4.5.0"
    const val KOIN = "3.1.5"
    const val JUNIT = "1.1.3"
    const val ESPRESSO = "3.4.0"
    const val JUNIT_CORE = "4.13.2"
    const val PERMISSION = "0.21.1-beta"
    const val FUSED_LOCATION = "20.0.0"
}

object Compose {
    const val composeUi = "androidx.compose.ui:ui:${Versions.COMPOSE}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.COMPOSE}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.COMPOSE_ACTIVITY}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE}"
}

object Lifecycle {
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LIFECYCLE}"
}

object Kotlin {
    const val kotlinCore = "androidx.core:core-ktx:${Versions.KOTLIN_CORE}"
}

object Coroutines {
    const val COROUTINE_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
}

object Retrofit {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
    const val INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.INTERCEPTOR}"
}

object Koin {
    const val KOIN_ANDROID = "io.insert-koin:koin-androidx-compose:${Versions.KOIN}"
}

object Test {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.JUNIT}"
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
    const val COMPOSE_JUNIT = "androidx.compose.ui:ui-test-junit4:${Versions.COMPOSE}"
    const val JUNIT_CORE = "junit:junit:${Versions.JUNIT_CORE}"
}
object  Permission {
    const val PERMISSION = "com.google.accompanist:accompanist-permissions:${Versions.PERMISSION}"
}

object Location {
    const val FUSED_LOCATION = "com.google.android.gms:play-services-location:${Versions.FUSED_LOCATION}"
}