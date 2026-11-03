package junjange.dev.ui.model

fun currentYear(): Int = js("new Date().getFullYear()")

private fun currentMonth(): Int = js("new Date().getMonth() + 1")

private fun currentDay(): Int = js("new Date().getDate()")

/** 재직 중인 경력의 입사일. 오늘 날짜(브라우저 기준)까지 채운 개월 수로 재직 기간을 계산한다. */
data class JoinDate(
    val year: Int,
    val month: Int,
    val day: Int,
) {
    fun elapsedMonths(): Int {
        var months = (currentYear() - year) * 12 + (currentMonth() - month)
        if (currentDay() < day) months -= 1
        return months.coerceAtLeast(0)
    }
}

/** 개월 수를 "1년 3개월" 형태로 바꾼다. 1개월 미만은 "1개월"로 표기한다. */
fun formatTenure(totalMonths: Int): String {
    val years = totalMonths / 12
    val months = totalMonths % 12
    return when {
        years == 0 -> "${months.coerceAtLeast(1)}개월"
        months == 0 -> "${years}년"
        else -> "${years}년 ${months}개월"
    }
}
