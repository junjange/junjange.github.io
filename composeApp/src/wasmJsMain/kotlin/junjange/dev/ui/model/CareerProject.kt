package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res.string
import junjange_dev.composeapp.generated.resources.career_lio_project1_period
import junjange_dev.composeapp.generated.resources.career_lio_project1_title
import junjange_dev.composeapp.generated.resources.career_lio_project2_period
import junjange_dev.composeapp.generated.resources.career_lio_project2_title
import junjange_dev.composeapp.generated.resources.career_lio_project3_period
import junjange_dev.composeapp.generated.resources.career_lio_project3_title
import junjange_dev.composeapp.generated.resources.career_matrios_project1_period
import junjange_dev.composeapp.generated.resources.career_matrios_project1_title
import junjange_dev.composeapp.generated.resources.career_paytalab_boss_title
import junjange_dev.composeapp.generated.resources.career_paytalab_common_title
import junjange_dev.composeapp.generated.resources.career_paytalab_content_title
import junjange_dev.composeapp.generated.resources.career_paytalab_gift_title
import junjange_dev.composeapp.generated.resources.career_paytalab_payment_title
import junjange_dev.composeapp.generated.resources.career_paytalab_ux_title
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
                    titleRes = string.career_paytalab_payment_title,
                    contributions =
                        listOf(
                            "OS 프로세스 회수 대비 결제 복원 구조 구축",
                            "OCR 기반 카드 등록 프로세스 재구축",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_ux_title,
                    contributions =
                        listOf(
                            "장바구니 유지 기능 신규 개발",
                            "매장상세 화면 XML → Compose 점진 마이그레이션 및 레거시 제거",
                            "Passorder Design System 구축",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_content_title,
                    contributions =
                        listOf(
                            "레거시 가격 산정 로직의 도메인 분리",
                            "외부 콘텐츠 탐색 도메인 신규 구축",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_gift_title,
                    contributions =
                        listOf(
                            "레거시 코드베이스 기반 신규 기능 구현",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_boss_title,
                    contributions =
                        listOf(
                            "주문 수신 Socket 유실로 인한 주문 미수신·자동취소 장애 해결",
                            "영수증 프린터 제어코드 기반 프로모션 인쇄 구조 구축",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_common_title,
                    contributions =
                        listOf(
                            "라이브러리 없는 MVI 구조 제안·도입 및 파트 표준화",
                            "AI가 작성한 테스트를 신뢰할 수 있게 만드는 워크플로우 설계",
                            "계측 검증용 DevTools 실시간 로그 뷰어 구축",
                        ),
                ),
            )

        val LIO_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_lio_project3_title,
                    periodRes = string.career_lio_project3_period,
                    contributions =
                        listOf(
                            "TTS를 적용해 어르신의 설문 접근성 확보",
                            "감정 분석 결과를 그래프로 시각화",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_lio_project2_title,
                    periodRes = string.career_lio_project2_period,
                    contributions =
                        listOf(
                            "Flutter와 BLoC 패턴 기반 단방향 데이터 흐름 아키텍처 설계",
                            "Firebase Storage·FCM 연동 (사업자 정보·실시간 거래 알림)",
                        ),
                ),
                CareerProject(
                    titleRes = string.career_lio_project1_title,
                    periodRes = string.career_lio_project1_period,
                    contributions =
                        listOf(
                            "파일 탐색기 UI 직접 구현 및 선택 파일에서 전화번호 파싱",
                            "Background Service 기반 자동 발신·통화 종료 제어 플로우 구축",
                        ),
                ),
            )

        val MATRIOS_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_matrios_project1_title,
                    periodRes = string.career_matrios_project1_period,
                    contributions =
                        listOf(
                            "화상 강의 화면 위에 겹쳐 동작하는 Floating Widget을 Service 기반으로 구현",
                            "AWS Cognito 연동 회원가입·로그인 인증 구현",
                            "UsageStatsManager로 강의 중 외부 앱 사용 여부 실시간 수집",
                        ),
                ),
            )
    }
}
