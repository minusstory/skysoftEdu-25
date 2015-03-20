<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<script>
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

	<table>
		<tr>
			<td> ${sessionScope.sessionVO.memberName}님께서 로그인 중입니다. </td>
			<td> <input type="button" id="logoutBtn" name="logoutBtn" value="로그아웃" onclick="fn_logout()"/> </td>
		</tr>

		<tr>
			<td>
				<input type="button" id="memInfoChange" name="memInfoChange" value="회원정보변경" onclick="fn_memInfoChange()" />
				<input type="button" id="pwChange" name="pwChange" value="비밀번호변경" onclick="fn_pwChange()" />
			</td>
		</tr>

	</table>

</form>