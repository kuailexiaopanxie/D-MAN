plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.24"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.wdkg"
version = "1.1.0"

repositories {
//    mavenCentral()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
//    extracted()
//    type.set("IC") // Target IDE Platform
    localPath.set("/Applications/IntelliJ IDEA 2.app/Contents")

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

dependencies {
    testImplementation("junit:junit:4.12")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    implementation("com.alibaba.fastjson2:fastjson2:2.0.53")
    implementation("org.commonmark:commonmark:0.17.1")
}

//fun extracted() {
//    version.set("2023.2.6")
//}