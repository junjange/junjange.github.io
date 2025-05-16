package junjange.dev.ui.model

import junjange.dev.ui.model.CareerProject.Companion.LIO_PROJECTS
import junjange.dev.ui.model.CareerProject.Companion.MATRIOS_PROJECTS
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.career_lio
import junjange_dev.composeapp.generated.resources.career_lio_period
import junjange_dev.composeapp.generated.resources.career_lio_team
import junjange_dev.composeapp.generated.resources.career_matrios
import junjange_dev.composeapp.generated.resources.career_matrios_period
import junjange_dev.composeapp.generated.resources.career_matrios_team
import junjange_dev.composeapp.generated.resources.ic_lio
import junjange_dev.composeapp.generated.resources.ic_matrios
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Career(
    val logoRes: DrawableResource,
    val periodRes: StringResource,
    val nameRes: StringResource,
    val teamRes: StringResource,
    val project: List<CareerProject>,
) {
    Lio(
        logoRes = Res.drawable.ic_lio,
        periodRes = Res.string.career_lio_period,
        nameRes = Res.string.career_lio,
        teamRes = Res.string.career_lio_team,
        project = LIO_PROJECTS,
    ),
    Matrios(
        logoRes = Res.drawable.ic_matrios,
        periodRes = Res.string.career_matrios_period,
        nameRes = Res.string.career_matrios,
        teamRes = Res.string.career_matrios_team,
        project = MATRIOS_PROJECTS,
    ),
}
