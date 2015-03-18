<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>



<script type="text/javascript">
	function fn_filedown(seq){
		document.location.href = '/test/attachFileDown.do?seq='+seq;
	}

	//페이지이동
	function fn_pageNavi(pageNo) {
		var frm = document.getElementById("frm");
		frm.pageNum.value = pageNo;
		frm.action = "<c:url value='/test/list.do'/>";
		frm.submit();
	}

	//전체 체크박스 클릭
	function fn_Allclick() {
		var achkbtn = document.getElementById("allChkBox");
		var subbtns = document.getElementsByName("checkBk");
		if (achkbtn.checked == true) {
			for ( var i = 0; i < subbtns.length; i++) {
				subbtns[i].checked = true;
			}
		} else {
			for ( var i = 0; i < subbtns.length; i++) {
				subbtns[i].checked = false;
			}
		}
	}

	//서브 체크박스 클릭
	function fn_subClick() {
		var achkbtn = document.getElementById("allChkBox");
		var subbtns = document.getElementsByName("checkBk");
		if (achkbtn.checked == true) { //check 된 상태에서 하나라도 비활성화 일때
			for ( var i = 0; i < subbtns.length; i++) {
				if (subbtns[i].checked == false)
					achkbtn.checked = false;
			}
		} else {
			for ( var i = 0; i < subbtns.length; i++) {
				if (subbtns[i].checked == false)
					return;
			}
			achkbtn.checked = true;
		}
	}

	//삭제 체크
	function fn_deleteCheck() {
		var args = [];
		var tempString = '"';
		var btnDelete = document.getElementById("btnDelete");
		var table = document.getElementById("table");

		var subbtns = document.getElementsByName("checkBk");
		for ( var i = 0; i < subbtns.length; i++) {
			for ( var i = 0; i < subbtns.length; i++) {
				if (subbtns[i].checked == true) {
					args.push(table.rows[i + 1].cells[1].innerHTML);
				}
			}
			if (args.length == 0)
				alert("삭제 할 데이터를 체크하세요");
			else {
				for ( var j = 0; j < args.length; j++) {
					tempString += args[j];
					if (j == args.length - 1)
						break;
					tempString += ", ";
				}
				tempString += '"';
				conf_Del(tempString);
			}
			function conf_Del(tempString) {
				if (confirm(("선택하신" + tempString + "번 총 " + args.length + "건의 데이터가 삭제됩니다. \n삭제하시겠습니까?"))) { //예
					document.location.href = '/test/selectDelete.do?seqs='+tempString.split('"');
				} else { //아니오
					return;
				}
			}
		}

	}

	function excel_down() {
		var frm = document.getElementById("frm");

		var recordPageNum = frm.recordCountPerPage.value;
		var tempPageNo = frm.pageNum.value;
		frm.pageNum.value = tempPageNo;
		frm.action = "<c:url value='/test/excelDown.do'/>";
		frm.submit();
	}

	function excel_allDown() {
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/test/excelAllDown.do'/>";
		frm.submit();
	}



	function popitup() {
		window.open('/test/excelUploadPopup.do','name','height=500,width=1000');
	}

	function fn_serchTable() {
		var frm = document.getElementById("frm");
		if(document.getElementById("itemText").value == ''){
			alert("검색키워드를 입력하세요");
			return ;
		}

// 		frm.action = "<c:url value='/test/searchlist.do'/>";
		frm.action = "<c:url value='/test/list.do'/>";
		frm.pageNum.value = 1; //검색 후 무조건 1페이지
		frm.submit();
	}


</script>

<form name="frm" id="frm" method="get" action="/test/list.do">
<%-- 	<input type="hidden" id="pageNum" name="pageNum"  value="${paging.currentPageNo}" /> --%>
	<input type="hidden" id="pageNum" name="pageNum"  value="${currentPageNo}" />


<!-- 	<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="10" /> -->
<input type="hidden" id="recordCountPerPage" name="recordCountPerPage" value="10" />

	<div>
		<select id="searchItem" name="searchItem">
			<option value="all">전체</option>
			<option value="title"
				<c:if test="${tableListVO.searchItem eq 'title'}">selected="selected"</c:if>>
				제목</option>
			<option value="contents"
				<c:if test="${tableListVO.searchItem eq 'contents'}">selected="selected"</c:if>>내용</option>
		</select> <input type="text" id="itemText" name="itemText"
			value="${tableListVO.itemText}" /> <input type="button"
			id="searchBtn" name="searchBtn" onclick="fn_serchTable()" value="검색" />
	</div>


	<table border="1"
		summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다."
		class="search_list" id="table">


		<colgroup>
			<col width="5%" />
			<col width="5%" />
			<col width="*" />
			<col width="10%" />
		</colgroup>
		<thead>
			<tr>
				<th scope="col"><input type="checkbox" id="allChkBox"
					name="allChkBox" onclick="fn_Allclick()" /> 전체</th>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
				<th scope="col">대표파일</th>
				<th scope="col">등록일</th>
			</tr>
		</thead>
		<tbody>
			<!-- 		<tr> -->
			<!-- 			<td>번호</td> -->
			<!-- 			<td>제목</td> -->
			<!-- 			<td>등록일</td> -->
			<!-- 		</tr> -->

			<c:if test="${fn:length(list) eq 0}">
				<tr>
					<td colspan="3">검색된 결과가 없습니다</td>
				</tr>
			</c:if>


			<c:if test="${fn:length(list) ne 0}">
				<c:forEach items="${list}" var="board">
					<tr class="record">
						<td align="left"><input type="checkbox" name="checkBk"
							onclick="fn_subClick()" /></td>
						<td align="center">${board.seq }</td>
						<td align="left"><a href="detail.do?seq=${board.seq }">${board.title}</a></td>

						<td align="left"><c:if test="${board.realfilenm ne null}">
								<input type="button" id="attachFile" name="attachFile"
									value="대표파일" onclick="fn_filedown(${board.seq})" />
							</c:if></td>
						<td align="center"><fmt:formatDate value="${board.regdtm}"
								pattern="yyyyMMdd" type="DATE" /></td>
					</tr>
				</c:forEach>
			</c:if>

		</tbody>
	</table>

	<!-- 페이징 처리 -->
	<div class="paging">
<!-- 		<img alt="처음" src="../images/common/btn/btn_pre_end.gif"> <img -->
<!-- 			alt="이전" src="../images/common/btn/btn_pre.gif"> -->
<%-- 		<ui:pagination paginationInfo="${paging}" type="text" --%>
<%-- 			jsFunction="fn_pageNavi" /> --%>
<!-- 		<img alt="다음" src="../images/common/btn/btn_next.gif"> <img -->
<!-- 			alt="마지막" src="../images/common/btn/btn_next_end.gif"> -->
			<c:out value="${pageString }" escapeXml="false"/>
	</div>

	<!-- 페이징 처리 -->
	<%--
	<a href="list.do?pageNum=1" >[처음]</a>
	<c:forEach var="i" begin="${pu.startPageNum }" end="${pu.endPageNum }">
					<c:choose>
						<c:when test="${i==pu.pageNum }">
							<a href="list.do?pageNum=${i }" >
							<span style="color:red; fon-size: 10pt"> [${i }]</span>
							</a>
						</c:when>
						<c:otherwise>
						<a href="list.do?pageNum=${i }"> [${i }]</a>
					</c:otherwise>
					</c:choose>
			</c:forEach>
	<a href="list.do?pageNum=${pu.totalPageCount}">[마지막]</a>
	 --%>


	<div class="btn">
		<input type="button" value="등록" onclick="javascript:document.location.href='/test/firstInsert.do'" />
		<input type="button" value="삭제" id="btnDelete" name="btnDelete" onclick="fn_deleteCheck()" />
		<input type="button" value="엑셀 다운로드"  id="excelDown" name="excelDown" onclick="excel_down()" />
		<input type="button" value="전체 다운로드" id="allExcDown" name="allExcDown" 	onclick="excel_allDown()" />
		<input type="button" value="엑셀 업로드"	id="excelUp" name="excelUp" onclick="popitup()" />
	</div>
</form>
