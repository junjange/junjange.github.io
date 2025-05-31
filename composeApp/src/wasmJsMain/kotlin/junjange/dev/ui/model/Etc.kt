package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.etc_devfest_campus_korea
import junjange_dev.composeapp.generated.resources.etc_devfest_campus_korea_period
import junjange_dev.composeapp.generated.resources.etc_droid_knights_2025
import junjange_dev.composeapp.generated.resources.etc_droid_knights_2025_period
import junjange_dev.composeapp.generated.resources.etc_gdsc_kr_winter_cup
import junjange_dev.composeapp.generated.resources.etc_gdsc_kr_winter_cup_period
import junjange_dev.composeapp.generated.resources.etc_gdsc_sch_x_skhy_hack
import junjange_dev.composeapp.generated.resources.etc_gdsc_sch_x_skhy_hack_period
import junjange_dev.composeapp.generated.resources.etc_kusitms
import junjange_dev.composeapp.generated.resources.etc_kusitms_period
import org.jetbrains.compose.resources.StringResource

enum class Etc(
    val titleRes: StringResource,
    val periodRes: StringResource,
) {
    DROID_KNIGHTS_2025(
        titleRes = Res.string.etc_droid_knights_2025,
        Res.string.etc_droid_knights_2025_period,
    ),
    KUSITMS(
        titleRes = Res.string.etc_kusitms,
        periodRes = Res.string.etc_kusitms_period,
    ),
    GDSC_SCH_X_SKHU_HACK(
        titleRes = Res.string.etc_gdsc_sch_x_skhy_hack,
        periodRes = Res.string.etc_gdsc_sch_x_skhy_hack_period,
    ),
    GDSC_KR_WINTER_CUP(
        titleRes = Res.string.etc_gdsc_kr_winter_cup,
        periodRes = Res.string.etc_gdsc_kr_winter_cup_period,
    ),
    DEVFEST_CAMPUS_KOREA(
        titleRes = Res.string.etc_devfest_campus_korea,
        periodRes = Res.string.etc_devfest_campus_korea_period,
    ),
}
