# 조준장 이력서 자동 생성 시스템

**웹 포트폴리오(KMP)의 데이터를 단일 진실로 삼아** 이력서 PDF를 생성합니다.
KMP의 `strings.xml` / `model/*.kt`를 고치면 이력서에도 자동 반영됩니다 (데이터 중복 관리 없음).
폰트(Pretendard)를 PDF에 직접 임베드하므로, 한 번 셋업하면 **오프라인에서도 항상 동일한 결과**가 나옵니다.

## 📁 프로젝트 구조

```
resume/
├── data/
│   ├── resume.json          # ⚙️ 자동 생성물 (KMP에서 추출, git 제외 — 직접 수정 금지)
│   ├── resume.extra.json    # KMP에 없는 보강 데이터 (techSkills, 대학, 전화번호)
│   └── resume.manifest.json # 이력서에 넣을 KMP 항목/순서 정의
├── template/
│   └── resume.html          # HTML/CSS 이력서 템플릿 (디자인)
├── fonts/
│   ├── Pretendard-*.woff2    # 임베드용 폰트 (git에 포함)
│   └── LICENSE.txt           # Pretendard OFL 라이선스
├── scripts/
│   ├── export-from-kmp.js   # KMP(strings.xml + model/*.kt) → resume.json 생성
│   ├── generate-pdf.js      # resume.json + 템플릿 → PDF (폰트 base64 임베드)
│   └── download-fonts.js    # 폰트 다운로드/갱신용
├── output/
│   └── 조준장_이력서.pdf    # 생성 결과물 (git에서 제외)
├── package.json
└── README.md
```

## 🔗 데이터 단일 소스 (중요)

이력서 내용의 **진실은 웹 포트폴리오(KMP)** 입니다:
- 텍스트(제목·설명·기간·기술스택·기여): `composeApp/.../composeResources/values/strings.xml`
- 구조·순서·링크 URL·경력 기여: `composeApp/.../ui/model/*.kt`

`npm run resume`를 실행하면 `export-from-kmp.js`가 위 데이터를 읽어 `resume/data/resume.json`을
**자동 생성**한 뒤 PDF를 만듭니다. 따라서 `resume.json`은 직접 수정하지 마세요 (덮어써집니다).

KMP에 없는 항목(기술 역량, 대학 학력, 전화번호)만 `resume.extra.json`에서 관리하고,
이력서에 어떤 항목을 어떤 순서로 넣을지는 `resume.manifest.json`에서 정합니다.

## 🚀 사용 방법

루트(`junjange.github.io/`)에서 바로 실행할 수 있습니다.

```bash
npm run resume          # 이력서 PDF 생성 → resume/output/조준장_이력서.pdf
```

`resume/` 안에서 실행해도 됩니다: `cd resume && npm run resume`

### 최초 셋업 (클론 직후)

폰트는 저장소에 포함되어 있어 별도 작업이 필요 없습니다. Puppeteer만 설치하면 됩니다.

```bash
npm run resume:install   # = npm --prefix resume install (Puppeteer/Chromium 설치)
```

폰트를 다시 받거나 버전을 바꾸고 싶을 때만:

```bash
npm run resume:fonts     # Pretendard woff2 재다운로드 (이미 있으면 건너뜀)
```

## 📝 이력서 수정하기

- **대부분의 내용**(이름 외 경력·프로젝트·교육·커뮤니티·활동): KMP의 `strings.xml` / `model/*.kt`를 수정
- **KMP에 없는 항목**(기술 역량·대학·전화번호): `data/resume.extra.json` 수정
- **포함 항목/순서**: `data/resume.manifest.json` 수정
- **디자인**: `template/resume.html`의 `<style>` 수정 (2단 레이아웃, 폰트 크기/여백 등)

수정 후 `npm run resume`을 다시 실행하면 됩니다. (`data/resume.json`은 자동 생성물이라 직접 고치지 마세요.)

## 🎯 동작 방식

1. `export-from-kmp.js`가 KMP의 `strings.xml`과 `model/*.kt`를 파싱하고,
   `resume.extra.json`·`resume.manifest.json`을 머지해 `resume.json`을 생성
2. `generate-pdf.js`가 `resume.json`을 읽어 HTML에 데이터를 주입
3. `fonts/`의 Pretendard woff2를 base64로 읽어 `@font-face`로 HTML에 임베드
4. Puppeteer(헤드리스 Chromium)로 렌더링 후 A4 PDF 출력 (페이지 번호는 footer로 자동 삽입)

- `npm run resume` = `npm run export && npm run generate` (export → PDF)
- 폰트가 PDF에 직접 박히므로 외부 네트워크 요청이 없습니다.
- `output/` 디렉토리가 없으면 자동 생성됩니다.

## 🔧 기술 스택

- **Puppeteer**: 헤드리스 Chromium PDF 렌더링
- **Node.js**: 스크립트 실행
- **HTML/CSS + Pretendard**: 이력서 템플릿/폰트

## 📄 라이선스

- 프로젝트: MIT
- 폰트: Pretendard — SIL Open Font License 1.1 (`fonts/LICENSE.txt` 참고)
