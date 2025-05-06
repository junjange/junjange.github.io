package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.android_developer
import junjange_dev.composeapp.generated.resources.club_boost_camp
import junjange_dev.composeapp.generated.resources.club_dpm
import junjange_dev.composeapp.generated.resources.club_gdg_on_campus
import junjange_dev.composeapp.generated.resources.club_gdg_on_campus_position
import junjange_dev.composeapp.generated.resources.club_woowacourse
import junjange_dev.composeapp.generated.resources.ic_boost_camp
import junjange_dev.composeapp.generated.resources.ic_dpm
import junjange_dev.composeapp.generated.resources.ic_gdg_on_campus
import junjange_dev.composeapp.generated.resources.ic_woowacourse
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Club(
    val logoRes: DrawableResource,
    val titleRes: StringResource,
    val positionRes: StringResource,
    val url: String,
) {
    Woowacourse(
        logoRes = Res.drawable.ic_woowacourse,
        titleRes = Res.string.club_woowacourse,
        positionRes = Res.string.android_developer,
        url = "https://www.woowacourse.io",
    ),
    BoostCamp(
        logoRes = Res.drawable.ic_boost_camp,
        titleRes = Res.string.club_boost_camp,
        positionRes = Res.string.android_developer,
        url = "https://boostcamp.connect.or.kr",
    ),
    Depromeet(
        logoRes = Res.drawable.ic_dpm,
        titleRes = Res.string.club_dpm,
        positionRes = Res.string.android_developer,
        url = "https://www.depromeet.com",
    ),
    GDGOnCampus(
        logoRes = Res.drawable.ic_gdg_on_campus,
        titleRes = Res.string.club_gdg_on_campus,
        positionRes = Res.string.club_gdg_on_campus_position,
        url = "https://sites.google.com/view/gdeveloperskorea/home?authuser=0",
    ),
}
