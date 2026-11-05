package junjange.dev.ui.model

enum class Route(
    val hash: String,
) {
    Main(""),
    Feedback("#/feedback"),
    ;

    companion object {
        fun fromHash(hash: String): Route = entries.firstOrNull { it.hash.isNotEmpty() && it.hash == hash } ?: Main
    }
}
