package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.android_developer
import junjange_dev.composeapp.generated.resources.github
import junjange_dev.composeapp.generated.resources.google_play_store
import junjange_dev.composeapp.generated.resources.notion
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_contributions
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_period
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_subtitle
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_techStack
import junjange_dev.composeapp.generated.resources.project_move_move_contributions
import junjange_dev.composeapp.generated.resources.project_move_move_period
import junjange_dev.composeapp.generated.resources.project_move_move_subtitle
import junjange_dev.composeapp.generated.resources.project_move_move_techStack
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_contributions
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_period
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_subtitle
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_techStack
import org.jetbrains.compose.resources.StringResource

data class ProjectInfo(
    val subtitleRes: StringResource,
    val links: List<Link>,
    val techStackRes: StringResource,
    val periodRes: StringResource,
    val roleRes: StringResource,
    val contributionsRes: StringResource,
) {
    companion object {
        val LuckyLottery =
            ProjectInfo(
                subtitleRes = Res.string.project_lucky_lottery_subtitle,
                links =
                    listOf(
                        Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.junjange.lotto3&hl=ko",
                        Res.string.github to "https://github.com/junjange/lucky-lottery-android-v2",
                        Res.string.notion to "https://www.notion.so/e3c739fdf1ce4613ad005dfae45f88a1",
                    ),
                techStackRes = Res.string.project_lucky_lottery_techStack,
                periodRes = Res.string.project_lucky_lottery_period,
                roleRes = Res.string.android_developer,
                contributionsRes = Res.string.project_lucky_lottery_contributions,
            )

        val MoveMove =
            ProjectInfo(
                subtitleRes = Res.string.project_move_move_subtitle,
                links =
                    listOf(
                        Res.string.github to "https://github.com/boostcampwm2023/and03_movemove",
                        Res.string.notion to "https://www.notion.so/00851359b105415cacc77e9b9a683324",
                    ),
                techStackRes = Res.string.project_move_move_techStack,
                periodRes = Res.string.project_move_move_period,
                roleRes = Res.string.android_developer,
                contributionsRes = Res.string.project_move_move_contributions,
            )

        val OhSoonTaxi =
            ProjectInfo(
                subtitleRes = Res.string.project_oh_soon_taxi_subtitle,
                links =
                    listOf(
                        Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.sch.sch_taxi&hl=ko-KR",
                        Res.string.github to "https://github.com/Uttug-Seuja/sch-taxi-android-v2",
                        Res.string.notion to "https://www.notion.so/v2-db3df8de7aa1424db4e23f118df96f68",
                    ),
                techStackRes = Res.string.project_oh_soon_taxi_techStack,
                periodRes = Res.string.project_oh_soon_taxi_period,
                roleRes = Res.string.android_developer,
                contributionsRes = Res.string.project_oh_soon_taxi_contributions,
            )
    }
}
