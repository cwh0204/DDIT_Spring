<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="form-section">
		<h3 id="form-title">MBTI 등록</h3>
		<form id="mbtiForm">
			<input type="text" id="mtType" name="mtType"
				placeholder="유형 (예: ISTJ)"> <input type="text" id="mtTitle"
				name="mtTitle" placeholder="제목"> <textarea type="text"
				id="mtContent" name="mtContent" placeholder="내용">
				</textarea>
			<button type="button" onclick="handleSave()">저장</button>
		</form>
	</div>

	<table>
		<thead>
			<tr>
				<th>유형</th>
				<th>제목</th>
				<th>내용</th>
				<th>관리</th>
			</tr>
		</thead>
		<tbody id="mbtiList"></tbody>
	</table>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/mbtiView.js"></script>
</html>