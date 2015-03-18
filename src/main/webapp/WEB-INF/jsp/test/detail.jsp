<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<form action="update.do" method="POST" id="tableListVO"
	name="tableListVO" enctype="multipart/form-data">
	<input type="hidden" id="seq" name="seq" value="${tableListVO.seq}" />
	<input type="hidden" id="delFileYn" name="delFileYn" value="N" /> <!-- 멘토 -->
	<input type="hidden" id="msg" name="msg" value="${msg}"/>
	<table border="1">
		<tr>
			<td width="150">제목</td>
			<td width="300"><input type="text" id="title" name="title"
				value="${tableListVO.title}" maxlength="30"></td>
		</tr>

		<tr>
			<td width="150">내용</td>
			<td width="300"><textarea wrap="hard" rows="10" cols="40"
					id="contents" name="contents">${tableListVO.contents }</textarea></td>
		</tr>

		<tr>
			<td width="150">첨부파일</td>
			<td width="300">
				<c:if test="${tableListVO.realfilenm ne null}">
					<div id="fileView">
						<a	href="attachFileDown.do?seq=${tableListVO.seq}">
							${tableListVO.realfilenm}
						</a>
						<input type="button" value="선택한파일삭제" id="attacjFileDelete" name="attachFileDelete" onclick="fn_attachFileDelete()" />
					</div>
					<input type="file" id="attachFile" name="attachFile" style="display:none;" />
				</c:if>
				<c:if test="${tableListVO.realfilenm eq null }">
					<input type="file" id="attachFile" name="attachFile" />
				</c:if>
			</td>
		</tr>

		<tr>
			<td width="150">등록일</td>
			<td width="300"><fmt:formatDate value="${tableListVO.regdtm}"
					pattern="yyyyMMdd" type="DATE" /></td>
		</tr>
	</table>

	<table>
		<tr>
			<td>
				<div class="btn">
					<input type="button" id="updateBtn" name="updateBtn" value="수정"
						onclick="formcheck()" />
				</div>
			</td>
			<td>
				<div class="btn">
					<input type="button" value="삭제" onclick="javascript:example()">
				</div>
			</td>
			<td>
				<div class="btn">
					<input type="button" value="목록"
						onclick="javascript:document.location.href='/test/list.do'">
				</div>
			</td>
		</tr>
	</table>
</form>


<script>
	//오류메시지 전달 시 처리
	window:onload = function(){
		var msg = "<c:out value='' />";
		if(msg!=""){
			alert(msg);
		}
	};

	function fn_attachFileDelete(){
		document.tableListVO.attachFile.style.display='';
		document.getElementById("fileView").style.display='none';
		document.getElementById("delFileYn").value='Y';
	}

	function example() {
		if (confirm("삭제하시겠습니까")) { //예
			document.location.href = '/test/delete.do?seq=${tableListVO.seq}';
		} else { //아니오
			return;
		}
	}

	function formcheck() {
		if (document.getElementById('title').value == '') {
			alert("제목을 입력하지 않으셨습니다.");
			document.getElementById('title').focus();
			return;
		} else if (trim(document.getElementById('contents').value) == '') {
			alert("내용을 입력하지 않으셨습니다.");
			document.getElementById('contents').focus();
			return;
		}
		else if(CheckStrLen(document.getElementById('title'),20) == false){
			alert("글자 수가 초과했습니다. 다시 작성하여 주세요");
			document.getElementById('title').focus();
			document.getElementById('title').value = '';
			return ;
		}
		else {
			document.getElementById('updateBtn').disabled = true;
			document.tableListVO.submit();
		}
	}
	function trim(str) {
		return str.replace(/(^\s*)|(\s*$)/gi, '');
	}

	function CheckStrLen(textval,maxlen){
        var temp; //들어오는 문자값...
        var msglen;
        msglen = maxlen*2;
        l = textval.value.length;
        tmpstr = "" ;
        if (l == 0){
        }else{
            for(k=0;k<l;k++){
                temp = textval.value.charAt(k);
                if (escape(temp).length > 4){
                    msglen -= 2;
                }else{
                    msglen--;
                }
                if(msglen < 0){
                	textval.value = tmpstr;
                    return false;
                }else{
                    tmpstr += temp;
                }
            }
        }
        return true;
    }
</script>