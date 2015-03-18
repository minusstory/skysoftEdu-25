<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<script>
	//데이터 보내기
	function fn_uploadExcel() {
		var excelForm = document.getElementById("excelForm");
 		excelForm.submit();
	}
</script>


<form id="excelForm" name="excelForm" method="post" enctype="multipart/form-data" action="/test/excelUpload.do">
	<table>
		<tbody>
			<tr>
				<td>엑셀 업로드</td>
				<td><input type="file" id="excelFile" name="excelFile" /></td>
			</tr>
			<tr>
				<td>	<img src="../images/common/btn/btn_save.gif" alt="저장"  onclick="fn_uploadExcel()"/> 	</td>
				<td>	<input type="button" value="닫기" id="closePopup" name="closePopup" onclick="window.close();"/> </td>
			</tr>
		</tbody>
	</table>
</form>

