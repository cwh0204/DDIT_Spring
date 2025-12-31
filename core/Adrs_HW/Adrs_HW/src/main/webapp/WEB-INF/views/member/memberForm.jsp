<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입양식</title>
</head>
<body>
	<form method="post" enctype="application/x-www-form-urlencoded"
		class="needs-validate3 ${not empty member ? 'was-validated' : ''}" novalidate id="regist-form">
		<table>
			<tr>
				<th>회원번호</th>
				<td><input type="text" id="memId" name="memId" maxlength="15"
					required value="${member.memId }" />
					<div class="valid-feedback">사용할 수 있는 아이디</div>
					<div class="invalid-feedback">아이디 중복 확인 필요!, ${errors.memId }</div>
					<button type="button" id="id-check">아이디 중복 체크</button></td>
			</tr>
			<tr>
				<th>암호</th>
				<td><input type="text" name="memPass" required
					pattern="\w{4,8}" value="${member.memPass }" />
					<div class="invalid-feedback">비밀번호 검증 실패, ${errors.memPass }</div></td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" required maxlength="20"
					value="${member.memName }" />
					<div class="invalid-feedback">회원명 검증 실패, ${errors.memName }</div></td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="number" name="memRegno1" required
					pattern="\d{6}" value="${member.memRegno1 }" />
					<div class="invalid-feedback">주민번호 검증 실패</div></td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="number" name="memRegno2" required
					pattern="\d{7}" value="${member.memRegno2 }" />
					<div class="invalid-feedback">주민번호 검증 실패</div></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="memBir" value="${member.memBir }" /></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" value="${member.memZip }" /></td>
			</tr>
			<tr>
				<th>기본주소</th>
				<td><input type="text" name="memAdd1"
					value="${member.memAdd1 }" /></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><input type="text" name="memAdd2"
					value="${member.memAdd2 }" /></td>
			</tr>
			<tr>
				<th>집전화번호</th>
				<td><input type="text" name="memHometel"
					value="${member.memHometel }" /></td>
			</tr>
			<tr>
				<th>회사전화</th>
				<td><input type="text" name="memComtel"
					value="${member.memComtel }" /></td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="memHp" required
					pattern="010-\d{3,4}-\d{3,4}" value="${member.memHp }" />
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">휴대폰번호를 입력해주세요, ${errors.memHp }</div></td>
			</tr>
			<tr>
				<th>메일주소</th>
				<td><input type="email" name="memMail" required
					value="${member.memMail }" />
					<div class="valid-feedback">Looks good!</div>
					<div class="invalid-feedback">메일주소를 입력해주세요, ${errors.memMail }</div></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob" value="${member.memJob }" /></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike"
					value="${member.memLike }" /></td>
			</tr>
			<tr>
				<th>기념일종류</th>
				<td><input type="text" name="memMemorial"
					value="${member.memMemorial }" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="date" name="memMemorialday"
					value="${member.memMemorialday }" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">저장</button>
					<button type="reset">취소</button>
				</td>
			</tr>
			
		
		</table>
	</form>
	<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", ()=>{
		
		const registForm = document.getElementById("regist-form");
		const idCheck = document.getElementById("id-check");
		const memId = registForm.memId;
		
		memId.setCustomValidity("아이디 중복 확인");
		
		const validateMemId = async() => {
// 			1. 입력한 아이디 확보
			const targetId = memId.value;
// 			2. 비동기 요청으로 서버로 전송한 후 중복 여부 확인
			const resp = await fetch(`/member/id-check?targetId=\${targetId}`);
			const {duplicated} = await resp.json();
			
			if(duplicated){
				memId.setCustomValidity("입력한 아이디 중복");
			}else{
				memId.setCustomValidity("");
			}
		}
		
		idCheck.addEventListener("click", validateMemId);	

		memId.addEventListener("input", () => memId.setCustomValidity("입력한 아이디 중복"));
		
	});
	
	</script>
</body>
</html>