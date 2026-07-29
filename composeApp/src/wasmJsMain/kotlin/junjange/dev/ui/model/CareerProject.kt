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
                                        TextSegment("결제 중 OS의 "),
                                        TextSegment("프로세스 강제 종료", true),
                                        TextSegment("로 결제가 끊기던 NICE 간편 결제 킬 이슈를, 재생성 시 "),
                                        TextSegment("WebView 상태와 결제 단계를 보존·복원", true),
                                        TextSegment("하도록 설계해 결제 실패율을 "),
                                        TextSegment("76% 감소", true),
                                        TextSegment("시킴"),
                                    ),
                                webCompact =
                                    listOf(
                                        TextSegment("결제 도중 OS가 "),
                                        TextSegment("프로세스를 강제 종료", true),
                                        TextSegment("하면 결제가 끊기던 NICE 간편 결제 킬 이슈를 해결하기 위해, 재생성 시점에 "),
                                        TextSegment("WebView 상태와 결제 단계를 보존·복원", true),
                                        TextSegment("하도록 설계하여 중단된 결제를 끊김 없이 이어가도록 개선함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("OCR 카드 등록 프로세스를 고도화하여 스캔 확인 단계 유저의 최종 등록 "),
                                        TextSegment("완료율 87%", true),
                                        TextSegment("를 달성함"),
                                    ),
                                webCompact =
                                    listOf(
                                        TextSegment("스캔 확인 단계에서 유저가 카드 등록을 포기하던 문제를 줄이고자 "),
                                        TextSegment("OCR 카드 등록 프로세스를 고도화", true),
                                        TextSegment("하여 등록 완주까지의 이탈을 개선함"),
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
                                        TextSegment("장바구니 유지", true),
                                        TextSegment(" 기능을 구현하여 앱 재접속 시 주문 내역을 보존하고 플로팅 버튼·앱푸시 퍼널로 이탈 유저의 결제 복귀 흐름을 설계하여, 기여 매출의 "),
                                        TextSegment("약 70%가 순증분 매출", true),
                                        TextSegment("로 측정되며 결제 전환에 유의미하게 기여함"),
                                    ),
                                webCompact =
                                    listOf(
                                        TextSegment("장바구니 유지", true),
                                        TextSegment(" 기능을 구현하여 앱 재접속 시 주문 내역을 보존하고 플로팅 버튼·앱푸시 퍼널로 이탈 유저의 결제 복귀 흐름을 설계해 결제 전환에 기여함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("가장 복잡한 핵심 화면인 매장 상세를 "),
                                        TextSegment("XML에서 Compose로 점진적 마이그레이션", true),
                                        TextSegment("하며 상태 관리·레이어 구조까지 재설계하여 코드 복잡도와 렌더링·스크롤 성능을 개선함"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Passorder Design System", true),
                                        TextSegment(" 구축을 주도하여 UI를 컴포넌트화하고, 디자인-개발 공통 언어로 UI 일관성과 개발 속도를 확보함"),
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
                                        TextSegment("선물하기 지원금", true),
                                        TextSegment(" 신규 기능을 레거시 코드베이스 위에서 설계·구현하고, 기존 Fragment 화면을 "),
                                        TextSegment("Compose", true),
                                        TextSegment("와 조합해 점진적으로 전환하며 상태 관리를 개선함"),
                                    ),
                                webCompact =
                                    listOf(
                                        TextSegment("선물하기 지원금", true),
                                        TextSegment(" 신규 기능을 레거시 코드베이스 위에서 설계·구현하고, 기존 Fragment 화면을 "),
                                        TextSegment("Compose", true),
                                        TextSegment("와 조합해 점진적으로 전환하며 상태 관리를 개선하여, 배포 후 "),
                                        TextSegment("선물 발송·신규 가입 지표 성장", true),
                                        TextSegment("을 견인함"),
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
                                // 성과 수치 항목: 위 기능 구현 항목의 webCompact에 R(결과)로 흡수시켜 웹에서는 숨긴다.
                                // segments(원문 수치)는 이력서 PDF에 그대로 유지된다.
                                webCompact = emptyList(),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("입사 후 첫 프로젝트임에도 배포 후 "),
                                        TextSegment("크래시·VOC 0건", true),
                                        TextSegment("으로 안정적으로 출시함"),
                                    ),
                                webCompact =
                                    listOf(
                                        TextSegment("입사 후 첫 프로젝트이자 레거시 코드베이스였음에도, 기존 Fragment 화면을 "),
                                        TextSegment("Compose로 점진적으로 전환", true),
                                        TextSegment("하고 상태 관리를 정비하여 크래시 없이 안정적으로 출시함"),
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
                                        TextSegment("외부 라이브러리 의존성 없는 "),
                                        TextSegment("자체 MVI 아키텍처", true),
                                        TextSegment("를 직접 설계 및 도입하여 팀 내 유지보수 효율을 높임"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("Android 하네스에 "),
                                        TextSegment("AI 기반 테스트 자산화 워크플로우", true),
                                        TextSegment("를 구축하고 비즈니스 로직·Hotfix 대응에 대한 "),
                                        TextSegment("테스트 코드 작성 원칙", true),
                                        TextSegment("을 수립하여 반복 작업 자동화와 코드·배포 안정성을 확보함"),
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
                                        TextSegment("Firebase", true),
                                        TextSegment("를 활용해 사업자 정보 저장(Storage)과 실시간 알림(Cloud Messaging) 기능을 구현"),
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
                                        TextSegment("를 직접 설계·구현하고, 선택한 파일에서 "),
                                        TextSegment("전화번호를 추출", true),
                                        TextSegment("하는 기능 개발"),
                                    ),
                            ),
                            ContributionItem(
                                segments =
                                    listOf(
                                        TextSegment("저장된 예약 번호 기반의 "),
                                        TextSegment("자동 발신 기능", true),
                                        TextSegment("을 구현하고, "),
                                        TextSegment("Service", true),
                                        TextSegment("를 활용해 발신 중인 전화를 종료하는 흐름까지 개발"),
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
    // 웹 전용 컴팩트 버전(정량 수치 제거). null이면 웹도 segments를 그대로 사용한다.
    // 이력서/포트폴리오 PDF export는 항상 segments(수치 포함 원문)만 파싱한다.
    val webCompact: List<TextSegment>? = null,
)
