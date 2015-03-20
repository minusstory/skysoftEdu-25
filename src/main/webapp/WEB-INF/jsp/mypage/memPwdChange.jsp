<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">
	window.onload = function(){
		var message = document.getElementById("message").value;
		if(message != ""){
			alert(message);
		}
	};

	function fn_changePw(){
		var frm = document.getElementById("frm");
		if(frm.changePw.value != frm.changePwConf.value){
			alert("변경하실 비밀번호가 같지 않습니다.");
			frm.changePw.focus();
			frm.changePw.value="";
			frm.changePwConf.value="";
			return ;
		}
		frm.action = "<c:url value='/mypage/changePW.do' />";
		alert(frm.action);
		frm.submit();
	}


	function fn_cancel(){
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/login.do' />";
		frm.password.disabled = true; //비밀번호 null값 전달을 위함
		frm.submit();
	}

</script>

<form id="frm" name="frm" method="POST">
	<input type="hidden" id="message" name="message" value="${message }" />
	<input type="hidden" id="id" name="id" value="${id }" />
	<table>
		<tr>
			<td>기존 비밀번호</td>
			<td><input type="text" id="password" name="password"/> </td>
		</tr>
		<tr>
			<td>변경 비밀번호</td>
			<td><input type="text" id="changePw" name="changePw"/> </td>
		</tr>
		<tr>
			<td>변경 비밀번호 확인</td>
			<td><input type="text" id="changePwConf" name="changePwConf"/> </td>
		</tr>

		<tr>
			<td> <input type="button" value="비밀번호변경" onclick="fn_changePw()"/> <input type="button" value="취소" onclick="fn_cancel()" /> </td>
		</tr>
	</table>
</form>

