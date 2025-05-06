package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.etc_gdsc_kr_winter_cup
import junjange_dev.composeapp.generated.resources.etc_gdsc_kr_winter_cup_date
import junjange_dev.composeapp.generated.resources.etc_gdsc_sch_x_skhy_hack
import junjange_dev.composeapp.generated.resources.etc_gdsc_sch_x_skhy_hack_date
import junjange_dev.composeapp.generated.resources.etc_kusitms
import junjange_dev.composeapp.generated.resources.etc_kusitms_date
import org.jetbrains.compose.resources.StringResource

enum class Etc(
    val titleRes: StringResource,
    val dateRes: StringResource,
) {
    Kusitms(
        titleRes = Res.string.etc_kusitms,
        dateRes = Res.string.etc_kusitms_date,
    ),
    GDSC_SCH_X_SKHU_HACK(
        titleRes = Res.string.etc_gdsc_sch_x_skhy_hack,
        dateRes = Res.string.etc_gdsc_sch_x_skhy_hack_date,
    ),
    GDSC_KR_WINTER_CUP(
        titleRes = Res.string.etc_gdsc_kr_winter_cup,
        dateRes = Res.string.etc_gdsc_kr_winter_cup_date,
    ),
}
