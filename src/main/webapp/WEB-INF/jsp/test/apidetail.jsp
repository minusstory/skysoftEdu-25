<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<input type="hidden" id="seq" name="seq" value="${apiVO.seq}"/>
<table border="1">
	<tr>
		<td width="150">제목</td>
		<td width="300">
			<textarea wrap="hard" rows="10" cols="40"> ${apiVO.title}</textarea>
		</td>
	</tr>

	<tr>
		<td width="150">요약</td>
		<td width="300">
			<textarea wrap="hard" rows="10" cols="40" id="description" name="description">${apiVO.description}</textarea>
		</td>
	</tr>

	<tr>
		<td width="150">링크</td>
		<td width="300">
			<textarea wrap="hard" rows="10" cols="40" id="link" name="link">${apiVO.link}</textarea>
		</td>
	</tr>


</table>
<div class="btn">
		<input type="button" value="목록" onclick="javascript:document.location.href='/test/apilist.do'">
</div>
