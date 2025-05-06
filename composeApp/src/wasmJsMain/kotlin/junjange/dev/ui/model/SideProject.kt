package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.Res.drawable
import junjange_dev.composeapp.generated.resources.ic_friendogly
import junjange_dev.composeapp.generated.resources.ic_knocknock
import junjange_dev.composeapp.generated.resources.ic_kordle
import junjange_dev.composeapp.generated.resources.ic_lucky_lottery
import junjange_dev.composeapp.generated.resources.ic_move_move
import junjange_dev.composeapp.generated.resources.ic_oh_soon_taxi
import junjange_dev.composeapp.generated.resources.ic_whatnow
import junjange_dev.composeapp.generated.resources.side_friendogly
import junjange_dev.composeapp.generated.resources.side_knocknock
import junjange_dev.composeapp.generated.resources.side_kordle
import junjange_dev.composeapp.generated.resources.side_lucky_lottery
import junjange_dev.composeapp.generated.resources.side_move_move
import junjange_dev.composeapp.generated.resources.side_oh_soon_taxi
import junjange_dev.composeapp.generated.resources.side_whatnow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class SideProject(
    val titleRes: StringResource,
    val logoRes: DrawableResource,
    val url: String,
) {
    Friendogly(
        titleRes = Res.string.side_friendogly,
        logoRes = drawable.ic_friendogly,
        url = "https://github.com/woowacourse-teams/2024-friendogly",
    ),
    MoveMove(
        titleRes = Res.string.side_move_move,
        logoRes = drawable.ic_move_move,
        url = "https://github.com/boostcampwm2023/and03_movemove",
    ),
    Whatnow(
        titleRes = Res.string.side_whatnow,
        logoRes = drawable.ic_whatnow,
        url = "https://github.com/depromeet/whatnow-android",
    ),
    OhSoonTaxi(
        titleRes = Res.string.side_oh_soon_taxi,
        logoRes = drawable.ic_oh_soon_taxi,
        url = "https://github.com/Uttug-Seuja/sch-taxi-android-v2",
    ),
    Knocknock(
        titleRes = Res.string.side_knocknock,
        logoRes = drawable.ic_knocknock,
        url = "https://github.com/depromeet/12th-KnockKnock-Android",
    ),
    Kordle(
        titleRes = Res.string.side_kordle,
        logoRes = drawable.ic_kordle,
        url = "https://github.com/junjange/kordle-android",
    ),
    LuckyLottery(
        titleRes = Res.string.side_lucky_lottery,
        logoRes = drawable.ic_lucky_lottery,
        url = "https://github.com/junjange/lucky-lottery-android-v2",
    ),
}
