<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>


<form class="needs-validate" method="post" enctype="application/x-www-form-urlencoded"
	novalidate id="change-form">
	<ul>
		<li>현재 비밀번호 : <input type="password" name="currentPassword"
			required />
			<div class="valid-feedback">Looks good!</div>
			<div class="invalid-feedback">현재 비밀번호 검증 실패</div>
		</li>
		<li>새 비밀번호 : <input type="password" name="newPassword" required />
		</li>
		<li>비밀번호 재입력 : <input type="password" name="retypePassword"
			required />
			<div class="valid-feedback">Looks good!</div>
			<div class="invalid-feedback">비밀번호 재입력 실패</div>
		</li>
		<li>
			<button type="submit">비밀번호 변경</button>
		</li>
	</ul>
</form>
<script>

// 폼 데이터를 대상으로 한 유효성 검증에 필요한 요소.
// 1. 검증 룰 : HTML5 속성 (required, min, max, maxLength, pattern), custom function..
// 2. 폼 데이터의 invalid 상태 표현 : setCustomValidity(검증 오류 메시지)
// 3. 유효성 검증 시작(트리거) : checkValidity(), reportValidity()



//Example starter JavaScript for disabling form submissions if there are invalid fields

document.addEventListener("DOMContentLoaded", ()=>{
	
	 const changeForm = document.getElementById("change-form");
	 const newPassword = changeForm.newPassword;
	 const retypePassword = changeForm.retypePassword;
	 
	 const validatePassword = () => {
		 const pw1 = newPassword.value;
		 const pw2 = retypePassword.value;
		 if(pw1 && pw1 === pw2){
			 retypePassword.setCustomValidity("");
		 }else{
			 retypePassword.setCustomValidity("비밀번호 재입력 오류");
		 }
	 }
	 
	 [newPassword, retypePassword].forEach(ff => ff.addEventListener("input", validatePassword))
	 
});
</script>















