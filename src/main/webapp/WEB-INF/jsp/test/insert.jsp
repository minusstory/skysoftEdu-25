<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<form action="secondInsert.do" method="POST" id="boardVO" name="boardVO">
	<table border="1">
		<tr>
			<td width="150">제목</td>
			<td width="300"><input type="text" maxlength="30" id="title" name="title" "></td>
		</tr>

		<tr>
			<td width="150">내용</td>

			<td width="300"><textarea id="contents" wrap="hard" cols="30" rows="10" name="contents" ></textarea>
			</td>
		</tr>

	</table>

	<table>
		<tr>
			<td>
				<div class="btn">
					<input type="button" id="submitBtn" name="submitBtn" value="등록" onclick="javascript:formcheck()" />
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

<script type="text/javascript">

	function formcheck(){
		if(document.getElementById('title').value == ''){
			alert("제목을 입력하지 않으셨습니다.");
			document.getElementById('title').focus();
			return ;
		}
		else if(trim(document.getElementById('contents').value) == ''){
			alert("내용을 입력하지 않으셨습니다.");
			document.getElementById('contents').focus();
			return ;
		}
		else if(CheckStrLen(document.getElementById('title'),10) == false){
			alert("글자 수가 초과했습니다. 다시 작성하여 주세요");
			document.getElementById('title').focus();
			document.getElementById('title').value = '';
			return ;
		}
		else{
			document.getElementById('submitBtn').disabled=true;
 			document.boardVO.submit();
		}
	}
	// ---------------------------------------------------------------------------------
	// 문자열 앞/뒤 공백제거
	// ---------------------------------------------------------------------------------
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
// 					alert("글자 수가 초과했습니다. 다시 작성하여 주세요");
// 					document.getElementById('title').focus();
                	textval.value = tmpstr;
//                     break;
                    return false;
                }else{
                    tmpstr += temp;
                }
            }
        }
        return true;
    }

</script>
