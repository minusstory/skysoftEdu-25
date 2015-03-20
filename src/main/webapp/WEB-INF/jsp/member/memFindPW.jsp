<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>



<script>
	window.onload = function(){
		var message = document.getElementById("message").value;

		if(message != ""){
			alert(message);
		}

		if(message == "메일 발송 완료"){
			document.location.href = '/index.do';
		}
	};

	function fn_findPw(){
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/member/memFindPWResult.do' /> ";
		frm.submit();
	}

	function fn_cancel(){
		document.location.href="/index.do";
	}
</script>

<form id="frm" name="frm" method="POST">
	<input type="hidden" name="message" id="message" value="${message }" />

	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" id="id" name="id" /></td>
		</tr>

		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" /></td>
		</tr>

		<tr>
			<td>주민등록번호</td>
			<td><input type="text" id="rrnumber1" name="rrnumber1" maxlength="6"/>-<input type="password" id="rrnumber2" name="rrnumber2" maxlength="7"/></td>
		</tr>

		<tr>
			<td>가입시 메일주소</td>
			<td><input type="text" id="email" name="email" /></td>
		</tr>

		<tr>
			<td><input type="button" value="비밀번호찾기" onclick="fn_findPw()" /> <input type="button" value="취소" onclick="fn_cancel()" /></td>
		</tr>
	</table>
</form>

