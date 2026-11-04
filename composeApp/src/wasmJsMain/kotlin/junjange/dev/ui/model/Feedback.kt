package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.feedback_place_other
import junjange_dev.composeapp.generated.resources.feedback_place_side_project
import junjange_dev.composeapp.generated.resources.feedback_relation_junior
import junjange_dev.composeapp.generated.resources.feedback_relation_leader
import junjange_dev.composeapp.generated.resources.feedback_relation_other_team
import junjange_dev.composeapp.generated.resources.feedback_relation_senior
import junjange_dev.composeapp.generated.resources.feedback_relation_teammate
import org.jetbrains.compose.resources.StringResource

const val FEEDBACK_FORM_URL = "https://github.com/junjange/junjange.github.io/issues/new?template=peer-feedback.yml"

data class Feedback(
    val author: FeedbackAuthor,
    val place: FeedbackPlace,
    val relation: FeedbackRelation,
    val strength: String,
    val growth: String? = null,
)

sealed interface FeedbackAuthor {
    data class GitHub(
        val login: String,
        val id: Long,
    ) : FeedbackAuthor {
        val profileUrl get() = "https://github.com/$login"
        val avatarUrl get() = "https://avatars.githubusercontent.com/u/$id?s=96&v=4"
    }

    data object Specter : FeedbackAuthor
}

sealed interface FeedbackPlace {
    data class Company(
        val career: Career,
    ) : FeedbackPlace

    data class Activity(
        val experience: Experience,
    ) : FeedbackPlace

    data object SideProject : FeedbackPlace

    data object Other : FeedbackPlace

    val labelRes: StringResource
        get() =
            when (this) {
                is Company -> career.nameRes
                is Activity -> experience.titleRes
                SideProject -> Res.string.feedback_place_side_project
                Other -> Res.string.feedback_place_other
            }
}

enum class FeedbackRelation(
    val labelRes: StringResource,
) {
    Leader(Res.string.feedback_relation_leader),
    Senior(Res.string.feedback_relation_senior),
    Teammate(Res.string.feedback_relation_teammate),
    OtherTeam(Res.string.feedback_relation_other_team),
    Junior(Res.string.feedback_relation_junior),
}
