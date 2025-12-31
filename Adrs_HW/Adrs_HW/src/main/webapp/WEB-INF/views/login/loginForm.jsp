<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
	<c:remove var="message" scope="session"/> 
	<!-- flash attribute -->
</c:if>
</head>
<body>
<h4> 아이디와 비밀번호 기반의 인증 시스템 </h4>
<pre>
	ID(username) : 사용자를 식별하기 위한 식별자
	Credential(password) : 신원 증명을 위한 자격증명
	2FA : 2가지 이상의 credential 을 조합하는 증명 체계
</pre>
<form method="post" enctype="application/x-www-form-urlencoded">
	아이디 : <input type="text" name="username" />
	비밀번호 : <input type="password" name="password" />
	<button type="submit">로그인</button>
</form>
</body>
</html>










