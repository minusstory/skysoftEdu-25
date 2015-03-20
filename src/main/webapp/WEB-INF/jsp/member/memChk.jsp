           <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>

<script>

	window:onload= function(){
		var useyn = document.getElementById("useyn");
		if(useyn.value == 'Y'){
			alert("이미 회원으로 가입되어 있습니다");
			document.location.href="/index.do";
		}
		else if(useyn.value == 'N'){
			alert("회원이 아닙니다");
			var frm = document.getElementById("frm");
			frm.action = "<c:url value='/member/beforeMemJoin.do'/> ";
			frm.submit();
		}

	};

	function check_memberShip(){
		var frm = document.getElementById("frm");

		if (!fn_CheckJuminNumber(frm.rrnumber1.value + frm.rrnumber2.value)) {
			frm.rrnumber1.value="";
			frm.rrnumber2.value="";
			frm.rrnumber1.focus();
			return; // 주민등록번호 유효성이 맞지 않을 경우 리턴
		}

		if(frm.name.value == ""){
			alert("이름을 입력하세요");
			frm.name.focus();
		}
		else if(frm.rrnumber1.value== "" || frm.rrnumber2.value==""){
			alert("주민등록번호를 입력하세요");
			frm.rrnum.focus();
		}
		else{
			frm.action = "<c:url value='/member/memChk.do' />";
			frm.submit();
		}
	}

	function cancel(){
		document.location.href="/index.do";
	}

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

</script>

<form id="frm" name="frm" method="POST">
	<input type="hidden" id="useyn" name="useyn" value="${memberVO.use_yn}" />
	<table>
		<tr>
			<td>이름 </td>
			<td><input type="text" id="name" name="name" value="${memberVO.name }" /> </td>
		</tr>

		<tr>
			<td>주민등록번호 </td>
			<td><input type="text" id="rrnumber1" name="rrnumber1" maxlength="6" value="${memberVO.rrnumber1 }"/> </td>
			<td> - </td>
			<td><input type="password" id="rrnumber2" name="rrnumber2" maxlength="7" value="${memberVO.rrnumber2 }"/> </td>
		</tr>

		<tr>
			<td> <input type="button" value="가입여부확인" id="checkBtn" name="checkBtn" onclick="check_memberShip()"/> </td>
			<td> <input type="button" value="취소" id="cancelBtn" name="cancelBtn" onclick="cancel()" /> </td>
		</tr>
	</table>
</form>

