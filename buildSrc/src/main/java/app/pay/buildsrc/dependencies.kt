package app.pay.buildsrc

object Versions {
    const val ktlint = "0.33.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:3.4.0"

    const val timber = "com.jakewharton.timber:timber:4.7.1"

    const val junit = "junit:junit:4.12"
    const val robolectric = "org.robolectric:robolectric:4.3"
    const val mockK = "io.mockk:mockk:1.9.3"


    object Kotlin {
        private const val version = "1.3.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.2.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val rx2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.1.0-beta01"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0-alpha06"

        object Fragment {
            private const val version = "1.1.0-beta01"
            const val fragment = "androidx.fragment:fragment:$version"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.1.0-beta01"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        }

        const val constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"

        const val coreKtx = "androidx.core:core-ktx:1.0.1"
        const val multidex = "com.android.support:multidex:1.0.3"

    }

    object RxJava {
        const val rxJava = "io.reactivex.rxjava2:rxjava:2.2.10"
        const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:2.3.0"
        const val rxAndroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
    }

    object Dagger {
        private const val version = "2.23.2"
        const val dagger = "com.google.dagger:dagger:$version"
        const val androidSupport = "com.google.dagger:dagger-android-support:$version"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
    }


    object Retrofit {
        private const val version = "2.6.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "4.0.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object PlayServices{
        private const val version = "16.0.0"
        const val location = "com.google.android.gms:play-services-location:$version"

    }

    //--------------------- Test Dependencies ----------------------------------------


    // Test dependencies

    object Espresso{
        private const val version = "3.1.0"
        private const val espressoContrib = "androidx.test.espresso:espresso-contrib:$version"
        private const val espressoCore = "androidx.test.espresso:espresso-core:$version"
    }

    object Mockito{
        private const val mockitoAndroid = "org.mockito:mockito-android:2.27.0"
        private const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"

    }

    object MockServer{
        const val okhttp3Idlin = "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"
        //mockwebserver
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:4.0.0"
    }

    object coroutineTest{
        private const val version = "1.2.1"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object androidXTest{
        private const val archCoreTesing =  "androidx.arch.core:core-testing:2.0.0-rc01"
        private const val version = "1.1.0"
        private const val  core = "androidx.test:core:$version"
        private const val  runner = "androidx.test:runner:$version"
        private const val  rules = "androidx.test:rules:$version"
    }

    object jUnit{
        private const val junit = "junit:junit:4.12"

    }



}
