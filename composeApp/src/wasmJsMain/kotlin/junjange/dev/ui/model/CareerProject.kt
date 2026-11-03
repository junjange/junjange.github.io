package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res.string
import junjange_dev.composeapp.generated.resources.career_lio_project1_title
import junjange_dev.composeapp.generated.resources.career_lio_project2_title
import junjange_dev.composeapp.generated.resources.career_lio_project3_title
import junjange_dev.composeapp.generated.resources.career_matrios_project1_title
import junjange_dev.composeapp.generated.resources.career_paytalab_common_title
import junjange_dev.composeapp.generated.resources.career_paytalab_stability_title
import junjange_dev.composeapp.generated.resources.career_paytalab_ui_title
import org.jetbrains.compose.resources.StringResource

data class CareerProject(
    val titleRes: StringResource,
    val periodRes: StringResource? = null,
    val contributions: List<String>,
) {
    companion object {
        val PAYTALAB_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_paytalab_stability_title,
                    contributions =
                        listOf(
                            "OS 프로세스 회수 대비 결제 복원으로 결제 자동 복구",
                            "서버 롤백에도 동작한 장바구니 유지 설계",
                            "현재 동작을 테스트로 고정한 뒤 결제 금액 로직을 도메인 계층으로 분리",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_ui_title,
                    contributions =
                        listOf(
                            "단위 테스트 기반으로 핵심 화면을 Compose로 전면 전환",
                            "사람과 AI가 같이 쓰는 디자인 시스템 구축",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_common_title,
                    contributions =
                        listOf(
                            "AI가 작성한 테스트를 신뢰할 수 있게 만드는 워크플로우 설계",
                            "개발자 없이도 이벤트를 검증하는 DevTools 구축",
                        ),
                ),
            )

        val LIO_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_lio_project3_title,
                    contributions =
                        listOf(
                            "TTS를 적용해 어르신의 설문 접근성 확보",
                            "감정 분석 결과를 그래프로 시각화해 설문 결과를 한눈에 볼 수 있게 구성",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_lio_project2_title,
                    contributions =
                        listOf(
                            "Flutter와 BLoC 패턴 기반 단방향 데이터 흐름 아키텍처 설계",
                            "Firebase Storage(사업자 정보)와 FCM(실시간 거래 알림) 연동",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_lio_project1_title,
                    contributions =
                        listOf(
                            "파일 탐색기 UI를 직접 구현하고, 선택한 파일에서 전화번호 파싱·추출",
                            "Background Service 기반으로 저장된 번호 자동 발신부터 통화 종료 제어까지 플로우 구축",
                        ),
                ),
            )

        val MATRIOS_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_matrios_project1_title,
                    contributions =
                        listOf(
                            "화상 강의 화면 위에 겹쳐 동작하는 Service 기반 Floating Widget 구현",
                            "AWS Cognito 연동으로 회원가입·로그인 인증 구현",
                            "UsageStatsManager로 강의 중 외부 앱 사용 여부 실시간 수집",
                        ),
                ),
            )
    }
}
