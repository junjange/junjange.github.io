package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.ic_email
import junjange_dev.composeapp.generated.resources.ic_github
import junjange_dev.composeapp.generated.resources.ic_linkedin
import junjange_dev.composeapp.generated.resources.ic_tistory
import org.jetbrains.compose.resources.DrawableResource

enum class Contact(
    val iconRes: DrawableResource,
    val url: String,
) {
    GITHUB(
        iconRes = Res.drawable.ic_github,
        url = "https://github.com/junjange",
    ),
    TISTORY(
        iconRes = Res.drawable.ic_tistory,
        url = "https://fre2-dom.tistory.com",
    ),
    LINKEDIN(
        iconRes = Res.drawable.ic_linkedin,
        url = "https://www.linkedin.com/in/junjange",
    ),

    EMAIL(
        iconRes = Res.drawable.ic_email,
        url = "mailto:wnswkd486@gmail.com",
    ),
}
