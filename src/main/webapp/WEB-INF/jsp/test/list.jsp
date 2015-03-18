<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table border="1"
	summary="이 표는 번호, 제목, 등록일 항목에 대한 정보를 제공합니다. 제목클릭시 상세페이지로 이동합니다."
	class="search_list">
	<caption>테스트과제 목록</caption>
	<colgroup>
		<col width="20%" />
		<col width="*" />
		<col width="35%" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">번호</th>
			<th scope="col">제목</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<tbody>
<!-- 		<tr> -->
<!-- 			<td>번호</td> -->
<!-- 			<td>제목</td> -->
<!-- 			<td>등록일</td> -->
<!-- 		</tr> -->

		<c:if test="${list ==null}">
			<tr><td colspan="3">검색된 결과가 없습니다</td></tr>
		</c:if>


		<c:if test="${list !=null }">
			<c:forEach items="${list}" var="board">
				<tr class="record">
					<td align="center">${board.seq }</td>
					<td align="left">
						<a href="detail.do?seq=${board.seq }">
							${board.title }
						</a>
					</td>
					<td align="center">
						<fmt:formatDate value="${board.regdtm }" pattern="yyyyMMdd" type="DATE"/>
<%-- 						<fmt:parseDate value="${board.regdtm }" pattern="yyyyMMdd" /> --%>
					</td>

				</tr>
			</c:forEach>
		</c:if>

	</tbody>
</table>

<!-- 페이징 처리 -->
<div class="paging">
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
</div>
<!-- 페이징 처리 -->

<div class="btn">
	<input type="button" value="등록"
		onclick="javascript:document.location.href='/test/firstInsert.do'">
</div>