package junjange.dev.ui.model

import junjange.dev.ui.model.CareerProject.Companion.LIO_PROJECTS
import junjange.dev.ui.model.CareerProject.Companion.MATRIOS_PROJECTS
import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.career_lio
import junjange_dev.composeapp.generated.resources.career_lio_date
import junjange_dev.composeapp.generated.resources.career_lio_team
import junjange_dev.composeapp.generated.resources.career_matrios
import junjange_dev.composeapp.generated.resources.career_matrios_date
import junjange_dev.composeapp.generated.resources.career_matrios_team
import junjange_dev.composeapp.generated.resources.ic_lio
import junjange_dev.composeapp.generated.resources.ic_matrios
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Career(
    val logoRes: DrawableResource,
    val dateRes: StringResource,
    val nameRes: StringResource,
    val teamRes: StringResource,
    val url: String,
    val project: List<CareerProject>,
) {
    Lio(
        logoRes = Res.drawable.ic_lio,
        dateRes = Res.string.career_lio_date,
        nameRes = Res.string.career_lio,
        teamRes = Res.string.career_lio_team,
        url = "https://www.lio.team",
        project = LIO_PROJECTS,
    ),
    Matrios(
        logoRes = Res.drawable.ic_matrios,
        dateRes = Res.string.career_matrios_date,
        nameRes = Res.string.career_matrios,
        teamRes = Res.string.career_matrios_team,
        url = "https://www.matriosonline.com",
        project = MATRIOS_PROJECTS,
    ),
}
