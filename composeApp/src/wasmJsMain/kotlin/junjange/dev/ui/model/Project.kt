package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.Res.drawable
import junjange_dev.composeapp.generated.resources.android_developer
import junjange_dev.composeapp.generated.resources.flutter_developer
import junjange_dev.composeapp.generated.resources.github
import junjange_dev.composeapp.generated.resources.google_play_store
import junjange_dev.composeapp.generated.resources.ic_bium_grapic
import junjange_dev.composeapp.generated.resources.ic_camping_tour_graphic
import junjange_dev.composeapp.generated.resources.ic_comaplain_graphic
import junjange_dev.composeapp.generated.resources.ic_friendogly_grahic
import junjange_dev.composeapp.generated.resources.ic_junjange_dev_graphic
import junjange_dev.composeapp.generated.resources.ic_knocknock_graphic
import junjange_dev.composeapp.generated.resources.ic_kordle_graphic
import junjange_dev.composeapp.generated.resources.ic_link_letter_graphic
import junjange_dev.composeapp.generated.resources.ic_lucky_lottery_graphic
import junjange_dev.composeapp.generated.resources.ic_move_move_graphic
import junjange_dev.composeapp.generated.resources.ic_oh_soon_taxi_graphic
import junjange_dev.composeapp.generated.resources.ic_pmd_key_graphic
import junjange_dev.composeapp.generated.resources.ic_public_poll_graphic
import junjange_dev.composeapp.generated.resources.ic_recycle_cycle_graphic
import junjange_dev.composeapp.generated.resources.ic_whatnow_graphic
import junjange_dev.composeapp.generated.resources.notion
import junjange_dev.composeapp.generated.resources.project_bium
import junjange_dev.composeapp.generated.resources.project_bium_contributions
import junjange_dev.composeapp.generated.resources.project_bium_description
import junjange_dev.composeapp.generated.resources.project_bium_period
import junjange_dev.composeapp.generated.resources.project_bium_subtitle
import junjange_dev.composeapp.generated.resources.project_bium_techStack
import junjange_dev.composeapp.generated.resources.project_camping_tour
import junjange_dev.composeapp.generated.resources.project_camping_tour_contributions
import junjange_dev.composeapp.generated.resources.project_camping_tour_description
import junjange_dev.composeapp.generated.resources.project_camping_tour_period
import junjange_dev.composeapp.generated.resources.project_camping_tour_subtitle
import junjange_dev.composeapp.generated.resources.project_camping_tour_techStack
import junjange_dev.composeapp.generated.resources.project_comaplain
import junjange_dev.composeapp.generated.resources.project_comaplain_contributions
import junjange_dev.composeapp.generated.resources.project_comaplain_description
import junjange_dev.composeapp.generated.resources.project_comaplain_period
import junjange_dev.composeapp.generated.resources.project_comaplain_subtitle
import junjange_dev.composeapp.generated.resources.project_comaplain_techStack
import junjange_dev.composeapp.generated.resources.project_friendogly
import junjange_dev.composeapp.generated.resources.project_friendogly_contributions
import junjange_dev.composeapp.generated.resources.project_friendogly_description
import junjange_dev.composeapp.generated.resources.project_friendogly_period
import junjange_dev.composeapp.generated.resources.project_friendogly_subtitle
import junjange_dev.composeapp.generated.resources.project_friendogly_techStack
import junjange_dev.composeapp.generated.resources.project_junjange_dev
import junjange_dev.composeapp.generated.resources.project_junjange_dev_contributions
import junjange_dev.composeapp.generated.resources.project_junjange_dev_description
import junjange_dev.composeapp.generated.resources.project_junjange_dev_period
import junjange_dev.composeapp.generated.resources.project_junjange_dev_subtitle
import junjange_dev.composeapp.generated.resources.project_junjange_dev_techStack
import junjange_dev.composeapp.generated.resources.project_knocknock
import junjange_dev.composeapp.generated.resources.project_knocknock_contributions
import junjange_dev.composeapp.generated.resources.project_knocknock_description
import junjange_dev.composeapp.generated.resources.project_knocknock_period
import junjange_dev.composeapp.generated.resources.project_knocknock_subtitle
import junjange_dev.composeapp.generated.resources.project_knocknock_techStack
import junjange_dev.composeapp.generated.resources.project_kordle
import junjange_dev.composeapp.generated.resources.project_kordle_contributions
import junjange_dev.composeapp.generated.resources.project_kordle_description
import junjange_dev.composeapp.generated.resources.project_kordle_period
import junjange_dev.composeapp.generated.resources.project_kordle_subtitle
import junjange_dev.composeapp.generated.resources.project_kordle_techStack
import junjange_dev.composeapp.generated.resources.project_link_letter
import junjange_dev.composeapp.generated.resources.project_link_letter_contributions
import junjange_dev.composeapp.generated.resources.project_link_letter_description
import junjange_dev.composeapp.generated.resources.project_link_letter_period
import junjange_dev.composeapp.generated.resources.project_link_letter_subtitle
import junjange_dev.composeapp.generated.resources.project_link_letter_techStack
import junjange_dev.composeapp.generated.resources.project_lucky_lottery
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_contributions
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_description
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_period
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_subtitle
import junjange_dev.composeapp.generated.resources.project_lucky_lottery_techStack
import junjange_dev.composeapp.generated.resources.project_move_move
import junjange_dev.composeapp.generated.resources.project_move_move_contributions
import junjange_dev.composeapp.generated.resources.project_move_move_description
import junjange_dev.composeapp.generated.resources.project_move_move_period
import junjange_dev.composeapp.generated.resources.project_move_move_subtitle
import junjange_dev.composeapp.generated.resources.project_move_move_techStack
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_contributions
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_description
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_period
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_subtitle
import junjange_dev.composeapp.generated.resources.project_oh_soon_taxi_techStack
import junjange_dev.composeapp.generated.resources.project_pbp
import junjange_dev.composeapp.generated.resources.project_pbp_contributions
import junjange_dev.composeapp.generated.resources.project_pbp_description
import junjange_dev.composeapp.generated.resources.project_pbp_period
import junjange_dev.composeapp.generated.resources.project_pbp_subtitle
import junjange_dev.composeapp.generated.resources.project_pbp_techStack
import junjange_dev.composeapp.generated.resources.project_pmd_key
import junjange_dev.composeapp.generated.resources.project_pmd_key_contributions
import junjange_dev.composeapp.generated.resources.project_pmd_key_description
import junjange_dev.composeapp.generated.resources.project_pmd_key_period
import junjange_dev.composeapp.generated.resources.project_pmd_key_subtitle
import junjange_dev.composeapp.generated.resources.project_pmd_key_techStack
import junjange_dev.composeapp.generated.resources.project_recycle_cycle
import junjange_dev.composeapp.generated.resources.project_recycle_cycle_contributions
import junjange_dev.composeapp.generated.resources.project_recycle_cycle_description
import junjange_dev.composeapp.generated.resources.project_recycle_cycle_period
import junjange_dev.composeapp.generated.resources.project_recycle_cycle_subtitle
import junjange_dev.composeapp.generated.resources.project_recycle_cycle_techStack
import junjange_dev.composeapp.generated.resources.project_whatnow
import junjange_dev.composeapp.generated.resources.project_whatnow_contributions
import junjange_dev.composeapp.generated.resources.project_whatnow_description
import junjange_dev.composeapp.generated.resources.project_whatnow_period
import junjange_dev.composeapp.generated.resources.project_whatnow_subtitle
import junjange_dev.composeapp.generated.resources.project_whatnow_techStack
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class Project(
    val titleRes: StringResource,
    val subtitleRes: StringResource,
    val graphicRes: DrawableResource,
    val periodRes: StringResource,
    val descriptionRes: StringResource,
    val roleRes: StringResource,
    val techStackRes: StringResource,
    val links: List<Link>,
    val contributionsRes: StringResource,
) {
    LINK_LETTER(
        titleRes = Res.string.project_link_letter,
        subtitleRes = Res.string.project_link_letter_subtitle,
        graphicRes = drawable.ic_link_letter_graphic,
        periodRes = Res.string.project_link_letter_period,
        descriptionRes = Res.string.project_link_letter_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_link_letter_techStack,
        links =
            listOf(
                Res.string.github to "https://github.com/junjange/linkletter-client",
            ),
        contributionsRes = Res.string.project_link_letter_contributions,
    ),
    JUNJANGE_DEV(
        titleRes = Res.string.project_junjange_dev,
        subtitleRes = Res.string.project_junjange_dev_subtitle,
        graphicRes = drawable.ic_junjange_dev_graphic,
        periodRes = Res.string.project_junjange_dev_period,
        descriptionRes = Res.string.project_junjange_dev_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_junjange_dev_techStack,
        links =
            listOf(
                Res.string.github to "https://github.com/junjange/junjange.github.io",
            ),
        contributionsRes = Res.string.project_junjange_dev_contributions,
    ),
    LUCKY_LOTTERY(
        titleRes = Res.string.project_lucky_lottery,
        subtitleRes = Res.string.project_lucky_lottery_subtitle,
        graphicRes = drawable.ic_lucky_lottery_graphic,
        periodRes = Res.string.project_lucky_lottery_period,
        descriptionRes = Res.string.project_lucky_lottery_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_lucky_lottery_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.junjange.lotto3",
                Res.string.github to "https://github.com/junjange/lucky-lottery-android-v2",
                Res.string.notion to "https://www.notion.so/e3c739fdf1ce4613ad005dfae45f88a1",
            ),
        contributionsRes = Res.string.project_lucky_lottery_contributions,
    ),
    FRIENDOGLY(
        titleRes = Res.string.project_friendogly,
        subtitleRes = Res.string.project_friendogly_subtitle,
        graphicRes = drawable.ic_friendogly_grahic,
        periodRes = Res.string.project_friendogly_period,
        descriptionRes = Res.string.project_friendogly_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_friendogly_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.happy.friendogly&hl=ko",
                Res.string.github to "https://github.com/woowacourse-teams/2024-friendogly",
            ),
        contributionsRes = Res.string.project_friendogly_contributions,
    ),
    MOVE_MOVE(
        titleRes = Res.string.project_move_move,
        subtitleRes = Res.string.project_move_move_subtitle,
        graphicRes = drawable.ic_move_move_graphic,
        periodRes = Res.string.project_move_move_period,
        descriptionRes = Res.string.project_move_move_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_move_move_techStack,
        links =
            listOf(
                Res.string.github to "https://github.com/boostcampwm2023/and03_movemove",
                Res.string.notion to "https://www.notion.so/00851359b105415cacc77e9b9a683324",
            ),
        contributionsRes = Res.string.project_move_move_contributions,
    ),
    WHATNOW(
        titleRes = Res.string.project_whatnow,
        subtitleRes = Res.string.project_whatnow_subtitle,
        graphicRes = drawable.ic_whatnow_graphic,
        periodRes = Res.string.project_whatnow_period,
        descriptionRes = Res.string.project_whatnow_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_whatnow_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.depromeet.whatnow&hl=ko-KR",
                Res.string.github to "https://github.com/depromeet/whatnow-android",
                Res.string.notion to "https://www.notion.so/what-now-b684457c95c2461786af4e3db6296917",
            ),
        contributionsRes = Res.string.project_whatnow_contributions,
    ),
    OH_SOON_TAXI(
        titleRes = Res.string.project_oh_soon_taxi,
        subtitleRes = Res.string.project_oh_soon_taxi_subtitle,
        graphicRes = drawable.ic_oh_soon_taxi_graphic,
        periodRes = Res.string.project_oh_soon_taxi_period,
        descriptionRes = Res.string.project_oh_soon_taxi_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_oh_soon_taxi_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.sch.sch_taxi&hl=ko-KR",
                Res.string.github to "https://github.com/Uttug-Seuja/sch-taxi-android-v2",
                Res.string.notion to "https://www.notion.so/v2-db3df8de7aa1424db4e23f118df96f68",
            ),
        contributionsRes = Res.string.project_oh_soon_taxi_contributions,
    ),
    KNOCKNOC(
        titleRes = Res.string.project_knocknock,
        subtitleRes = Res.string.project_knocknock_subtitle,
        graphicRes = drawable.ic_knocknock_graphic,
        periodRes = Res.string.project_knocknock_period,
        descriptionRes = Res.string.project_knocknock_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_knocknock_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.depromeet.knockknock",
                Res.string.github to "https://github.com/depromeet/12th-KnockKnock-Android",
                Res.string.notion to "https://www.notion.so/Knocknock-b0b443889a174b8eacba191084e7e9a3",
            ),
        contributionsRes = Res.string.project_knocknock_contributions,
    ),
    KORDLE(
        titleRes = Res.string.project_kordle,
        subtitleRes = Res.string.project_kordle_subtitle,
        graphicRes = drawable.ic_kordle_graphic,
        periodRes = Res.string.project_kordle_period,
        descriptionRes = Res.string.project_kordle_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_kordle_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.junjange.kordle",
                Res.string.github to "https://github.com/junjange/Kordle-Android",
                Res.string.notion to "https://www.notion.so/80c53deb694e4443b4af366237bc9809",
            ),
        contributionsRes = Res.string.project_kordle_contributions,
    ),
    PBP(
        titleRes = Res.string.project_pbp,
        subtitleRes = Res.string.project_pbp_subtitle,
        graphicRes = drawable.ic_public_poll_graphic,
        periodRes = Res.string.project_pbp_period,
        descriptionRes = Res.string.project_pbp_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_pbp_techStack,
        links =
            listOf(
                Res.string.github to "https://github.com/junjange/PublicPollAndroid",
                Res.string.notion to "https://www.notion.so/Public-Poll-b1409d1c7b044d16962d852eddf1487a",
            ),
        contributionsRes = Res.string.project_pbp_contributions,
    ),
    PMD_KEY(
        titleRes = Res.string.project_pmd_key,
        subtitleRes = Res.string.project_pmd_key_subtitle,
        graphicRes = drawable.ic_pmd_key_graphic,
        periodRes = Res.string.project_pmd_key_period,
        descriptionRes = Res.string.project_pmd_key_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_pmd_key_techStack,
        links =
            listOf(
                Res.string.github to "https://github.com/junjange/MECA-PMD-Key-Android",
                Res.string.notion to "https://www.notion.so/PMD-Key-c42feb08d4504f61a7070390ef787f90",
            ),
        contributionsRes = Res.string.project_pmd_key_contributions,
    ),
    COMAPLAIN(
        titleRes = Res.string.project_comaplain,
        subtitleRes = Res.string.project_comaplain_subtitle,
        graphicRes = drawable.ic_comaplain_graphic,
        periodRes = Res.string.project_comaplain_period,
        descriptionRes = Res.string.project_comaplain_description,
        roleRes = Res.string.flutter_developer,
        techStackRes = Res.string.project_comaplain_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.comaplain.comaplain",
                Res.string.github to "https://github.com/junjange/comaplain-app",
                Res.string.notion to "https://www.notion.so/Comaplain-fe8c33c71d5a4a42b456410a1db36cda",
            ),
        contributionsRes = Res.string.project_comaplain_contributions,
    ),
    BIUM(
        titleRes = Res.string.project_bium,
        subtitleRes = Res.string.project_bium_subtitle,
        graphicRes = drawable.ic_bium_grapic,
        periodRes = Res.string.project_bium_period,
        descriptionRes = Res.string.project_bium_description,
        roleRes = Res.string.flutter_developer,
        techStackRes = Res.string.project_bium_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.sch.sch_taxi&hl=ko-KR",
                Res.string.github to "https://github.com/Uttug-Seuja/sch-taxi-android-v2",
                Res.string.notion to "https://www.notion.so/v2-db3df8de7aa1424db4e23f118df96f68",
            ),
        contributionsRes = Res.string.project_bium_contributions,
    ),
    RECYCLE_CYCLE(
        titleRes = Res.string.project_recycle_cycle,
        subtitleRes = Res.string.project_recycle_cycle_subtitle,
        graphicRes = drawable.ic_recycle_cycle_graphic,
        periodRes = Res.string.project_recycle_cycle_period,
        descriptionRes = Res.string.project_recycle_cycle_description,
        roleRes = Res.string.flutter_developer,
        techStackRes = Res.string.project_recycle_cycle_techStack,
        links =
            listOf(
                Res.string.github to "https://github.com/gdscHEO/heoproject",
                Res.string.notion to "https://www.notion.so/Bium-65fee4aeeba247ddba99cb0330f64dc8",
            ),
        contributionsRes = Res.string.project_recycle_cycle_contributions,
    ),
    CAMPING_TOUR(
        titleRes = Res.string.project_camping_tour,
        subtitleRes = Res.string.project_camping_tour_subtitle,
        graphicRes = drawable.ic_camping_tour_graphic,
        periodRes = Res.string.project_camping_tour_period,
        descriptionRes = Res.string.project_camping_tour_description,
        roleRes = Res.string.android_developer,
        techStackRes = Res.string.project_camping_tour_techStack,
        links =
            listOf(
                Res.string.google_play_store to "https://play.google.com/store/apps/details?id=com.junjange.touring",
                Res.string.github to "https://github.com/junjange/camping-tour-android",
                Res.string.notion to "https://www.notion.so/c6cc6728e74c44d2bf2c1749fe0d7469",
            ),
        contributionsRes = Res.string.project_camping_tour_contributions,
    ),
}
