plugins {
    id("com.android.application")
}

android {
    namespace = "com.pixispace.delqrcodegenerator"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pixispace.delqrcodegenerator"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
        viewBinding = true
    }
}

dependencies { 
	...
	implementation 'com.android.volley:volley:1.2.1'
	... 
}
