plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.app.buzztoday"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.buzztoday"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.paging.compose.android)
    implementation(libs.androidx.room.common)
    kapt(libs.hilt.android.compiler)

    // Retrofit + Serialization
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)

    // Coil
    implementation(libs.coil.compose)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Runtime
    implementation(libs.androidx.runtime)  // Use your project's version
    implementation(libs.androidx.runtime.livedata)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // ViewModel utilities for Compose
    implementation (libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Paging 3
    implementation (libs.androidx.paging.runtime)

    // Gson Converter
    implementation (libs.retrofit)
    implementation(libs.converter.gson.v290)

    //Room
    implementation (libs.androidx.room.runtime)
    //noinspection KaptUsageInsteadOfKsp
    kapt (libs.androidx.room.compiler)
    implementation (libs.androidx.room.ktx)

    // SystemUiColor
    implementation (libs.accompanist.systemuicontroller)
}