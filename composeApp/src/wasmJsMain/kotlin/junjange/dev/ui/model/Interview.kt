package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res
import junjange_dev.composeapp.generated.resources.question1
import junjange_dev.composeapp.generated.resources.question1_answer
import junjange_dev.composeapp.generated.resources.question2
import junjange_dev.composeapp.generated.resources.question2_answer
import junjange_dev.composeapp.generated.resources.question3
import junjange_dev.composeapp.generated.resources.question3_answer
import org.jetbrains.compose.resources.StringResource

enum class Interview(
    val question: StringResource,
    val answer: StringResource,
) {
    Question1(
        question = Res.string.question1,
        answer = Res.string.question1_answer,
    ),
    Question2(
        question = Res.string.question2,
        answer = Res.string.question2_answer,
    ),
    Question3(
        question = Res.string.question3,
        answer = Res.string.question3_answer,
    ),
}
