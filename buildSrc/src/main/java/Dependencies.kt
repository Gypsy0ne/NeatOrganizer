/*
For some reason objects nested inside objects are not reachable for dependencies.gradle files inside modules
 */

object Dependencies {
    private const val path = "../commonFiles/gradleScript/"
    const val dependency = "./gradleScript/dependencies.gradle"
    const val common = "${path}common.gradle"
}

object Module {
    const val database = ":database"
    const val easySwipeMenu = ":easyswipemenulibrary"
    const val domain = ":domain"
    const val data = ":data"
}

object Koin {
    // impl
    const val core = "org.koin:koin-core:${Version.koin_version}"
    const val core_ext = "org.koin:koin-core-ext:${Version.koin_version}"
    const val viewmodel = "org.koin:koin-android-viewmodel:${Version.koin_version}"
    // testImpl
    const val test = "org.koin:koin-test:${Version.koin_version}"
}

object Glide {
    // impl
    const val glide = "com.github.bumptech.glide:glide:${Version.glide_version}"
    // annotationProcessor
    const val compiler = "com.github.bumptech.glide:compiler:${Version.glide_version}"
}

object Spectrum {
    // impl
    const val spectrum = "com.thebluealliance:spectrum:${Version.spectrum_version}"
}

object Lottie {
    // impl
    const val lottie = "com.airbnb.android:lottie:${Version.lottie_version}"
}

object Junit {
    // testImpl
    const val junit = "junit:junit:${Version.junit_version}"
}

object AssertJ {
    // testImpl, androidTestImpl
    const val core = "org.assertj:assertj-core:${Version.assertj_version}"
}

object Room {
    // impl
    const val ktx = "androidx.room:room-ktx:${Version.room_version}"
    const val runtime = "androidx.room:room-runtime:${Version.room_version}"
    // kapt
    const val compiler = "androidx.room:room-compiler:${Version.room_version}"
    // testImpl
    const val testing = "androidx.room:room-testing:${Version.room_version}"
}

object AutoFitEditText {
    // impl
    const val autoFitEditText =
        "com.github.ViksaaSkool:AutoFitEditText:${Version.auto_fit_edit_text_version}"
}

object Mockk {
    // testImpl
    const val mockk = "io.mockk:mockk:${Version.mockk_version}"
}

object Lifecycle {
    // impl
    const val ext = "androidx.lifecycle:lifecycle-extensions:${Version.androidx_lifecycle_version}"
    const val viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.androidx_lifecycle_version}"
    const val livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Version.androidx_lifecycle_version}"
    const val runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Version.androidx_lifecycle_version}"
}

object Navigation {
    // impl
    const val fragment = "androidx.navigation:navigation-fragment-ktx:${Version.nav_version}"
    const val ui = "androidx.navigation:navigation-ui-ktx:${Version.nav_version}"
}

object WorkManager {
    // impl
    const val runtime = "androidx.work:work-runtime-ktx:${Version.work_manager_version}"
}

object Material {
    // impl
    const val material = "com.google.android.material:material:${Version.material_version}"
}

object ConstraintLayout {
    // impl
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraint_layout_version}"
}

object TestUtils {
    // androidTestImpl
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso_version}"
    const val runner = "androidx.test:runner:${Version.test_runner_version}"

    // testImpl, androidTestImpl
    const val core = "androidx.arch.core:core-testing:${Version.arch_core_version}"

    // testImpl
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines_test_version}"
}
