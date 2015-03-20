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

	function fn_updateInfo(){
		var frm = document.getElementById("frm");

		if (!fn_emptyCheck(frm.telFir, frm.telSec, frm.telThr)
				&& !fn_emptyCheck(frm.hpFir, frm.hpSec, frm.hpThr)) {
			alert("휴대폰 번호 혹은 연락처 중 최소 하나를 작성하세요");
			return; // 연락처, 휴대폰 중 모두 안쓸경우 리턴
		}

		// 두 항목중 하나라도 작성할 경우 작성하지 않은 항목 값 비우기
		if (!fn_emptyCheck(frm.telFir, frm.telSec, frm.telThr)){
			frm.telFir.value = "";
			frm.telSec.value= "";
			frm.telThr.value="";
		}
		if (!fn_emptyCheck(frm.hpFir, frm.hpSec, frm.hpThr)){
			frm.hpFir.value = "";
			frm.hpSec.value= "";
			frm.hpThr.value="";
		}

		if (!fn_emptyCheck(frm.emailFir, frm.emailSec, frm.emailThr)) {
			alert("이메일을 올바르게 입력하세요");
			return;
		}

		frm.action = "<c:url value='/mypage/updateInfo.do'  />";
		frm.id.disabled = false;
		frm.name.disabled = false;
		frm.rrnumber1.disabled = false;
		frm.rrnumber2.disabled = false;
		frm.submit();
	}

	function fn_changePw(){
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/mypage/memPwdChange.do' />";
		frm.submit();
	}


	//입력할 3칸 중 빈칸찾는 함수
	function fn_emptyCheck(fir, sec, thr) {
		if (fir.value == "" || sec.value == "" || thr.value == "")
			return false;
		else
			return true;
	}

	//이메일 선택 별 채우기 함수
	function fn_emailFill() {
		var frm = document.getElementById("frm");
		if (frm.emailThr.value == "직접입력") {
			frm.emailSec.value = "";
			frm.emailSec.disabled = false;
		} else if (frm.emailThr.value == "선택") {
		} else {
			frm.emailSec.value = frm.emailThr.value;
		}
	};

	function fn_updateImage(){
		var updateYN = document.getElementById("updateYN");
		updateYN.value = "Y";
	}


	function fn_cancel(){
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/login.do' />";
		frm.id.disabled = false;
		frm.name.disabled = false;
		frm.rrnumber1.disabled = false;
		frm.rrnumber2.disabled = false; //disabled를 풀어서 폼으로 전달한다.
		frm.submit();
	}
</script>


<form id="frm" name="frm" enctype="multipart/form-data" method="POST">
	<input type="hidden" id="message" name="message" value="${message }" />
	<input type="hidden" id="tel" name="tel" value="${memberVO.tel}" />
	<input type="hidden" id="hp" name="hp" value="${memberVO.hp}" />
	<input type="hidden" id="email" name="email" value="${memberVO.email}" />
	<input type="hidden" name="profile" name="profile" value="${memberVO.profile }" />
	<input type="hidden" name="updateYN" id="updateYN" value="N" />
	<input type="hidden" name="password" id="password" value="${memberVO.password }" />
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" value="${memberVO.name }" disabled="disabled" /></td>
		</tr>

		<tr>
			<td>주민등록번호</td>
			<td><input type="text" id="rrnumber1" name="rrnumber1" value="${memberVO.rrnumber1 }" disabled="disabled" maxlength="6"/>-<input type="text" id="rrnumber2" name="rrnumber2" value="${memberVO.rrnumber2 }" disabled="disabled"  maxlength="7"/></td>
		</tr>

		<tr>
			<td>아이디</td>
			<td><input type="text" id="id" name="id" value="${memberVO.id }" disabled="disabled" />
		</tr>

		<tr>
			<td>연락처</td>
			<td><select id="telFir" name="telFir">
					<option value='02'  <c:if test="${memberVO.telFir eq '02' }">selected="selected"</c:if>>02</option>
					<option value='031' <c:if test="${memberVO.telFir eq '031' }">selected="selected"</c:if>'>031</option>
					<option value='032' <c:if test="${memberVO.telFir eq '032' }">selected="selected"</c:if>>032</option>
					<option value='051' <c:if test="${memberVO.telFir eq '051' }">selected="selected"</c:if>>051</option>
					<option value='053' <c:if test="${memberVO.telFir eq '053' }">selected="selected"</c:if>>053</option>
					<option value='054' <c:if test="${memberVO.telFir eq '054' }">selected="selected"</c:if>>054</option>
					<option value='055' <c:if test="${memberVO.telFir eq '055' }">selected="selected"</c:if>>055</option>
					<option value='033' <c:if test="${memberVO.telFir eq '033' }">selected="selected"</c:if>>033</option>
					<option value='042' <c:if test="${memberVO.telFir eq '042' }">selected="selected"</c:if>>042</option>
					<option value='041' <c:if test="${memberVO.telFir eq '041' }">selected="selected"</c:if>>041</option>
					<option value='043' <c:if test="${memberVO.telFir eq '043' }">selected="selected"</c:if>>043</option>
					<option value='062' <c:if test="${memberVO.telFir eq '062' }">selected="selected"</c:if>>062</option>
					<option value='061' <c:if test="${memberVO.telFir eq '061' }">selected="selected"</c:if>>061</option>
					<option value='063' <c:if test="${memberVO.telFir eq '063' }">selected="selected"</c:if>>063</option>
					<option value='064' <c:if test="${memberVO.telFir eq '064' }">selected="selected"</c:if>>064</option>
				</select>-<input type="text" id="telSec" name="telSec" value="${memberVO.telSec }" />-<input type="text" id="telThr" name="telThr" value="${memberVO.telThr }" /></td>
		</tr>

		<tr>
			<td>휴대폰번호</td>
			<td><select id="hpFir" name="hpFir">
					<option value='010' <c:if test="${memberVO.hpFir eq '010' }">selected="selected"</c:if>>010</option>
					<option value='011' <c:if test="${memberVO.hpFir eq '011' }">selected="selected"</c:if>>011</option>
					<option value='016' <c:if test="${memberVO.hpFir eq '016' }">selected="selected"</c:if>>016</option>
					<option value='017' <c:if test="${memberVO.hpFir eq '017' }">selected="selected"</c:if>>017</option>
					<option value='018' <c:if test="${memberVO.hpFir eq '018' }">selected="selected"</c:if>>018</option>
					<option value='019' <c:if test="${memberVO.hpFir eq '019' }">selected="selected"</c:if>>019</option>
				</select>-<input type="text" id="hpSec" name="hpSec" value="${memberVO.hpSec }" />-<input type="text" id="hpThr" name="hpThr" value="${memberVO.hpThr }" /></td>
		</tr>

		<tr>
			<td>이메일주소</td>
			<td><input type="text" id="emailFir" name="emailFir" value="${memberVO.emailFir}" />@<input type="text" id="emailSec" name="emailSec" value="${memberVO.emailSec }" /> <select id="emailThr" name="emailThr" onchange="fn_emailFill()">
					<option value='직접입력' selected="selected">직접입력</option>
					<option value='naver.com' <c:if test="${memberVO.emailSec eq 'naver.com' }">selected = "selected"</c:if>>네이버</option>
					<option value='gmail.com' <c:if test="${memberVO.emailSec eq 'gmail.com' }">selected = "selected"</c:if>>GMAIL</option>
					<option value='daum.net' <c:if test="${memberVO.emailSec eq 'daum.net' }">selected = "selected"</c:if>>다음</option>
					<option value='nate.com' <c:if test="${memberVO.emailSec eq 'nate.com' }">selected = "selected"</c:if>>네이트</option>
					<option value='hotmail.com' <c:if test="${memberVO.emailSec eq 'hotmail.com' }">selected = "selected"</c:if>>핫메일</option>
				</select></td>
		</tr>

		<tr>
			<td>우편번호</td>
			<td><input type="text" id="zipcode" name="zipcode" value="${memberVO.zipcode }" /></td>
		</tr>

		<tr>
			<td>주소1</td>
			<td><input type="text" id="address" name="address" value="${memberVO.address }" /></td>
		</tr>

		<tr>
			<td>상세주소</td>
			<td><input type="text" id="d_address" name="d_address" value="${memberVO.d_address }" /></td>
		</tr>

		<tr>
			<td>프로필 사진</td>
			<td><input type="file" accept="image/*" id="profileImg" name="profileImg" value="${memberVO.profile }" onchange="fn_updateImage()" /></td>
		</tr>

		<tr>
			<td>

				<c:if test="${memberVO.profile eq null}" >
					<img src="../images/profile/defaultImg.png" width="100" height="100" />
				</c:if>
				<c:if test="${memberVO.profile ne null}" >
					<img src="../images/profile/${memberVO.id}${memberVO.profile}" width="100" height="100" />
				</c:if>

			</td>
		</tr>

		<tr>
			<td><input type="button" id="joinBtn" name="joinBtn" value="가입완료" onclick="fn_updateInfo()" /> <input type="button" id="cancelBtn" name="cancelBtn" value="취소" onclick="fn_cancel()" /> <input type="button" id="changePwBtn" name="changePwBtn" value="비밀번호변경" onclick="fn_changePw()" /></td>
		</tr>
	</table>
</form>