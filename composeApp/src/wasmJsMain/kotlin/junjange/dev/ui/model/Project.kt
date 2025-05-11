package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.Res.drawable
import junjange_dev.composeapp.generated.resources.ic_friendogly
import junjange_dev.composeapp.generated.resources.ic_junjange_dev_black
import junjange_dev.composeapp.generated.resources.ic_knocknock
import junjange_dev.composeapp.generated.resources.ic_kordle
import junjange_dev.composeapp.generated.resources.ic_lucky_lottery
import junjange_dev.composeapp.generated.resources.ic_move_move
import junjange_dev.composeapp.generated.resources.ic_oh_soon_taxi
import junjange_dev.composeapp.generated.resources.ic_whatnow
import junjange_dev.composeapp.generated.resources.project_friendogly
import junjange_dev.composeapp.generated.resources.project_junjange_dev
import junjange_dev.composeapp.generated.resources.project_knocknock
import junjange_dev.composeapp.generated.resources.project_kordle
import junjange_dev.composeapp.generated.resources.project_lucky_lottery
import junjange_dev.composeapp.generated.resources.project_move_move
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi
import junjange_dev.composeapp.generated.resources.project_whatnow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Project(
    val titleRes: StringResource,
    val logoRes: DrawableResource,
    val url: String,
    val info: ProjectInfo? = null,
) {
    LuckyLottery(
        titleRes = Res.string.project_lucky_lottery,
        logoRes = drawable.ic_lucky_lottery,
        url = "https://github.com/junjange/lucky-lottery-android-v2",
        info = ProjectInfo.LuckyLottery,
    ),
    JunJangEDev(
        titleRes = Res.string.project_junjange_dev,
        logoRes = drawable.ic_junjange_dev_black,
        url = "https://github.com/junjange/junjange.dev",
    ),
    Friendogly(
        titleRes = Res.string.project_friendogly,
        logoRes = drawable.ic_friendogly,
        url = "https://github.com/woowacourse-teams/2024-friendogly",
    ),
    MoveMove(
        titleRes = Res.string.project_move_move,
        logoRes = drawable.ic_move_move,
        url = "https://github.com/boostcampwm2023/and03_movemove",
        info = ProjectInfo.MoveMove,
    ),
    Whatnow(
        titleRes = Res.string.project_whatnow,
        logoRes = drawable.ic_whatnow,
        url = "https://github.com/depromeet/whatnow-android",
    ),
    OhSoonTaxi(
        titleRes = Res.string.project_oh_soon_taxi,
        logoRes = drawable.ic_oh_soon_taxi,
        url = "https://github.com/Uttug-Seuja/sch-taxi-android-v2",
        info = ProjectInfo.OhSoonTaxi,
    ),
    Knocknock(
        titleRes = Res.string.project_knocknock,
        logoRes = drawable.ic_knocknock,
        url = "https://github.com/depromeet/12th-KnockKnock-Android",
    ),
    Kordle(
        titleRes = Res.string.project_kordle,
        logoRes = drawable.ic_kordle,
        url = "https://github.com/junjange/kordle-android",
    ),
    ;

    companion object {
        val featuredProjects: List<Project>
            get() = entries.filter { it.info != null }
    }
}
