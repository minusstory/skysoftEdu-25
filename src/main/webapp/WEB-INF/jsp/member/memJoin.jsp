<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<script type="text/javascript">
	window.onload = function() {
		var idCheckResult = document.getElementById("idCheckResult");
		var idText = document.getElementById("id");
		if (idCheckResult.value == "Y") {
			alert("동일한 아이디가 이미 존재합니다");
			idText.value = "";
			idText.focus();
		} else if (idCheckResult.value == "N") {
			alert("아이디를 사용할 수 있습니다.");
		}
	};

	/**
	 * 주민등록번호 유효성 검사
	 * 2014-07-18 :주민번호 뒷자리 1,2,3,4 대응 가능
	 * @param rrnumber : 검사대상 주민등록번호
	 **/

	function fn_CheckJuminNumber(rrnumber) {
		var flag = true; //결과 return 변수
		var checkType; //내,외국인 구분
		var regExp = /^[0-9]{13}$/; //숫자만 13개 입력되었는지 체크하기 위한 정규식
		if (!regExp.test(rrnumber)) { //길이체크
			alert("주민등록번호는 13자리 숫자만 입력가능합니다.");
			flag = false;
		} else {
			checkType = rrnumber.substring(6, 7); //내, 외국인 구분값 세팅
			if (checkType == '5' || checkType == '6' || checkType == '7'
					|| checkType == '8') { //외국인 인경우
				var sum = 0;
				var odd = 0;
				buf = new Array(13);
				for ( var i = 0; i < 13; i++)
					buf[i] = parseInt(rrnumber.charAt(i));
				odd = buf[7] * 10 + buf[8];
				if (odd % 2 != 0) {
					flag = false;
				}
				if ((buf[11] != 6) && (buf[11] != 7) && (buf[11] != 8)
						&& (buf[11] != 9)) {
					flag = false;
				}
				multipliers = [ 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 ];
				for (i = 0, sum = 0; i < 12; i++)
					sum += (buf[i] *= multipliers[i]);
				sum = 11 - (sum % 11);

				if (sum >= 10)
					sum -= 10;
				sum += 2;
				if (sum >= 10)
					sum -= 10;
				if (sum != buf[12]) {
					flag = false;
				}
			} else { //내국인
				var sum = 0;
				var array = [ 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 ]; // 주민등록번호 유효성 검사 값
				var perNumber;
				var checkNum;

				for ( var i = 0; i < 12; i++) { //1~12 번째자리까지만 체크, 마지막 자리는 버림
					sum += rrnumber.charAt(i) * array[i];
				}

				perNumber = sum % 11;
				checkNum = 11 - perNumber;

				if (checkNum > 9) {
					checkNum = checkNum - 10;
				}
				if (checkNum != rrnumber.charAt(12)) {
					flag = false;
				}
			}
			if (!flag)
				alert("주민등록번호를 다시 확인해주세요.");
		}
		return flag;
	};

	//아이디 중복 체크
	function fn_idOverlapCheck() {
		var frm = document.getElementById("frm");
		if(frm.id.value==""){
			alert("아이디를 입력하세요");
			return ;
		}
		//eamil , hp , tel 합쳐서 hidden에 저장해주기
		frm.action = "/member/idCheck.do";
		frm.name.disabled=false;
		frm.rrnumber1.disabled=false;
		frm.rrnumber2.disabled=false;
		frm.submit();
	};

	//입렵된 비빌먼호 확인
	function fn_passwordcheck(password, repassword) {
		if (password == repassword)
			return true;
		else {
			alert("비밀번호가 일치 하지 않습니다. 다시 확인해주세요")
			return false;
		}
	};

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

	//둘중 하나는 빈칸 안되게 처리하기
	function fn_memberJoin() {

		var frm = document.getElementById("frm");

		if (frm.name.value == "") {
			alert("이름을 입력하세요");
			frm.name.focus();
			return; //이름을 입력하지 않은 경우 리턴
		}

		if (!fn_CheckJuminNumber(frm.rrnumber1.value + frm.rrnumber2.value)) {
			frm.rrnumber1.value="";
			frm.rrnumber2.value="";
			frm.rrnumber1.focus();
			return; // 주민등록번호 유효성이 맞지 않을 경우 리턴
		}

		if (frm.id.value == "") {
			alert("아이디를 입력하세요");
			frm.id.focus();
			return; //아이디 입력 하지 않았을 경우
		}

		if (frm.password.value == "" || frm.repassword.value == "") {
			alert("비밀번호를 입력하세요");
			frm.password.focus();
		}

		if (!fn_passwordcheck(frm.password.value, frm.repassword.value)) {
			return; //비밀번호 , 비밀번호 확인이 불이치시 리턴
		}

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
		frm.emailSec.disabled = false;
		frm.name.disabled=false;
		frm.rrnumber1.disabled=false;
		frm.rrnumber2.disabled=false;
		frm.action = "<c:url value='/member/memJoin.do' />";
		frm.submit();
	};

	function fn_cancel(){
		document.location.href="../index.do";
	}

	function fn_registImage(){
		var registProfileYN = document.getElementById("registProfileYN");
		registProfileYN.value="Y";
		alert(registProfileYN.value);
	}
</script>


<form id="frm" name="frm" method="POST"  enctype="multipart/form-data" >
	<input type="hidden" name="idCheckResult" id="idCheckResult" value="${idCheckResult}" />
	<input type="hidden" name="registProfileYN" id="registProfileYN" value="N" />
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" id="name" name="name" value="${memberVO.name }" disabled="disabled"/></td>
		</tr>

		<tr>
			<td>주민등록번호</td>
			<td><input type="text" id="rrnumber1" name="rrnumber1" value="${memberVO.rrnumber1 }" maxlength="6" disabled="disabled"/>-<input type="password" id="rrnumber2" name="rrnumber2" value="${memberVO.rrnumber2 }" maxlength="7"disabled="disabled" /></td>
		</tr>

		<tr>
			<td>아이디</td>
			<td><input type="text" id="id" name="id" value="${memberVO.id }" />
				<input type="button" id="idcheckBtn" name="idcheckBtn" value="중복확인" onclick="fn_idOverlapCheck()" /></td>
		</tr>

		<tr>
			<td>비밀번호</td>
			<td><input type="password" id="password" name="password" value="${memberVO.password }" /></td>
		</tr>

		<tr>
			<td>비밀번호확인</td>
			<td><input type="password" id="repassword" name="repassword" value="${memberVO.repassword}" /></td>
		</tr>

		<tr>
			<td>연락처</td>
			<td><select id="telFir" name="telFir">
					<option value='02' selected="selected">02</option>
					<option value='031'>031</option>
					<option value='032'>032</option>
					<option value='051'>051</option>
					<option value='053'>053</option>
					<option value='054'>054</option>
					<option value='055'>055</option>
					<option value='033'>033</option>
					<option value='042'>042</option>
					<option value='041'>041</option>
					<option value='043'>043</option>
					<option value='062'>062</option>
					<option value='061'>061</option>
					<option value='063'>063</option>
					<option value='064'>064</option>
				</select>
				-<input type="text" id="telSec" name="telSec" value="${memberVO.telSec }" />
				-<input type="text" id="telThr" name="telThr" value="${memberVO.telThr }" />
			</td>
		</tr>


		<tr>
			<td>휴대폰번호</td>
			<td><select id="hpFir" name="hpFir">
					<option value='010' selected="selected">010</option>
					<option value='011'>011</option>
					<option value='016'>016</option>
					<option value='017'>017</option>
					<option value='018'>018</option>
					<option value='019'>019</option>
				</select>
				-<input type="text" id="hpSec" name="hpSec" value="${memberVO.hpSec }" />
				-<input type="text" id="hpThr" name="hpThr" value="${memberVO.hpThr }" />
			</td>
		</tr>

		<tr>
			<td>이메일주소</td>
			<td>
				<input type="text" id="emailFir" name="emailFir" value="${memberVO.emailFir}"/>
				@<input type="text" id="emailSec" name="emailSec" disabled="disabled" value="${memberVO.emailSec }"/>
				<select id="emailThr" name="emailThr" onchange="fn_emailFill()">
					<option value='선택' selected="selected">선택</option>
					<option value='naver.com'>네이버</option>
					<option value='gmail.com'>GMAIL</option>
					<option value='daum.net'>다음</option>
					<option value='nate.com'>네이트</option>
					<option value='hotmail.com'>핫메일</option>
					<option value='직접입력'>직접입력</option>

				</select></td>
		</tr>

		<tr>
			<td>우편번호</td>
			<td><input type="text" id="zipcode" name="zipcode" /></td>
		</tr>


		<tr>
			<td>주소1</td>
			<td><input type="text" id="address" name="address" /></td>
		</tr>

		<tr>
			<td>상세주소</td>
			<td><input type="text" id="d_address" name="d_address" /></td>
		</tr>

		<tr>
			<td>프로필 사진</td>
			<td><input type="file"  accept="image/*" id="profileImg" name="profileImg" onchange="fn_registImage()"/></td>
		</tr>

		<tr>
			<td><input type="button" id="joinBtn" name="joinBtn" value="가입완료" onclick="fn_memberJoin()" /></td>
			<td><input type="button" id="cancelBtn" name="cancelBtn" value="취소" onclick="fn_cancel()"/></td>
		</tr>
	</table>
</form>