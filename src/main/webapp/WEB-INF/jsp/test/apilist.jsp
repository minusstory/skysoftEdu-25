<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>


<script type="text/javascript">
	function fn_searchClick() {
		var frm = document.getElementById("frm");
		frm.action = "<c:url value='/test/registNews.do' />";
		frm.submit();
	}

	//페이지이동
	function fn_pageNavi(pageNo) {
		var frm = document.getElementById("frm");
		frm.pageNum.value = pageNo;
		frm.action = "<c:url value='/test/apilist.do'/>";
		frm.submit();
	}
</script>

<form id="frm" name="frm" method="GET">
	<input type="hidden" value="${cureentPageNum}" id="pageNum" name="pageNum" />
	<select>
		<option>news</option>
	</select>
	<input type="text" id="searchText" name="searchText" />
	<input type="button" id="searchBtn" name="searchBtn" onclick="fn_searchClick()" value="네이버 뉴스 검색" />

	<table border="1">
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">제목</th>
			</tr>
		</thead>

		<tbody>
			<c:if test="${fn:length(list) ne 0 }">
				<c:forEach items="${list }" var="api">
					<tr>
						<td>${api.seq}</td>
						<td>
							<a href="apidetail.do?seq=${api.seq}">${api.title}</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<!-- 페이징 처리 -->
	<c:if test="${fn:length(list) ne 0 }">
		<div class="paging">
			<ui:pagination paginationInfo="${paging}" type="text" jsFunction="fn_pageNavi" />
		</div>
	</c:if>

</form>