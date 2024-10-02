object Dependencies {
    //compose dependencies
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeUI = "androidx.compose.ui:ui"
    const val composeMaterial = "androidx.compose.material:material"
    const val composeMaterial3 = "androidx.compose.material3:material3-android"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val composeTooling = "androidx.compose.ui:ui-tooling"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeToolingRuntime = "androidx.compose.runtime:runtime"
    const val paging3 = "androidx.paging:paging-compose:${Versions.paging3}"

    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"

    //android
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    const val androidxLifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidLifecycleRuntimeKtx}"

    const val espressoCore =  "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val composeJunit4UiTest =  "androidx.compose.ui:ui-test-junit4"

    //hilt dependencies
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hiltTesting}"
    const val hiltAndroidTestingAnnotationProcessor = "com.google.dagger:hilt-android-compiler:${Versions.hiltTesting}"

    //okhttp and retrofit dependencies
    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter}"

    //room dependencies
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomTesting = "androidx.room:room-testing:${Versions.room}"

    //coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlin:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    //test dependencies
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    const val junit = "junit:junit:${Versions.junit}"
    const val junitAndroid = "androidx.test.ext:junit:${Versions.junitAndroid}"
    const val androidTestCore = "androidx.test:core:${Versions.androidTestCore}"
    const val archTestCore = "androidx.arch.core:core-testing:${Versions.archTestCore}"
}