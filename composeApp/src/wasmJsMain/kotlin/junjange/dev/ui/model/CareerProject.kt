package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.career_lio_project1_contributions
import junjange_dev.composeapp.generated.resources.career_lio_project1_period
import junjange_dev.composeapp.generated.resources.career_lio_project1_tech_stack
import junjange_dev.composeapp.generated.resources.career_lio_project1_title
import junjange_dev.composeapp.generated.resources.career_lio_project2_contributions
import junjange_dev.composeapp.generated.resources.career_lio_project2_period
import junjange_dev.composeapp.generated.resources.career_lio_project2_tech_stack
import junjange_dev.composeapp.generated.resources.career_lio_project2_title
import junjange_dev.composeapp.generated.resources.career_lio_project3_contributions
import junjange_dev.composeapp.generated.resources.career_lio_project3_period
import junjange_dev.composeapp.generated.resources.career_lio_project3_tech_stack
import junjange_dev.composeapp.generated.resources.career_lio_project3_title
import junjange_dev.composeapp.generated.resources.career_matrios_project1_contributions
import junjange_dev.composeapp.generated.resources.career_matrios_project1_period
import junjange_dev.composeapp.generated.resources.career_matrios_project1_tech_stack
import junjange_dev.composeapp.generated.resources.career_matrios_project1_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_contributions
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_period
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_title
import org.jetbrains.compose.resources.StringResource

data class CareerProject(
    val titleRes: StringResource,
    val periodRes: StringResource,
    val techStackRes: StringResource,
    val contributionsRes: StringResource,
) {
    companion object {
        val PAYTALAB_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = Res.string.career_paytalab_project1_title,
                    periodRes = Res.string.career_paytalab_project1_period,
                    techStackRes = Res.string.career_paytalab_project1_tech_stack,
                    contributionsRes = Res.string.career_paytalab_project1_contributions,
                ),
            )

        val LIO_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = Res.string.career_lio_project3_title,
                    periodRes = Res.string.career_lio_project3_period,
                    techStackRes = Res.string.career_lio_project3_tech_stack,
                    contributionsRes = Res.string.career_lio_project3_contributions,
                ),
                CareerProject(
                    titleRes = Res.string.career_lio_project2_title,
                    periodRes = Res.string.career_lio_project2_period,
                    techStackRes = Res.string.career_lio_project2_tech_stack,
                    contributionsRes = Res.string.career_lio_project2_contributions,
                ),
                CareerProject(
                    titleRes = Res.string.career_lio_project1_title,
                    periodRes = Res.string.career_lio_project1_period,
                    techStackRes = Res.string.career_lio_project1_tech_stack,
                    contributionsRes = Res.string.career_lio_project1_contributions,
                ),
            )

        val MATRIOS_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = Res.string.career_matrios_project1_title,
                    periodRes = Res.string.career_matrios_project1_period,
                    techStackRes = Res.string.career_matrios_project1_tech_stack,
                    contributionsRes = Res.string.career_matrios_project1_contributions,
                ),
            )
    }
}
