package junjange.dev.ui.model

import junjange_dev.composeapp.generated.resources.Res.string
import junjange_dev.composeapp.generated.resources.career_lio_project1_period
import junjange_dev.composeapp.generated.resources.career_lio_project1_tech_stack
import junjange_dev.composeapp.generated.resources.career_lio_project1_title
import junjange_dev.composeapp.generated.resources.career_lio_project2_period
import junjange_dev.composeapp.generated.resources.career_lio_project2_tech_stack
import junjange_dev.composeapp.generated.resources.career_lio_project2_title
import junjange_dev.composeapp.generated.resources.career_lio_project3_period
import junjange_dev.composeapp.generated.resources.career_lio_project3_tech_stack
import junjange_dev.composeapp.generated.resources.career_lio_project3_title
import junjange_dev.composeapp.generated.resources.career_matrios_project1_period
import junjange_dev.composeapp.generated.resources.career_matrios_project1_tech_stack
import junjange_dev.composeapp.generated.resources.career_matrios_project1_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_desc
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project2_desc
import junjange_dev.composeapp.generated.resources.career_paytalab_project2_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project2_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project3_desc
import junjange_dev.composeapp.generated.resources.career_paytalab_project3_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project3_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project4_desc
import junjange_dev.composeapp.generated.resources.career_paytalab_project4_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project4_title
import org.jetbrains.compose.resources.StringResource

data class CareerProject(
    val titleRes: StringResource,
    val periodRes: StringResource?,
    val techStackRes: StringResource?,
    val descRes: StringResource? = null,
    val contributions: List<ContributionItem>,
) {
    companion object {
        val PAYTALAB_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_paytalab_project3_title,
                    periodRes = null,
                    techStackRes = string.career_paytalab_project3_tech_stack,
                    descRes = string.career_paytalab_project3_desc,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("NICE 간편결제 킬 이슈", true),
                                        TextSegment(" 해결 (프로세스 종료 시 결제 상태 복원) - 결제 성공률 "),
                                        TextSegment("90.3% → 91.1%", true),
                                        TextSegment(" 개선(실패율 약 8% 감소), 결제 끊김 관련 VOC 사실상 소멸"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("OCR 카드 등록 프로세스", true),
                                        TextSegment(" 고도화 - 완료율 "),
                                        TextSegment("87%", true),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_project2_title,
                    periodRes = null,
                    techStackRes = string.career_paytalab_project2_tech_stack,
                    descRes = string.career_paytalab_project2_desc,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("장바구니 유지 기능", true),
                                        TextSegment(" 구현 - 이탈 유저 결제 복귀 흐름 설계"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("매장 상세 XML → Compose", true),
                                        TextSegment(" 점진 마이그레이션"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("스크롤 리컴포지션 "),
                                        TextSegment("88% 감소", true),
                                        TextSegment(" (115 → 13회, 상태 분리·불변 파라미터)"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Passorder Design System", true),
                                        TextSegment(" 구축 주도"),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_project4_title,
                    periodRes = null,
                    techStackRes = string.career_paytalab_project4_tech_stack,
                    descRes = string.career_paytalab_project4_desc,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("선물하기 지원금 신규 기능", true),
                                        TextSegment(" 설계·구현 (Fragment + Compose 점진 전환)"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("입사 첫 프로젝트, 레거시 위에서 "),
                                        TextSegment("크래시·VOC 0건", true),
                                        TextSegment(" 출시"),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_project1_title,
                    periodRes = null,
                    techStackRes = null,
                    descRes = string.career_paytalab_project1_desc,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("자체 MVI 아키텍처", true),
                                        TextSegment(" 설계·도입 (외부 의존성 없이 팀 공통 베이스로 정착)"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("AI 기반 테스트 자산화 워크플로우", true),
                                        TextSegment(" 구축 (테스트 작성 원칙 수립)"),
                                    ),
                            ),
                        ),
                ),
            )

        val LIO_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_lio_project3_title,
                    periodRes = string.career_lio_project3_period,
                    techStackRes = string.career_lio_project3_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("TTS 접근성", true),
                                        TextSegment(" 지원 (어르신 설문 접근성 개선)"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("감정 분석 결과 그래프 시각화", true),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_lio_project2_title,
                    periodRes = string.career_lio_project2_period,
                    techStackRes = string.career_lio_project2_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("BLoC 패턴", true),
                                        TextSegment(" 기반 아키텍처 설계"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Firebase Storage·Cloud Messaging", true),
                                        TextSegment(" 연동 (정보 저장·실시간 알림)"),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_lio_project1_title,
                    periodRes = string.career_lio_project1_period,
                    techStackRes = string.career_lio_project1_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("파일 탐색기 UI", true),
                                        TextSegment(" 직접 구현 (파일에서 전화번호 추출)"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Service 기반 자동 발신", true),
                                        TextSegment(" 기능 개발"),
                                    ),
                            ),
                        ),
                ),
            )

        val MATRIOS_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_matrios_project1_title,
                    periodRes = string.career_matrios_project1_period,
                    techStackRes = string.career_matrios_project1_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Service 기반 Floating Widget", true),
                                        TextSegment(" 구현"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("AWS Cognito 인증 시스템", true),
                                        TextSegment(" 구축"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("UsageStatsManager", true),
                                        TextSegment(" 외부 앱 사용 모니터링"),
                                    ),
                            ),
                        ),
                ),
            )
    }
}

data class TextSegment(
    val text: String,
    val isHighlighted: Boolean = false,
)

data class ContributionItem(
    val segments: List<TextSegment>,
)
