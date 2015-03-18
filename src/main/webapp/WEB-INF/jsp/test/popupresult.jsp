<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<body onload="">
	<input type="hidden" value="${result }" id="resultValue" name="resultValue" onchange="fn_resultCheck()"/>
</body>


<script>
	window:onload=function(){
		var result = document.getElementById("resultValue");
		if(result.value == "true"){
			window.opener.location.reload();
			window.close();
		}
		else{
			alert("에러가 발생하였습니다 다시 선택하여 주세요");
			document.location.href='/test/excelUploadPopup.do';
		}
	};
</script>