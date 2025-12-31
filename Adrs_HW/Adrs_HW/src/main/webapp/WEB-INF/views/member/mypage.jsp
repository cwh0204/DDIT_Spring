<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>${member.memId}님의마이페이지</h4>
	<table class="table table-bordered">
		<tr>
			<th>회원번호</th>
			<td>${member.memId}</td>
		</tr>
		<tr>
			<th>암호</th>
			<td>${member.memPass}</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>${member.memName}</td>
		</tr>
		<tr>
			<th>주민번호</th>
			<td>${member.memRegno1}-${member.memRegno2}</td>
		</tr>
		<tr>
			<th>생년월일</th>
			<td>${member.memBir}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip}</td>
		</tr>
		<tr>
			<th>기본주소</th>
			<td>${member.memAdd1}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${member.memAdd2}</td>
		</tr>
		<tr>
			<th>집 전화번호</th>
			<td>${member.memHometel}</td>
		</tr>
		<tr>
			<th>회사 전화</th>
			<td>${member.memComtel}</td>
		</tr>
		<tr>
			<th>휴대폰 번호</th>
			<td>${member.memHp}</td>
		</tr>
		<tr>
			<th>메일 주소</th>
			<td>${member.memMail}</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob}</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memLike}</td>
		</tr>
		<tr>
			<th>기념일 종류</th>
			<td>${member.memMemorial}</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.memMemorialday}</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage}</td>
		</tr>
		<tr>
			<th>상태정보</th>
			<td>${member.memDelete}</td>
		</tr>
		<tr>
			<th>역활</th>
			<td>${member.memRole}</td>
		</tr>

		<tr>
			<td colspan="2">
				<button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-danger" data-bs-toggle="modal"
					data-bs-target="#exampleModal">탈퇴</button>
			</td>
		</tr>
	</table>


	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Modal
						title</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form method="post" action="/member/remove"
					enctype="application/x-www-form-urlencoded">
					<div class="modal-body">
						<p>탈퇴할거면 한번더 비번인증!!</p>
						비밀번호 : <input type="password" name="password" />
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>