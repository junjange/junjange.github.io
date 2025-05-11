package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.section_about
import junjange_dev.composeapp.generated.resources.section_career
import junjange_dev.composeapp.generated.resources.section_experience
import junjange_dev.composeapp.generated.resources.section_home
import junjange_dev.composeapp.generated.resources.section_project
import org.jetbrains.compose.resources.StringResource

enum class Section(
    val title: StringResource,
) {
    Home(Res.string.section_home),
    AboutMe(Res.string.section_about),
    Career(Res.string.section_career),
    Project(Res.string.section_project),
    Experience(Res.string.section_experience),
}
