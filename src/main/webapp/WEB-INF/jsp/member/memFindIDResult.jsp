<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script>
	window.onload = function(){
		var message = document.getElementById("message").value;
		if(message != "")
			alert(message);
	};

	function fn_login(){
		document.location.href = "/index.do";
	}

	function fn_findPw(){
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/member/memFindPW.do' />" ;
		frm.submit();
	}
</script>

<form id="frm" name="frm" method="POST">
	<input type="hidden" name="message" id="message" value="${message }" />

	<table>
		<tr>
			<td>가입하신 아이디는 "${memberVO.id }" 입니다.</td>
		</tr>

		<tr>
			<td><input type="button" value="로그인" onclick="fn_login()"/> <input type="button" value="비밀번호 찾기" onclick="fn_findPw()" /></td>
		</tr>
	</table>
</form>

