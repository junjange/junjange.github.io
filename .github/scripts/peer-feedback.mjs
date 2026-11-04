import { appendFileSync, readFileSync, writeFileSync } from "node:fs";

const env = process.env;
const options = JSON.parse(readFileSync(".github/feedback/options.json", "utf8"));

const LABELS = {
  place: "함께한 곳",
  relation: "관계",
  strength: "좋았던 점",
  growth: "아쉬웠던 점 (선택)",
  consent: "확인",
};
const LIMITS = { strength: 300, growth: 200 };

function parseSections(body) {
  const sections = {};
  const parts = body.replace(/\r/g, "").split(/^### /m).slice(1);
  for (const part of parts) {
    const newline = part.indexOf("\n");
    const title = part.slice(0, newline).trim();
    const value = part.slice(newline + 1).trim();
    sections[title] = value === "_No response_" ? "" : value;
  }
  return sections;
}

function findId(list, label) {
  return list.find((item) => item.label === label)?.id;
}

const errors = [];
const sections = parseSections(env.ISSUE_BODY ?? "");
const issue = Number(env.ISSUE_NUMBER);
const authorId = Number(env.AUTHOR_ID);

if (env.AUTHOR_TYPE !== "User") errors.push("사람 계정으로 작성한 피드백만 받을 수 있어요.");
if (!Number.isSafeInteger(issue) || issue <= 0) errors.push("이슈 번호를 확인하지 못했어요.");
if (!Number.isSafeInteger(authorId) || authorId <= 0) errors.push("작성자 정보를 확인하지 못했어요.");

const place = findId(options.places, sections[LABELS.place]);
if (!place) errors.push(`**${LABELS.place}**을 목록에서 골라주세요.`);

const relation = findId(options.relations, sections[LABELS.relation]);
if (!relation) errors.push(`**${LABELS.relation}**를 목록에서 골라주세요.`);

const strength = sections[LABELS.strength] ?? "";
if (!strength) errors.push(`**${LABELS.strength}**을 적어주세요.`);
if (strength.length > LIMITS.strength) errors.push(`**${LABELS.strength}**은 ${LIMITS.strength}자 이내로 줄여주세요. (지금 ${strength.length}자)`);

const growth = sections[LABELS.growth] ?? "";
if (growth.length > LIMITS.growth) errors.push(`**${LABELS.growth.replace(" (선택)", "")}**은 ${LIMITS.growth}자 이내로 줄여주세요. (지금 ${growth.length}자)`);

const consent = sections[LABELS.consent] ?? "";
const checked = (consent.match(/^- \[[xX]\]/gm) ?? []).length;
if (checked < 2) errors.push(`**${LABELS.consent}** 항목을 모두 체크해주세요.`);

const output = (line) => appendFileSync(env.GITHUB_OUTPUT, `${line}\n`);

if (errors.length > 0) {
  const message = ["피드백을 올리기 전에 아래 내용을 고쳐주세요. 이슈를 수정하면 다시 확인해요.", "", ...errors.map((e) => `- ${e}`)].join("\n");
  writeFileSync(`${env.RUNNER_TEMP}/feedback-errors.md`, message);
  output("valid=false");
} else {
  const feedback = {
    source: "github",
    login: env.AUTHOR_LOGIN,
    id: authorId,
    issue,
    place,
    relation,
    strength,
    ...(growth ? { growth } : {}),
    createdAt: (env.ISSUE_CREATED_AT ?? "").slice(0, 10),
  };
  writeFileSync(`feedbacks/github-${issue}.json`, `${JSON.stringify(feedback, null, 2)}\n`);
  output("valid=true");
}
