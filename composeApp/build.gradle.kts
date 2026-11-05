import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer =
                    (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                        static =
                            (static ?: mutableListOf()).apply {
                                // Serve sources to debug inside browser
                                add(rootDirPath)
                                add(projectDirPath)
                            }
                    }
            }
        }
        binaries.executable()
    }

    sourceSets {

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
    }
}

val feedbackDataDir: Directory = rootProject.layout.projectDirectory.dir("feedbacks")
val feedbackTemplateFile: RegularFile = rootProject.layout.projectDirectory.file(".github/ISSUE_TEMPLATE/peer-feedback.yml")
val feedbackOptionsFile: RegularFile = rootProject.layout.projectDirectory.file(".github/feedback/options.json")
val feedbackSourceFiles =
    files(
        "src/wasmJsMain/kotlin/junjange/dev/ui/model/Career.kt",
        "src/wasmJsMain/kotlin/junjange/dev/ui/model/Experience.kt",
        "src/wasmJsMain/kotlin/junjange/dev/ui/model/Feedback.kt",
        "src/commonMain/composeResources/values/strings.xml",
    )

val FEEDBACK_MAX_LENGTH = 300

data class FeedbackOption(
    val id: String,
    val label: String,
    val code: String,
)

class FeedbackOptions(
    val places: List<FeedbackOption>,
    val relations: List<FeedbackOption>,
)

fun readFeedbackOptions(modelDir: File, stringsFile: File): FeedbackOptions {
    val strings =
        Regex("""<string name="(\w+)">(.*?)</string>""", RegexOption.DOT_MATCHES_ALL)
            .findAll(stringsFile.readText())
            .associate { it.groupValues[1] to it.groupValues[2].replace("\\'", "'").replace("&amp;", "&") }

    fun label(key: String) = strings[key] ?: throw GradleException("strings.xml에 $key 항목이 없어요.")

    fun enumBlocks(source: String): List<Pair<String, String>> {
        val headers = Regex("""(?m)^enum class (\w+)""").findAll(source).toList()
        return headers.mapIndexed { index, header ->
            header.groupValues[1] to source.substring(header.range.last, headers.getOrNull(index + 1)?.range?.first ?: source.length)
        }
    }

    fun entries(body: String, resKey: String): List<Pair<String, String>> =
        Regex("""(?ms)^ {4}(\w+)\(\n(.*?)^ {4}\),""").findAll(body).map { entry ->
            val res =
                Regex("""$resKey = Res\.string\.(\w+)""").find(entry.groupValues[2])?.groupValues?.get(1)
                    ?: throw GradleException("${entry.groupValues[1]}에서 $resKey 를 찾지 못했어요.")
            entry.groupValues[1] to res
        }.toList()

    val careers =
        enumBlocks(modelDir.resolve("Career.kt").readText()).flatMap { (enumName, body) ->
            entries(body, "nameRes").map { (id, res) -> FeedbackOption(id, label(res), "FeedbackPlace.Company($enumName.$id)") }
        }
    val experiences =
        enumBlocks(modelDir.resolve("Experience.kt").readText()).flatMap { (enumName, body) ->
            entries(body, "titleRes").map { (id, res) -> FeedbackOption(id, label(res), "FeedbackPlace.Activity($enumName.$id)") }
        }
    val extras =
        listOf(
            FeedbackOption("SideProject", label("feedback_place_side_project"), "FeedbackPlace.SideProject"),
            FeedbackOption("Other", label("feedback_place_other"), "FeedbackPlace.Other"),
        )
    val relations =
        Regex("""(?m)^ {4}(\w+)\(Res\.string\.(feedback_relation_\w+)\),$""")
            .findAll(modelDir.resolve("Feedback.kt").readText())
            .map { FeedbackOption(it.groupValues[1], label(it.groupValues[2]), "FeedbackRelation.${it.groupValues[1]}") }
            .toList()

    val places = careers + experiences + extras
    if (careers.isEmpty() || experiences.isEmpty() || relations.isEmpty()) throw GradleException("피드백 선택지를 읽지 못했어요.")
    if (places.map { it.id }.toSet().size != places.size) throw GradleException("함께한 곳의 id가 중복돼요.")
    return FeedbackOptions(places, relations)
}

fun jsonString(value: String): String =
    buildString {
        append('"')
        value.forEach { char ->
            when {
                char == '"' -> append("\\\"")
                char == '\\' -> append("\\\\")
                char == '\n' -> append("\\n")
                char < ' ' -> append("\\u%04x".format(char.code))
                else -> append(char)
            }
        }
        append('"')
    }

fun renderOptionsJson(options: FeedbackOptions): String {
    fun list(items: List<FeedbackOption>) = items.joinToString(",\n") { "    { \"id\": ${jsonString(it.id)}, \"label\": ${jsonString(it.label)} }" }
    return "{\n  \"places\": [\n${list(options.places)}\n  ],\n  \"relations\": [\n${list(options.relations)}\n  ]\n}\n"
}

fun renderTemplate(template: String, options: FeedbackOptions): String {
    fun replaceBlock(source: String, name: String, items: List<FeedbackOption>): String {
        val block = Regex("""(?ms)^( *)# generated:$name:start\n.*?^ *# generated:$name:end$""")
        val match = block.find(source) ?: throw GradleException("이슈 템플릿에서 generated:$name 표시를 찾지 못했어요.")
        val indent = match.groupValues[1]
        val lines = listOf("$indent# generated:$name:start") + items.map { "$indent- ${it.label}" } + "$indent# generated:$name:end"
        return source.replaceRange(match.range, lines.joinToString("\n"))
    }
    return replaceBlock(replaceBlock(template, "places", options.places), "relations", options.relations)
}

fun currentFeedbackOptions() =
    readFeedbackOptions(
        project.file("src/wasmJsMain/kotlin/junjange/dev/ui/model"),
        project.file("src/commonMain/composeResources/values/strings.xml"),
    )

tasks.register("syncFeedbackOptions") {
    group = "feedback"
    description = "Career·Experience 기준으로 동료 피드백 이슈 양식의 선택지를 갱신해요."
    inputs.files(feedbackSourceFiles)
    outputs.files(feedbackTemplateFile, feedbackOptionsFile)
    doLast {
        val options = currentFeedbackOptions()
        val template = feedbackTemplateFile.asFile
        template.writeText(renderTemplate(template.readText(), options))
        feedbackOptionsFile.asFile.apply { parentFile.mkdirs() }.writeText(renderOptionsJson(options))
    }
}

tasks.register("checkFeedbackOptions") {
    group = "feedback"
    description = "동료 피드백 이슈 양식의 선택지가 최신인지 확인해요."
    inputs.files(feedbackSourceFiles, feedbackTemplateFile, feedbackOptionsFile)
    doLast {
        val options = currentFeedbackOptions()
        val template = feedbackTemplateFile.asFile.readText()
        val optionsFile = feedbackOptionsFile.asFile
        val upToDate =
            renderTemplate(template, options) == template &&
                optionsFile.exists() &&
                optionsFile.readText() == renderOptionsJson(options)
        if (!upToDate) throw GradleException("동료 피드백 선택지가 최신이 아니에요. ./gradlew syncFeedbackOptions 를 실행해주세요.")
    }
}

val generateFeedbacks by tasks.registering {
    group = "feedback"
    description = "feedbacks/*.json을 검증하고 Kotlin 코드로 만들어요."
    val outputDir = layout.buildDirectory.dir("generated/feedback/kotlin")
    inputs.files(feedbackDataDir.asFileTree)
    inputs.files(feedbackSourceFiles)
    outputs.dir(outputDir)
    doLast {
        val options = currentFeedbackOptions()
        val places = options.places.associateBy { it.id }
        val relations = options.relations.associateBy { it.id }
        val loginPattern = Regex("^[A-Za-z0-9](?:[A-Za-z0-9]|-(?=[A-Za-z0-9])){0,38}$")
        val datePattern = Regex("""^\d{4}-\d{2}-\d{2}$""")

        fun kotlinString(value: String) =
            buildString {
                append('"')
                value.forEach { char ->
                    when (char) {
                        '"' -> append("\\\"")
                        '\\' -> append("\\\\")
                        '$' -> append("\\$")
                        '\n' -> append("\\n")
                        else -> if (char >= ' ') append(char)
                    }
                }
                append('"')
            }

        val entries =
            feedbackDataDir.asFile
                .listFiles { file -> file.extension == "json" }
                .orEmpty()
                .sortedBy { it.name }
                .map { file ->
                    fun fail(reason: String): Nothing = throw GradleException("feedbacks/${file.name}: $reason")

                    @Suppress("UNCHECKED_CAST")
                    val json =
                        runCatching { groovy.json.JsonSlurper().parse(file) as Map<String, Any?> }
                            .getOrElse { fail("JSON 형식이 올바르지 않아요.") }

                    fun text(key: String, max: Int, required: Boolean): String? {
                        val value = json[key] ?: if (required) fail("$key 값이 필요해요.") else return null
                        if (value !is String) fail("$key 는 문자열이어야 해요.")
                        val trimmed = value.replace("\r", "").trim()
                        if (trimmed.isEmpty()) if (required) fail("$key 가 비어 있어요.") else return null
                        if (trimmed.length > max) fail("$key 는 ${max}자 이내여야 해요.")
                        return trimmed
                    }

                    val source = json["source"]
                    val allowedKeys =
                        when (source) {
                            "github" -> setOf("source", "login", "id", "issue", "place", "relation", "strength", "growth", "createdAt")
                            "specter" -> setOf("source", "login", "id", "place", "relation", "period", "strength", "growth", "createdAt")
                            else -> fail("source 는 github 또는 specter 여야 해요.")
                        }
                    (json.keys - allowedKeys).takeIf { it.isNotEmpty() }?.let { fail("알 수 없는 항목이 있어요: $it") }

                    val author =
                        if (source == "github") {
                            val login = json["login"] as? String ?: fail("login 값이 필요해요.")
                            if (!loginPattern.matches(login)) fail("login 형식이 올바르지 않아요.")
                            val id = (json["id"] as? Number)?.toLong()?.takeIf { it > 0 } ?: fail("id 는 양수여야 해요.")
                            val issue = (json["issue"] as? Number)?.toLong()?.takeIf { it > 0 } ?: fail("issue 는 양수여야 해요.")
                            if (file.name != "github-$issue.json") fail("파일 이름은 github-$issue.json 이어야 해요.")
                            "FeedbackAuthor.GitHub(login = ${kotlinString(login)}, id = ${id}L)"
                        } else {
                            if (!Regex("^specter-[a-z0-9-]+\\.json$").matches(file.name)) fail("파일 이름은 specter-이름.json 형식이어야 해요.")
                            val login = json["login"] as? String
                            val id = (json["id"] as? Number)?.toLong()
                            when {
                                login == null && id == null -> "FeedbackAuthor.Specter"
                                login == null || id == null || id <= 0 || !loginPattern.matches(login) -> fail("login 과 id 는 함께 올바르게 적어야 해요.")
                                else -> "FeedbackAuthor.GitHub(login = ${kotlinString(login)}, id = ${id}L)"
                            }
                        }
                    val place = places[json["place"]] ?: fail("place 는 ${places.keys} 중 하나여야 해요.")
                    val relation = relations[json["relation"]] ?: fail("relation 은 ${relations.keys} 중 하나여야 해요.")
                    val strength = text("strength", FEEDBACK_MAX_LENGTH, required = true)!!
                    val growth = text("growth", FEEDBACK_MAX_LENGTH, required = false)
                    val createdAt = text("createdAt", 10, required = false)
                    if (createdAt != null && !datePattern.matches(createdAt)) fail("createdAt 은 YYYY-MM-DD 형식이어야 해요.")
                    val period = text("period", 30, required = false)
                    if (period != null && !Regex("""^\d{4}\.\d{2}( - \d{4}\.\d{2})?$""").matches(period)) fail("period 는 YYYY.MM - YYYY.MM 형식이어야 해요.")

                    val code =
                        """
                        |    Feedback(
                        |        author = $author,
                        |        place = ${place.code},
                        |        relation = ${relation.code},
                        |        strength = ${kotlinString(strength)},
                        |        growth = ${growth?.let(::kotlinString) ?: "null"},
                        |        period = ${period?.let(::kotlinString) ?: "null"},
                        |    ),
                        """.trimMargin()
                    (createdAt ?: "") to code
                }.sortedByDescending { it.first }

        val output = outputDir.get().asFile
        output.deleteRecursively()
        output.resolve("junjange/dev/ui/model").apply { mkdirs() }.resolve("PeerFeedbacks.kt").writeText(
            buildString {
                appendLine("package junjange.dev.ui.model")
                appendLine()
                appendLine("val peerFeedbacks: List<Feedback> =")
                appendLine("    listOf(")
                entries.forEach { appendLine(it.second.prependIndent("    ")) }
                appendLine("    )")
            },
        )
    }
}

kotlin.sourceSets.named("wasmJsMain") {
    kotlin.srcDir(generateFeedbacks.map { it.outputs.files.singleFile })
}
