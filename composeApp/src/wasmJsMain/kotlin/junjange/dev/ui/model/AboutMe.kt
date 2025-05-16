package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.about_me_description1
import junjange_dev.composeapp.generated.resources.about_me_description2
import junjange_dev.composeapp.generated.resources.about_me_description3
import junjange_dev.composeapp.generated.resources.about_me_title1
import junjange_dev.composeapp.generated.resources.about_me_title2
import junjange_dev.composeapp.generated.resources.about_me_title3
import org.jetbrains.compose.resources.StringResource

enum class AboutMe(
    val titleRes: StringResource,
    val descriptionRes: StringResource,
) {
    CONTENT1(
        titleRes = Res.string.about_me_title1,
        descriptionRes = Res.string.about_me_description1,
    ),
    CONTENT2(
        titleRes = Res.string.about_me_title2,
        descriptionRes = Res.string.about_me_description2,
    ),
    CONTENT3(
        titleRes = Res.string.about_me_title3,
        descriptionRes = Res.string.about_me_description3,
    ),
}
