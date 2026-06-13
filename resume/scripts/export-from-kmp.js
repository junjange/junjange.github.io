// KMP 모델(단일 진실)에서 이력서 데이터를 추출해 resume/data/resume.json을 생성합니다.
//
// 데이터 출처:
//   - 텍스트:   composeApp/.../composeResources/values/strings.xml
//   - 구조/순서/링크/contributions: composeApp/.../ui/model/*.kt
//   - 보강(KMP에 없음): resume/data/resume.extra.json (techSkills, 대학, 전화번호 등)
//   - 포함 항목/순서: resume/data/resume.manifest.json
//
// 이 스크립트가 생성하는 resume/data/resume.json은 "자동 생성물"입니다. 직접 수정하지 마세요.
// 내용을 바꾸려면 KMP의 strings.xml / model 코드를, 보강 데이터는 resume.extra.json을 수정하세요.

const fs = require('fs');
const path = require('path');

const ROOT = path.join(__dirname, '..', '..');
const KMP = path.join(ROOT, 'composeApp', 'src');
const STRINGS_XML = path.join(KMP, 'commonMain', 'composeResources', 'values', 'strings.xml');
const MODEL_DIR = path.join(KMP, 'wasmJsMain', 'kotlin', 'junjange', 'dev', 'ui', 'model');

const DATA_DIR = path.join(__dirname, '..', 'data');
const EXTRA_PATH = path.join(DATA_DIR, 'resume.extra.json');
const MANIFEST_PATH = path.join(DATA_DIR, 'resume.manifest.json');
const OUT_PATH = path.join(DATA_DIR, 'resume.json');

// ───────────────────────── 1. strings.xml 파서 ─────────────────────────
function parseStrings() {
    const xml = fs.readFileSync(STRINGS_XML, 'utf-8');
    const map = {};
    // <string name="key">value</string> (멀티라인 허용)
    const re = /<string\s+name="([^"]+)"\s*>([\s\S]*?)<\/string>/g;
    let m;
    while ((m = re.exec(xml)) !== null) {
        const key = m[1];
        let val = m[2];
        // XML 엔티티 복원
        val = val
            .replace(/&lt;/g, '<')
            .replace(/&gt;/g, '>')
            .replace(/&amp;/g, '&')
            .replace(/&quot;/g, '"')
            .replace(/&apos;/g, "'");
        // 리터럴 \n → 실제 줄바꿈
        val = val.replace(/\\n/g, '\n');
        map[key] = val.trim();
    }
    return map;
}

function s(strings, key) {
    if (!(key in strings)) throw new Error(`strings.xml에 키가 없습니다: ${key}`);
    return strings[key];
}

// ──────────────────── 2. .kt 모델에서 enum 블록 추출 유틸 ────────────────────
function readModel(file) {
    return fs.readFileSync(path.join(MODEL_DIR, file), 'utf-8');
}

// "Res.string.foo" 또는 "string.foo" → "foo"
function stripStringRef(ref) {
    const m = ref.match(/(?:Res\.)?string\.([A-Za-z0-9_]+)/);
    return m ? m[1] : null;
}

// ──────────────── 3. CareerProject.kt: 경력 프로젝트 + contributions ────────────────
// 각 *_PROJECTS 리스트의 CareerProject 블록을 순서대로 파싱.
function parseCareerProjects() {
    const src = readModel('CareerProject.kt');
    const result = {}; // { PAYTALAB: [ {titleKey, periodKey, techKey, contributions[]}, ... ], ... }

    for (const listName of ['PAYTALAB_PROJECTS', 'LIO_PROJECTS', 'MATRIOS_PROJECTS']) {
        const company = listName.replace('_PROJECTS', '');
        // 리스트 시작 위치
        const startIdx = src.indexOf(`val ${listName}`);
        if (startIdx === -1) throw new Error(`${listName}을 찾을 수 없습니다`);
        // 다음 val ... _PROJECTS 또는 companion 끝까지를 범위로
        const rest = src.slice(startIdx);
        // 이 리스트의 끝 = 다음 "val XXX_PROJECTS" 전까지 (마지막은 companion 닫힘)
        const nextIdx = rest.slice(10).search(/val [A-Z_]+_PROJECTS\b/);
        const block = nextIdx === -1 ? rest : rest.slice(0, nextIdx + 10);

        // CareerProject( ... ) 단위로 분리: titleRes 기준으로 나눔
        const projects = [];
        const projRe = /CareerProject\(([\s\S]*?contributions\s*=\s*\n?\s*listOf\(([\s\S]*?)\),?\s*\n\s*\),)/g;
        // 위 정규식은 중첩 괄호로 취약 → 더 단순하게 titleRes/periodRes/techStackRes + contributions 텍스트만 추출
        // 각 CareerProject는 titleRes로 시작한다고 가정하고 titleRes 위치로 분할
        const titleRe = /titleRes\s*=\s*(string\.[A-Za-z0-9_]+)/g;
        const indices = [];
        let tm;
        while ((tm = titleRe.exec(block)) !== null) {
            indices.push({ idx: tm.index, titleKey: stripStringRef(tm[1]) });
        }
        for (let i = 0; i < indices.length; i++) {
            const segStart = indices[i].idx;
            const segEnd = i + 1 < indices.length ? indices[i + 1].idx : block.length;
            const seg = block.slice(segStart, segEnd);

            const periodMatch = seg.match(/periodRes\s*=\s*(string\.[A-Za-z0-9_]+|null)/);
            const techMatch = seg.match(/techStackRes\s*=\s*(string\.[A-Za-z0-9_]+|null)/);
            const periodKey = periodMatch && periodMatch[1] !== 'null' ? stripStringRef(periodMatch[1]) : null;
            const techKey = techMatch && techMatch[1] !== 'null' ? stripStringRef(techMatch[1]) : null;

            // contributions: ContributionItem 단위로, 그 안의 TextSegment("..."[, true]) text만 이어붙임
            const contributions = [];
            const itemRe = /ContributionItem\(([\s\S]*?)\),\s*(?=ContributionItem\(|\)\s*,?\s*$|\),\s*\n)/g;
            // 위도 취약 → segments 안의 TextSegment를 ContributionItem 경계로 묶기 위해 단순 분할 사용
            const itemBlocks = seg.split('ContributionItem(').slice(1);
            for (const ib of itemBlocks) {
                // 이 item의 segments 영역만: 첫 번째 매칭되는 segments = listOf( ... ) 내부
                const segTexts = [];
                const tsRe = /TextSegment\(\s*"((?:[^"\\]|\\.)*)"/g;
                // ContributionItem 경계 보호: 다음 ContributionItem이 같은 블록에 안 들어오도록 첫 ")," 묶음까지만
                // 안전하게 이 item 블록 전체에서 TextSegment를 뽑되, 다음 item은 split으로 이미 분리됨
                let sm;
                while ((sm = tsRe.exec(ib)) !== null) {
                    segTexts.push(sm[1].replace(/\\"/g, '"').replace(/\\n/g, '\n'));
                }
                if (segTexts.length) contributions.push(segTexts.join(''));
            }

            projects.push({
                titleKey: indices[i].titleKey,
                periodKey,
                techKey,
                contributions,
            });
        }
        result[company] = projects;
    }
    return result;
}

// ──────────────── 4. Project.kt: 포트폴리오 프로젝트 링크 URL + 순서 ────────────────
function parseProjectLinks() {
    const src = readModel('Project.kt');
    // enum 본문(첫 "{" 이후)에서 각 enum 상수 블록 추출
    const enumStart = src.indexOf('enum class Project');
    const body = src.slice(src.indexOf('{', enumStart) + 1);

    const result = {}; // { LUCKY_LOTTERY: { links: [{titleKey, url}], titleKey, subtitleKey, ... }, ... }
    // enum 상수: 대문자/언더스코어 이름 바로 뒤 "(" 로 시작
    const constRe = /(^|\n)\s*([A-Z][A-Z0-9_]+)\(/g;
    const consts = [];
    let cm;
    while ((cm = constRe.exec(body)) !== null) {
        consts.push({ name: cm[2], idx: cm.index });
    }
    for (let i = 0; i < consts.length; i++) {
        const segStart = consts[i].idx;
        const segEnd = i + 1 < consts.length ? consts[i + 1].idx : body.length;
        const seg = body.slice(segStart, segEnd);

        const get = (field) => {
            const mm = seg.match(new RegExp(`${field}\\s*=\\s*(?:Res\\.)?string\\.([A-Za-z0-9_]+)`));
            return mm ? mm[1] : null;
        };

        // links: "Res.string.github to \"https://...\"" 또는 "string.x to \"...\""
        const links = [];
        const linkRe = /(?:Res\.)?string\.([A-Za-z0-9_]+)\s+to\s+"([^"]+)"/g;
        let lm;
        while ((lm = linkRe.exec(seg)) !== null) {
            links.push({ titleKey: lm[1], url: lm[2] });
        }

        result[consts[i].name] = {
            titleKey: get('titleRes'),
            subtitleKey: get('subtitleRes'),
            periodKey: get('periodRes'),
            descriptionKey: get('descriptionRes'),
            techStackKey: get('techStackRes'),
            contributionsKey: get('contributionsRes'),
            links,
        };
    }
    return result;
}

// ──────────────── 5. Experience.kt / Etc.kt: 교육·커뮤니티·활동 ────────────────
function parseEnumSimple(file, enumName, fields) {
    const src = readModel(file);
    const enumStart = src.indexOf(`enum class ${enumName}`);
    if (enumStart === -1) throw new Error(`${enumName}을 찾을 수 없습니다 (${file})`);
    const body = src.slice(src.indexOf('{', enumStart) + 1);
    // 다음 enum/sealed 정의 전까지로 제한
    const stop = body.search(/\nenum class |\nsealed /);
    const scoped = stop === -1 ? body : body.slice(0, stop);

    const result = {};
    const constRe = /(^|\n)\s*([A-Za-z][A-Za-z0-9_]+)\(/g;
    const consts = [];
    let cm;
    while ((cm = constRe.exec(scoped)) !== null) {
        // override/val 같은 키워드 제외
        if (['override', 'val', 'listOf', 'Res'].includes(cm[2])) continue;
        consts.push({ name: cm[2], idx: cm.index });
    }
    for (let i = 0; i < consts.length; i++) {
        const segStart = consts[i].idx;
        const segEnd = i + 1 < consts.length ? consts[i + 1].idx : scoped.length;
        const seg = scoped.slice(segStart, segEnd);

        // 블록 내 모든 string 참조(positional fallback용)
        const allRefs = [];
        const refRe = /(?:Res\.)?string\.([A-Za-z0-9_]+)/g;
        let rm;
        while ((rm = refRe.exec(seg)) !== null) allRefs.push(rm[1]);

        const obj = {};
        fields.forEach((f, fi) => {
            const mm = seg.match(new RegExp(`${f}\\s*=\\s*(?:Res\\.)?string\\.([A-Za-z0-9_]+)`));
            // 라벨 매칭 실패 시: 같은 위치의 positional 인자로 fallback
            obj[f] = mm ? mm[1] : allRefs[fi] || null;
        });
        // links
        const links = [];
        const linkRe = /(?:Res\.)?string\.([A-Za-z0-9_]+)\s+to\s+"([^"]+)"/g;
        let lm;
        while ((lm = linkRe.exec(seg)) !== null) links.push({ titleKey: lm[1], url: lm[2] });
        obj.links = links;
        result[consts[i].name] = obj;
    }
    return result;
}

// 평문 contributions("- a\n- b") → 배열
function splitDashList(text) {
    return text
        .split('\n')
        .map((l) => l.replace(/^\s*-\s*/, '').trim())
        .filter(Boolean);
}

// description의 \n\n 단락은 그대로 유지(렌더러가 처리), 단일 \n은 공백 정리
function normalizeDescription(text) {
    return text.trim();
}

// ───────────────────────── 6. 조립 ─────────────────────────
function build() {
    const strings = parseStrings();
    const extra = JSON.parse(stripJsonComments(fs.readFileSync(EXTRA_PATH, 'utf-8')));
    const manifest = JSON.parse(stripJsonComments(fs.readFileSync(MANIFEST_PATH, 'utf-8')));

    const careerProjects = parseCareerProjects();
    const projectMeta = parseProjectLinks();
    const educationMeta = parseEnumSimple('Experience.kt', 'Education', ['titleRes', 'subtitleRes', 'descriptionRes', 'periodRes']);
    const communityMeta = parseEnumSimple('Experience.kt', 'Community', ['titleRes', 'subtitleRes', 'descriptionRes', 'periodRes']);
    const etcMeta = parseEnumSimple('Etc.kt', 'Etc', ['titleRes', 'periodRes']);

    // 링크 표시 텍스트: strings.xml의 해당 키
    const linkLabel = (titleKey) => s(strings, titleKey);

    // personalInfo: Contact는 URL 위주라 라벨은 extra + strings 조합
    const personalInfo = {
        name: '조준장',
        github: 'junjange',
        blog: extra.personalInfo.blogLabel,
        portfolio: extra.personalInfo.portfolioLabel,
        phone: extra.personalInfo.phone,
        email: 'wnswkd486@gmail.com',
    };

    // 자기소개
    const aboutKeys = [
        ['about_me_title1', 'about_me_description1'],
        ['about_me_title2', 'about_me_description2'],
        ['about_me_title3', 'about_me_description3'],
    ];
    const introductions = aboutKeys.map(([t, d]) => ({
        title: s(strings, t),
        description: normalizeDescription(s(strings, d)),
    }));

    // 경력
    const careers = manifest.careers.map((companyEnum) => {
        const key = companyEnum.toLowerCase(); // Paytalab → paytalab (strings 키 접두)
        const nameKey = `career_${key}`;
        const company = s(strings, nameKey);
        const description = s(strings, `career_${key}_intro`);
        const period = s(strings, `career_${key}_period`);
        const projsRaw = careerProjects[companyEnum.toUpperCase()] || [];
        const projects = projsRaw.map((p) => ({
            name: p.titleKey ? s(strings, p.titleKey) : '',
            period: p.periodKey ? s(strings, p.periodKey) : null,
            techStack: p.techKey ? s(strings, p.techKey) : null,
            details: p.contributions,
        }));
        return {
            company,
            description,
            position: '안드로이드 개발자',
            period,
            projects,
        };
    });

    // 프로젝트
    const projects = manifest.projects.map((enumName) => {
        const meta = projectMeta[enumName];
        if (!meta) throw new Error(`Project.kt에 ${enumName}이 없습니다`);
        const contribRaw = meta.contributionsKey ? s(strings, meta.contributionsKey) : '';
        return {
            name: s(strings, meta.titleKey),
            subtitle: s(strings, meta.subtitleKey),
            description: normalizeDescription(s(strings, meta.descriptionKey)),
            period: meta.periodKey ? s(strings, meta.periodKey) : null,
            techStack: meta.techStackKey ? s(strings, meta.techStackKey) : null,
            links: meta.links.map((l) => ({ label: linkLabel(l.titleKey), url: l.url })),
            achievements: splitDashList(contribRaw),
        };
    });

    // 교육
    const educations = manifest.educations.map((enumName) => {
        const meta = educationMeta[enumName];
        if (!meta) throw new Error(`Education에 ${enumName}이 없습니다`);
        return {
            name: s(strings, meta.titleRes),
            subtitle: s(strings, meta.subtitleRes),
            period: s(strings, meta.periodRes),
            achievements: splitDashList(s(strings, meta.descriptionRes)),
            links: meta.links.map((l) => ({ label: linkLabel(l.titleKey), url: l.url })),
        };
    });

    // 커뮤니티
    const communities = manifest.communities.map((enumName) => {
        const meta = communityMeta[enumName];
        if (!meta) throw new Error(`Community에 ${enumName}이 없습니다`);
        return {
            name: s(strings, meta.titleRes),
            role: s(strings, meta.subtitleRes),
            activities: splitDashList(s(strings, meta.descriptionRes)),
            links: meta.links.map((l) => ({ label: linkLabel(l.titleKey), url: l.url })),
        };
    });

    // 그 외 활동
    const activities = manifest.activities.map((enumName) => {
        const meta = etcMeta[enumName];
        if (!meta) throw new Error(`Etc에 ${enumName}이 없습니다`);
        return {
            title: s(strings, meta.titleRes),
            period: s(strings, meta.periodRes),
        };
    });

    return {
        _generated: 'resume/scripts/export-from-kmp.js (KMP 모델에서 자동 생성 — 직접 수정 금지)',
        personalInfo,
        introductions,
        careers,
        projects,
        educations,
        school: extra.school,
        communities,
        activities,
        techSkills: extra.techSkills,
    };
}

// JSON에 // 또는 /* */ 주석, _comment 키 허용
function stripJsonComments(text) {
    return text.replace(/\/\*[\s\S]*?\*\//g, '').replace(/(^|\s)\/\/.*$/gm, '$1');
}

// ───────────────────────── 실행 ─────────────────────────
function main() {
    console.log('📦 KMP 모델에서 이력서 데이터 추출 중...\n');
    const resume = build();
    fs.writeFileSync(OUT_PATH, JSON.stringify(resume, null, 2) + '\n', 'utf-8');
    console.log(`   ✓ 경력 ${resume.careers.length}개, 프로젝트 ${resume.projects.length}개, 교육 ${resume.educations.length}개, 커뮤니티 ${resume.communities.length}개, 활동 ${resume.activities.length}개`);
    console.log(`   ✓ 생성 완료: ${OUT_PATH}\n`);
}

main();
