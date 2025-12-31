<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보 수정</title>
</head>
<body>
	<form class="needs-validate3 ${not empty member ? 'was-validated' : ''}" id="modify-form" method="post" enctype="application/x-www-form-urlencoded" novalidate>
		<table>
			<tr>
				<th>회원번호</th>
				<td>
				<div>
					<input type="text" name="memId" required value="${member.memId }"/>
					<div class="invalid-feedback">아이디 입력 필수 , ${errors.memId }</div>
				</div>
				</td>
			</tr>
			<tr>
				<th>암호</th>
				<td>
				<input type="text" name="memPass" required pattern="\w{4,8}" />
				<div class="invalid-feedback">비밀번호 검증 실패 , ${errors.memPass }</div>
				</td>
			</tr>
			<tr>
				<th>회원명</th>
				<td><input type="text" name="memName" required value="${member.memName }"/>
				<div class="invalid-feedback">회원명 검증 실패 , ${errors.memName }</div>
				</td>
			</tr>
			<tr>
				<th>주민번호1</th>
				<td><input type="text" name="memRegno1" pattern="\d{6}" value="${member.memRegno1 }"/>
				<div class="invalid-feedback">주민번호 앞자리 검증 실패</div>
				</td>
			</tr>
			<tr>
				<th>주민번호2</th>
				<td><input type="text" name="memRegno2" pattern="\d{7}" value="${member.memRegno2 }"/>
					<div class="invalid-feedback">주민번호 뒷자리 검증 실패</div>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td><input type="date" name="memBir" value="${member.memBir }"/></td>
			</tr>
			<tr>
				<th>우편번호</th>
				<td><input type="text" name="memZip" value="${member.memZip }"/></td>
			</tr>
			<tr>
				<th>기본주소</th>
				<td><input type="text" name="memAdd1"value="${member.memAdd1 }" /></td>
			</tr>
			<tr>
				<th>상세주소</th>
				<td><input type="text" name="memAdd2" value="${member.memAdd2 }"/></td>
			</tr>
			<tr>
				<th>집전화번호</th>
				<td><input type="text" name="memHometel" value="${member.memHometel }"/></td>
			</tr>
			<tr>
				<th>회사전화</th>
				<td><input type="text" name="memComtel" value="${member.memComtel }"/></td>
			</tr>
			<tr>
				<th>휴대폰 번호</th>
				<td><input type="text" name="memHp" required pattern="010-\d{3,4}-\d{4}" value="${member.memHp }"/>
					<div class="invalid-feedback">휴대폰 번호 형식 확인 , ${errors.memHp }</div>
				</td>
			</tr>
			<tr>
				<th>메일주소</th>
				<td><input type="email" name="memMail" required  value="${member.memMail }"/>
					<div class="invalid-feedback">메일 주소 형식 확인, ${errors.memMail }</div>
				</td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob" value="${member.memJob }"/></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike" value="${member.memLike }"/></td>
			</tr>
			<tr>
				<th>기념일종류</th>
				<td><input type="text" name="memMemorial"value="${member.memMemorial }" /></td>
			</tr>
			<tr>
				<th>기념일</th>
				<td><input type="date" name="memMemorialday"value="${member.memMemorialday }" /></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="text" name="memMileage"value="${member.memMileage }" min="0" />
				<div class="invalid-feedback">마일리지는 0이상의 정수, ${errors.memMileage }</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="submit">저장</button>
					<button type="reset">취소</button>
				</td>
			</tr>
			
			
		</table>
	</form>
</body>
</html>









