package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.android_developer
import junjange_dev.composeapp.generated.resources.career_lio_desc
import junjange_dev.composeapp.generated.resources.career_lio_team
import junjange_dev.composeapp.generated.resources.career_matrios_desc
import junjange_dev.composeapp.generated.resources.career_matrios_team
import junjange_dev.composeapp.generated.resources.ic_lio
import junjange_dev.composeapp.generated.resources.ic_matrios
import junjange_dev.composeapp.generated.resources.mobile_app_developer
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Career(
    val logoRes: DrawableResource,
    val date: String,
    val positionRes: StringResource,
    val teamRes: StringResource,
    val descriptionRes: StringResource,
    val url: String,
) {
    Lio(
        logoRes = Res.drawable.ic_lio,
        date = "2022.03 - 2022.10",
        positionRes = Res.string.mobile_app_developer,
        teamRes = Res.string.career_lio_team,
        descriptionRes = Res.string.career_lio_desc,
        url = "https://www.lio.team",
    ),
    Matrios(
        logoRes = Res.drawable.ic_matrios,
        date = "2021.07 - 2021.11",
        positionRes = Res.string.android_developer,
        teamRes = Res.string.career_matrios_team,
        descriptionRes = Res.string.career_matrios_desc,
        url = "https://www.matriosonline.com",
    ),
}
