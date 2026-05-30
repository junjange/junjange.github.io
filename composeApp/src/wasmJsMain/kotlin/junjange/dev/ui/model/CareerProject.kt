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
import junjange_dev.composeapp.generated.resources.career_paytalab_project1_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project2_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project2_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project3_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project3_title
import junjange_dev.composeapp.generated.resources.career_paytalab_project4_tech_stack
import junjange_dev.composeapp.generated.resources.career_paytalab_project4_title
import org.jetbrains.compose.resources.StringResource

data class CareerProject(
    val titleRes: StringResource,
    val periodRes: StringResource?,
    val techStackRes: StringResource?,
    val contributions: List<ContributionItem>,
) {
    companion object {
        val PAYTALAB_PROJECTS =
            listOf(
                CareerProject(
                    titleRes = string.career_paytalab_project4_title,
                    periodRes = null,
                    techStackRes = string.career_paytalab_project4_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("레거시 환경에서 "),
                                        TextSegment("선물하기 지원금", true),
                                        TextSegment(" 기능을 안정적으로 설계 및 구현하여 서비스 성장 기반을 마련함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("기능 배포 후 일평균 선물 발송 건수 "),
                                        TextSegment("398%", true),
                                        TextSegment(", 신규 가입자 "),
                                        TextSegment("567% 증가", true),
                                        TextSegment("라는 지표 성장을 견인함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("선물 수신자의 이용 흐름과 "),
                                        TextSegment("UI/UX 최적화", true),
                                        TextSegment("를 통해 신규 유저가 서비스에 원활하게 안착하도록 기여함"),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_project3_title,
                    periodRes = null,
                    techStackRes = string.career_paytalab_project3_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("OCR 카드 등록 프로세스를 고도화하여 스캔 확인 단계 유저의 최종 등록 "),
                                        TextSegment("완료율 87%", true),
                                        TextSegment("를 달성함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("NICE 간편 결제 프로세스 킬 이슈를 해결하여 결제 실패율을 "),
                                        TextSegment("76% 감소", true),
                                        TextSegment("시키고 서비스 안정성을 강화함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("카드 등록 과정의 "),
                                        TextSegment("UX 디테일 개선", true),
                                        TextSegment("으로 결제 진입 장벽을 낮추고 유저 전환 경험을 강화함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("법인카드 결제 수단을 신규 추가하여 서비스 결제 범위를 "),
                                        TextSegment("B2B 영역", true),
                                        TextSegment("으로 확장하고 유저 니즈를 충족함"),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_project2_title,
                    periodRes = null,
                    techStackRes = string.career_paytalab_project2_tech_stack,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Passorder Design System", true),
                                        TextSegment("을 구축하여 서비스 전반의 UI 요소를 컴포넌트화하고 코드 일관성을 확보함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("매장 상세 화면을 "),
                                        TextSegment("XML에서 Compose로 마이그레이션", true),
                                        TextSegment("하고 전체 아키텍처를 개선하여 코드 간결성과 성능을 최적화함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("장바구니 유지", true),
                                        TextSegment(" 기능을 구현하여 앱 재접속 시 유저의 주문 내역을 보존하고 결제 진입 과정의 이탈을 방지함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("사용자 편의 중심의 "),
                                        TextSegment("인터페이스 고도화", true),
                                        TextSegment(" 등 다수의 개선 과제를 수행하여 서비스 전반의 사용성을 최적화함"),
                                    ),
                            ),
                        ),
                ),
                CareerProject(
                    titleRes = string.career_paytalab_project1_title,
                    periodRes = null,
                    techStackRes = null,
                    contributions =
                        listOf(
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("외부 라이브러리 의존성 없는 "),
                                        TextSegment("자체 MVI 아키텍처", true),
                                        TextSegment("를 직접 설계 및 도입하여 팀 내 유지보수 효율을 높임"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("AI 주도 개발 프로세스", true),
                                        TextSegment("를 도입하여 반복 작업 자동화 및 개발 생산성을 향상시킴"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("네트워크 에러 시 "),
                                        TextSegment("재시도 프로세스", true),
                                        TextSegment(" 구현을 선제적으로 제안하여 결제 단계에서의 유저 이탈을 방지함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("비즈니스 로직 및 Hotfix 대응 건에 대한 "),
                                        TextSegment("테스트 코드 작성 원칙", true),
                                        TextSegment("을 수립하여 코드 안정성과 배포 신뢰성을 확보함"),
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
                                        TextSegment("TTS 기능", true),
                                        TextSegment("을 적용해 어르신들이 설문에 쉽게 접근할 수 있도록 지원"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("온보딩 UI", true),
                                        TextSegment("를 구현해 서비스 이용 흐름을 직관적으로 안내"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("감정 분석 결과를 "),
                                        TextSegment("그래프 시각화", true),
                                        TextSegment("로 제공해 사용자 이해도 향상"),
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
                                        TextSegment("을 적용해 확장성과 유지보수성을 고려한 아키텍처 설계"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Firebase Storage", true),
                                        TextSegment("를 활용해 사업자 정보를 안전하게 저장하는 기능 구현"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Firebase Cloud Messaging", true),
                                        TextSegment("으로 실시간 알림 기능 개발"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Google Play와 Apple App Store", true),
                                        TextSegment("에 앱 배포"),
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
                                        TextSegment("예약 전화를 위한 "),
                                        TextSegment("파일 탐색기 UI", true),
                                        TextSegment("를 직접 설계 및 구현"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("전화번호를 추출하여 "),
                                        TextSegment("Room DB", true),
                                        TextSegment("에 저장하는 기능 개발"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("저장된 예약 번호를 기반으로 "),
                                        TextSegment("자동 발신 기능", true),
                                        TextSegment(" 구현"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Service", true),
                                        TextSegment("를 활용해 자동 발신 중인 전화를 종료하는 기능 개발"),
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
                                        TextSegment("화상 강의 중에도 앱을 원활히 이용할 수 있도록 "),
                                        TextSegment("Service 기반 Floating Widget", true),
                                        TextSegment(" 구현"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("AWS Cognito", true),
                                        TextSegment("를 활용한 인증 시스템 구축으로 보안 강화 및 회원가입·로그인 절차 간소화"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("UsageStatsManager", true),
                                        TextSegment("를 적용해 외부 앱 사용 여부를 실시간 모니터링하고 사용자 활동 분석 지원"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("튜토리얼 페이지", true),
                                        TextSegment("를 통해 신규 사용자 온보딩 개발 및 서비스 이해도·만족도 향상"),
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
