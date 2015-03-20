<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script type="text/javascript">
	window.onload = function() {

		var message = document.getElementById("message").value;
		if (message != "") {
			alert(message);
		}
	};

	function login() {
		var frm = document.getElementById("frm");

		if (frm.id.value == "") {
			alert("아이디를 입력하세요");
			frm.id.focus();
			return;
		}

		if (frm.password.value == "") {
			alert("비밀번호를 입력하세요");
			frm.password.focus();
			return;
		}

		frm.action = "<c:url value='/login.do' />";
		frm.submit();
	}

	function registMembership() {
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/member/beforeMemChk.do' />";
		frm.submit();

	}

	function searchIdPw() {
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/member/memFindID.do' />";
		frm.submit();
	}

	function fn_logout(){
		document.location.href="/logout.do";
	}

	function fn_memInfoChange(){
		document.location.href="/mypage/memInfo.do";
	}

	function fn_pwChange(){
		document.location.href="/mypage/memPwdChange.do";
	}

</script>


<form id="frm" name="frm" method="POST">
	<input type="hidden" id="message" name="message" value="${message }" />
	<c:if test="${sessionScope.sessionVO eq null }">
		<table>
			<tr>
				<td>
					<table>
						<tbody>
							<tr>
								<td>ID</td>
								<td><input type="text" id="id" name="id" /></td>
							</tr>
							<tr>
								<td>PW</td>
								<td><input type="password" id="password" name="password" /></td>
							</tr>
						</tbody>
					</table>
				</td>
				<td><input type="button" id="loginBtn" name="loginBtn" value="로그인" onclick="login()" /></td>

			</tr>

			<tr align="center">
				<td>
					<table>
						<tr>
							<td><input type="button" id="registBtn" name="registBtn" value="회원가입" onclick="registMembership()" /></td>
							<td><input type="button" id="searchIdPwBtn" name="searchIdPwBtn" value="ID/PW 찾기" onclick="searchIdPw()" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</c:if>

	<c:if test="${sessionScope.sessionVO ne null }">
		<table>
			<tr>
				<td>${sessionScope.sessionVO.memberName}님께서 로그인 중입니다.</td>
				<td><input type="button" id="logoutBtn" name="logoutBtn" value="로그아웃" onclick="fn_logout()" /></td>
			</tr>

			<tr>
				<td><input type="button" id="memInfoChange" name="memInfoChange" value="회원정보변경" onclick="fn_memInfoChange()" /> <input type="button" id="pwChange" name="pwChange" value="비밀번호변경" onclick="fn_pwChange()" /></td>
			</tr>
		</table>
	</c:if>

</form>