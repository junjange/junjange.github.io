# 조준장 이력서 자동 생성 시스템

JSON 데이터 + HTML 템플릿으로 이력서 PDF를 생성합니다. 폰트(Pretendard)를 PDF에 직접 임베드하므로, 한 번 셋업하면 **오프라인에서도 항상 동일한 결과**가 나옵니다.

## 📁 프로젝트 구조

```
resume/
├── data/
│   └── resume.json          # 이력서 데이터 (이것만 고치면 내용이 바뀜)
├── template/
│   └── resume.html          # HTML/CSS 이력서 템플릿 (디자인)
├── fonts/
│   ├── Pretendard-*.woff2    # 임베드용 폰트 (git에 포함)
│   └── LICENSE.txt           # Pretendard OFL 라이선스
├── scripts/
│   ├── generate-pdf.js      # PDF 생성 (폰트 base64 임베드 + 데이터 주입)
│   └── download-fonts.js    # 폰트 다운로드/갱신용
├── output/
│   └── 조준장_이력서.pdf    # 생성 결과물 (git에서 제외)
├── package.json
└── README.md
```

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

- **내용**: `data/resume.json` 수정 (이름, 경력, 프로젝트, 교육, 기술 역량 등)
- **디자인**: `template/resume.html`의 `<style>` 수정 (2단 레이아웃, 폰트 크기/여백 등)

수정 후 `npm run resume`을 다시 실행하면 됩니다.

## 🎯 동작 방식

1. `generate-pdf.js`가 `resume.json`을 읽어 HTML에 데이터를 주입
2. `fonts/`의 Pretendard woff2를 base64로 읽어 `@font-face`로 HTML에 임베드
3. Puppeteer(헤드리스 Chromium)로 렌더링 후 A4 PDF 출력 (페이지 번호는 footer로 자동 삽입)

- 폰트가 PDF에 직접 박히므로 외부 네트워크 요청이 없습니다.
- `output/` 디렉토리가 없으면 자동 생성됩니다.

## 🔧 기술 스택

- **Puppeteer**: 헤드리스 Chromium PDF 렌더링
- **Node.js**: 스크립트 실행
- **HTML/CSS + Pretendard**: 이력서 템플릿/폰트

## 📄 라이선스

- 프로젝트: MIT
- 폰트: Pretendard — SIL Open Font License 1.1 (`fonts/LICENSE.txt` 참고)
