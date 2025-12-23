let isEditMode = false;

// 페이지 로드 시 실행
document.addEventListener("DOMContentLoaded", loadList);

// 1. 목록 조회
async function loadList() {
	const resp = await fetch(`../mbti`);
	if (resp.ok) {
		const list = await resp.json();
		const tbody = document.getElementById("mbtiList");
		tbody.innerHTML = list.map(item => `
            <tr>
                <td>${item.mtType}</td>
                <td>${item.mtTitle}</td>
                <td>${item.mtContent}</td>
                <td>
                    <button onclick="editMode('${item.mtType}', '${item.mtTitle}', \`${item.mtContent}\`)">수정</button>
                    <button onclick="deleteMbti('${item.mtType}')">삭제</button>
                </td>
            </tr>
        `).join("");
	}
}

// 2. 저장/수정
async function handleSave() {
	const type = document.getElementById("mtType").value;
	let params = new URLSearchParams(new FormData(document.getElementById("mbtiForm")));
	const url = isEditMode ? `../mbti/${type}` : `../mbti`;
	const method = isEditMode ? "PUT" : "POST";
	if (method == "PUT") {
		params = JSON.stringify(params = {
			mtType: document.getElementById("mtType").value,
			mtTitle: document.getElementById("mtTitle").value,
			mtContent: document.getElementById("mtContent").value
		});
	}
	const resp = await fetch(url, {
		method: method,
		headers: { "Content-Type": "application/x-www-form-urlencoded" },
		body: params
	});
	if (resp.ok) {
		alert("완료되었습니다.");
		resetForm();
		loadList();
	}
}

// 3. 삭제
async function deleteMbti(type) {
	if (!confirm("삭제하시겠습니까?")) return;
	const resp = await fetch(`../mbti/${type}`, { method: "DELETE" });
	if (resp.ok) loadList();
}

// 수정 모드 전환
function editMode(type, title, content) {
	isEditMode = true;
	document.getElementById("mtType").value = type;
	document.getElementById("mtType").readOnly = true;
	document.getElementById("mtTitle").value = title;
	document.getElementById("mtContent").value = content;
	document.getElementById("form-title").innerText = "MBTI 정보 수정";
}

// 폼 초기화
function resetForm() {
	isEditMode = false;
	document.getElementById("mbtiForm").reset();
	document.getElementById("mtType").readOnly = false;
	document.getElementById("form-title").innerText = "새 유형 등록";
}