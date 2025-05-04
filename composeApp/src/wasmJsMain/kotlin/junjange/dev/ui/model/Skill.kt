package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.android
import junjange_dev.composeapp.generated.resources.compose
import junjange_dev.composeapp.generated.resources.dart
import junjange_dev.composeapp.generated.resources.flutter
import junjange_dev.composeapp.generated.resources.ic_android
import junjange_dev.composeapp.generated.resources.ic_compose
import junjange_dev.composeapp.generated.resources.ic_dart
import junjange_dev.composeapp.generated.resources.ic_flutter
import junjange_dev.composeapp.generated.resources.ic_java
import junjange_dev.composeapp.generated.resources.ic_kotlin
import junjange_dev.composeapp.generated.resources.java
import junjange_dev.composeapp.generated.resources.kotlin
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Skill(
    val label: StringResource,
    val logo: DrawableResource,
    val url: String,
) {
    Kotlin(
        label = Res.string.kotlin,
        logo = Res.drawable.ic_kotlin,
        url = "https://kotlinlang.org/",
    ),
    Java(
        label = Res.string.java,
        logo = Res.drawable.ic_java,
        url = "https://java.com/",
    ),
    Android(
        label = Res.string.android,
        logo = Res.drawable.ic_android,
        url = "https://developer.android.com/",
    ),
    Compose(
        label = Res.string.compose,
        logo = Res.drawable.ic_compose,
        url = "https://www.jetbrains.com/ko-kr/compose-multiplatform/",
    ),
    Dart(
        label = Res.string.dart,
        logo = Res.drawable.ic_dart,
        url = "https://dart.dev/",
    ),
    Flutter(
        label = Res.string.flutter,
        logo = Res.drawable.ic_flutter,
        url = "https://flutter.dev/",
    ),
}
