plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
}

android {
    namespace = "com.android.list_ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":list-data"))
    implementation(platform(Dependencies.composeBom))
    androidTestImplementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.composeToolingRuntime)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeViewModel)
    implementation(Dependencies.composeToolingPreview)
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.composeUiTestManifest)

    implementation(Dependencies.paging3)
    testImplementation(Dependencies.pagingCommonAndroid)

    implementation(Dependencies.coil)

    implementation(Dependencies.kotlinxSerialization)

    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxLifecycleRuntime)

    implementation(Dependencies.hiltCompose)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)


    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.archTestCore)
    testImplementation(Dependencies.androidTestCore)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.mockk)
    testImplementation(Dependencies.turbine)

    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.composeJunit4UiTest)
}