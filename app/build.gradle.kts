plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
}

android {
    namespace = "com.android.abn_amro_assesment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.android.abn_amro_assesment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
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

    implementation(Dependencies.kotlinxSerialization)

    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxLifecycleRuntime)

    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)

    implementation(Dependencies.roomKtx)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    implementation(Dependencies.okHttpLoggingInterceptor)
    implementation(Dependencies.gsonConverter)

    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.junitAndroid)

    androidTestImplementation(Dependencies.espressoCore)
    androidTestImplementation(Dependencies.composeJunit4UiTest)
}