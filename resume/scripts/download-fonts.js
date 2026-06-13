// Pretendard 폰트(woff2)를 fonts/ 디렉토리로 내려받습니다.
// 이미 받은 파일은 건너뜁니다. PDF는 이 폰트를 base64로 임베드하므로
// 한 번만 실행하면 이후로는 오프라인에서도 동일하게 생성됩니다.
const fs = require('fs');
const path = require('path');
const https = require('https');

const VERSION = '1.3.9';
const BASE = `https://cdn.jsdelivr.net/npm/pretendard@${VERSION}/dist/web/static/woff2`;
const WEIGHTS = ['Regular', 'Medium', 'SemiBold', 'Bold'];
const fontsDir = path.join(__dirname, '..', 'fonts');

function download(url, dest) {
    return new Promise((resolve, reject) => {
        const file = fs.createWriteStream(dest);
        https.get(url, res => {
            if (res.statusCode !== 200) {
                file.close();
                fs.unlink(dest, () => {});
                return reject(new Error(`${res.statusCode} ${url}`));
            }
            res.pipe(file);
            file.on('finish', () => file.close(resolve));
        }).on('error', err => {
            file.close();
            fs.unlink(dest, () => {});
            reject(err);
        });
    });
}

(async () => {
    console.log('🔤 Pretendard 폰트 다운로드 중...\n');
    if (!fs.existsSync(fontsDir)) fs.mkdirSync(fontsDir, { recursive: true });

    for (const w of WEIGHTS) {
        const name = `Pretendard-${w}.woff2`;
        const dest = path.join(fontsDir, name);
        if (fs.existsSync(dest) && fs.statSync(dest).size > 0) {
            console.log(`   • ${name} (이미 있음, 건너뜀)`);
            continue;
        }
        await download(`${BASE}/${name}`, dest);
        const kb = (fs.statSync(dest).size / 1024).toFixed(0);
        console.log(`   ✓ ${name} (${kb} KB)`);
    }
    console.log('\n🎉 폰트 준비 완료!\n');
})().catch(err => {
    console.error('❌ 폰트 다운로드 실패:', err.message);
    process.exit(1);
});
