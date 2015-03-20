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


	function fn_findId(){
		var frm = document.getElementById("frm");
		if(frm.name.value == ""){
			alert("이름을 올바르게 입력하세요");
			frm.name.focus();
			return; //이름에 빈칸입력시
		}
		else if(frm.rrnumber1.value=="" || frm.rrnumber2.value==""){
			alert("주민등록번호를 올바르게 입력하세요");
			frm.rrnumber1.focus();
			return; //주민등록번호 빈칸 입력시
		}
		else{
			frm.action = "<c:url value='/member/memFindIDResult.do' />";
			frm.submit();

		}
	}

	function fn_cancel(){
		document.location.href="/index.do";
	}
</script>

<form id="frm" name="frm" method="POST">
	<input type="hidden" name="message" id="message" value="${message }" />

	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" /></td>
		</tr>

		<tr>
			<td>주민등록번호</td>
			<td><input type="text" id="rrnumber1" name="rrnumber1"maxlength="6" />-<input type="password" id="rrnumber2" name="rrnumber2" maxlength="7"/></td>
		</tr>

		<tr>
			<td><input type="button" value="아이디 찾기" onclick="fn_findId()"/> <input type="button" value="취소" onclick="fn_cancel()" /></td>
		</tr>
	</table>
</form>

