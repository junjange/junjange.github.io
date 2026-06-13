const puppeteer = require('puppeteer');
const path = require('path');
const fs = require('fs');

async function generateResumePDF() {
    console.log('📄 이력서 PDF 생성을 시작합니다...\n');

    // 출력 디렉토리 확인 및 생성
    const outputDir = path.join(__dirname, '..', 'output');
    if (!fs.existsSync(outputDir)) {
        fs.mkdirSync(outputDir, { recursive: true });
    }

    // JSON 데이터 로드
    console.log('📂 이력서 데이터 로드 중...');
    const dataPath = path.join(__dirname, '..', 'data', 'resume.json');
    const resumeData = JSON.parse(fs.readFileSync(dataPath, 'utf-8'));
    console.log('   ✓ 데이터 로드 완료\n');

    // HTML 템플릿 로드
    console.log('📂 HTML 템플릿 로드 중...');
    const templatePath = path.join(__dirname, '..', 'template', 'resume.html');
    let htmlContent = fs.readFileSync(templatePath, 'utf-8');
    console.log('   ✓ 템플릿 로드 완료\n');

    // Pretendard 폰트를 base64로 임베드 (오프라인/경로 독립)
    console.log('🔤 Pretendard 폰트 임베드 중...');
    const fontFaceCss = buildFontFaceCss(path.join(__dirname, '..', 'fonts'));
    htmlContent = htmlContent.replace(
        '<!-- @font-face (Pretendard) is injected by generate-pdf.js -->',
        `<style>\n${fontFaceCss}\n    </style>`
    );
    console.log('   ✓ 폰트 임베드 완료\n');

    // HTML에 데이터 주입
    console.log('🔧 데이터를 HTML에 주입 중...');
    htmlContent = htmlContent.replace(
        '<script>',
        `<script>
        const resumeData = ${JSON.stringify(resumeData, null, 2)};
        `
    );
    console.log('   ✓ 데이터 주입 완료\n');

    // 브라우저 실행
    const browser = await puppeteer.launch({
        headless: 'new',
        args: ['--no-sandbox', '--disable-setuid-sandbox']
    });

    try {
        const page = await browser.newPage();

        console.log('🌐 HTML 렌더링 중...');

        // HTML 콘텐츠 직접 설정
        await page.setContent(htmlContent, {
            waitUntil: 'networkidle0'
        });

        // 웹폰트(Pretendard) 로딩 완료 대기
        await page.evaluateHandle('document.fonts.ready');

        // 약간의 대기 시간 (폰트 렌더링 안정화)
        await new Promise(resolve => setTimeout(resolve, 1000));

        const outputPath = path.join(outputDir, '조준장_이력서.pdf');

        console.log('🖨️  PDF 생성 중...\n');

        // PDF 생성
        await page.pdf({
            path: outputPath,
            format: 'A4',
            margin: {
                top: '13mm',
                right: '18mm',
                bottom: '11mm',
                left: '18mm'
            },
            printBackground: true,
            preferCSSPageSize: false,
            displayHeaderFooter: true,
            headerTemplate: '<span></span>',
            footerTemplate: `
                <div style="width: 100%; text-align: center; font-size: 8pt; color: #666;">
                    - <span class="pageNumber"></span> -
                </div>
            `
        });

        console.log('✅ PDF 생성 완료!');
        console.log(`   저장 위치: ${outputPath}\n`);

        // 파일 크기 출력
        const stats = fs.statSync(outputPath);
        const fileSizeInMB = (stats.size / (1024 * 1024)).toFixed(2);
        console.log(`📊 파일 크기: ${fileSizeInMB} MB\n`);

    } catch (error) {
        console.error('❌ 오류 발생:', error);
        process.exit(1);
    } finally {
        await browser.close();
    }
}

// fonts/ 디렉토리의 woff2 파일들을 base64 @font-face CSS로 변환
function buildFontFaceCss(fontsDir) {
    const faces = [
        { file: 'Pretendard-Regular.woff2', weight: 400 },
        { file: 'Pretendard-Medium.woff2', weight: 500 },
        { file: 'Pretendard-SemiBold.woff2', weight: 600 },
        { file: 'Pretendard-Bold.woff2', weight: 700 },
    ];

    return faces
        .map(({ file, weight }) => {
            const fontPath = path.join(fontsDir, file);
            if (!fs.existsSync(fontPath)) {
                throw new Error(`폰트 파일을 찾을 수 없습니다: ${fontPath}\n   → 'npm run resume:fonts'로 폰트를 내려받으세요.`);
            }
            const base64 = fs.readFileSync(fontPath).toString('base64');
            return `        @font-face {
            font-family: 'Pretendard';
            font-weight: ${weight};
            font-style: normal;
            font-display: block;
            src: url(data:font/woff2;base64,${base64}) format('woff2');
        }`;
        })
        .join('\n');
}

// 스크립트 실행
generateResumePDF()
    .then(() => {
        console.log('🎉 이력서 생성 프로세스 완료!\n');
        process.exit(0);
    })
    .catch(error => {
        console.error('❌ 치명적 오류:', error);
        process.exit(1);
    });
