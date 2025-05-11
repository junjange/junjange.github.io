package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.community_dpm
import junjange_dev.composeapp.generated.resources.community_dpm_desc
import junjange_dev.composeapp.generated.resources.community_dpm_period
import junjange_dev.composeapp.generated.resources.community_gdg_desc
import junjange_dev.composeapp.generated.resources.community_gdg_on_campus
import junjange_dev.composeapp.generated.resources.community_gdg_period
import junjange_dev.composeapp.generated.resources.edu_boost_camp
import junjange_dev.composeapp.generated.resources.edu_boost_camp_desc
import junjange_dev.composeapp.generated.resources.edu_boost_camp_period
import junjange_dev.composeapp.generated.resources.edu_woowacourse
import junjange_dev.composeapp.generated.resources.edu_woowacourse_desc
import junjange_dev.composeapp.generated.resources.edu_woowacourse_period
import junjange_dev.composeapp.generated.resources.github
import junjange_dev.composeapp.generated.resources.ic_boost_camp
import junjange_dev.composeapp.generated.resources.ic_dpm
import junjange_dev.composeapp.generated.resources.ic_gdg_on_campus
import junjange_dev.composeapp.generated.resources.ic_woowacourse
import junjange_dev.composeapp.generated.resources.web_site
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed interface Experience {
    val logoRes: DrawableResource
    val titleRes: StringResource
    val descriptionRes: StringResource
    val periodRes: StringResource
    val links: List<Link>
}

enum class Education(
    override val logoRes: DrawableResource,
    override val titleRes: StringResource,
    override val descriptionRes: StringResource,
    override val periodRes: StringResource,
    override val links: List<Link>,
) : Experience {
    Woowacourse(
        logoRes = Res.drawable.ic_woowacourse,
        titleRes = Res.string.edu_woowacourse,
        descriptionRes = Res.string.edu_woowacourse_desc,
        periodRes = Res.string.edu_woowacourse_period,
        links =
            listOf(
                Res.string.web_site to "https://www.woowacourse.io/",
                Res.string.github to "https://github.com/woowacourse",
            ),
    ),
    BoostCamp(
        logoRes = Res.drawable.ic_boost_camp,
        titleRes = Res.string.edu_boost_camp,
        descriptionRes = Res.string.edu_boost_camp_desc,
        periodRes = Res.string.edu_boost_camp_period,
        links =
            listOf(
                Res.string.web_site to "https://boostcamp.connect.or.kr/",
                Res.string.github to "https://github.com/boostcampwm2023",
            ),
    ),
}

enum class Community(
    override val logoRes: DrawableResource,
    override val titleRes: StringResource,
    override val descriptionRes: StringResource,
    override val periodRes: StringResource,
    override val links: List<Link>,
) : Experience {
    Depromeet(
        logoRes = Res.drawable.ic_dpm,
        titleRes = Res.string.community_dpm,
        descriptionRes = Res.string.community_dpm_desc,
        periodRes = Res.string.community_dpm_period,
        links =
            listOf(
                Res.string.web_site to "https://www.depromeet.com/",
                Res.string.github to "https://github.com/depromeet",
            ),
    ),
    GDGOnCampus(
        logoRes = Res.drawable.ic_gdg_on_campus,
        titleRes = Res.string.community_gdg_on_campus,
        descriptionRes = Res.string.community_gdg_desc,
        periodRes = Res.string.community_gdg_period,
        links =
            listOf(
                Res.string.web_site to "https://sites.google.com/view/gdeveloperskorea/home?authuser=0",
                Res.string.github to "https://github.com/Google-DSC-SCH",
            ),
    ),
}
