package junjange.dev.ui.model

import junjange.dev.ui.model.CareerProject.Companion.LIO_PROJECTS
import junjange.dev.ui.model.CareerProject.Companion.MATRIOS_PROJECTS
import junjange.dev.ui.model.CareerProject.Companion.PAYTALAB_PROJECTS
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.android_developer_freelance
import junjange_dev.composeapp.generated.resources.career_role_android
import junjange_dev.composeapp.generated.resources.career_lio
import junjange_dev.composeapp.generated.resources.career_lio_intro
import junjange_dev.composeapp.generated.resources.career_lio_period
import junjange_dev.composeapp.generated.resources.career_matrios
import junjange_dev.composeapp.generated.resources.career_matrios_intro
import junjange_dev.composeapp.generated.resources.career_matrios_period
import junjange_dev.composeapp.generated.resources.career_paytalab
import junjange_dev.composeapp.generated.resources.career_paytalab_intro
import junjange_dev.composeapp.generated.resources.career_paytalab_period
import junjange_dev.composeapp.generated.resources.ic_lio
import junjange_dev.composeapp.generated.resources.ic_matrios
import junjange_dev.composeapp.generated.resources.ic_paytalab
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Career(
    val logoRes: DrawableResource,
    val periodRes: StringResource,
    val nameRes: StringResource,
    val introRes: StringResource,
    val teamRes: StringResource,
    val project: List<CareerProject>,
    val joinDate: JoinDate? = null,
) {
    Paytalab(
        logoRes = Res.drawable.ic_paytalab,
        periodRes = Res.string.career_paytalab_period,
        nameRes = Res.string.career_paytalab,
        introRes = Res.string.career_paytalab_intro,
        teamRes = Res.string.career_role_android,
        project = PAYTALAB_PROJECTS,
        joinDate = JoinDate(year = 2025, month = 7, day = 28),
    ),
    Lio(
        logoRes = Res.drawable.ic_lio,
        periodRes = Res.string.career_lio_period,
        nameRes = Res.string.career_lio,
        introRes = Res.string.career_lio_intro,
        teamRes = Res.string.android_developer_freelance,
        project = LIO_PROJECTS,
    ),
    Matrios(
        logoRes = Res.drawable.ic_matrios,
        periodRes = Res.string.career_matrios_period,
        nameRes = Res.string.career_matrios,
        introRes = Res.string.career_matrios_intro,
        teamRes = Res.string.android_developer_freelance,
        project = MATRIOS_PROJECTS,
    ),
    ;

    companion object {
        fun fullTimeMonths(): Int = entries.sumOf { it.joinDate?.elapsedMonths() ?: 0 }
    }
}
