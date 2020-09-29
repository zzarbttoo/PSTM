<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th {
	width: 120px;
	font-weight: lighter;
	font-size: 20px;
}

.inputSize {
	width: 400px;
	height: 50px;
	font-size: 22px;
	border: none;
}

.inputPw {
	width: 400px;
	height: 50px;
	font-size: 22px;
	border: none;
}

#signUpBtn {
	width: 530px;
	height: 50px;
	font-size: 22px;
	border: none;
	background-color: yellow;
}

#DupChk {
	width: 100px;
	height: 30px;
}
</style>

<script type="text/javascript">	
	function idChk() {

		var doc = document.getElementsByName("id")[0];
		if (doc.value == null || doc.value.trim() == "") {
			alert("Please enter your ID first!!");
		} else {
			open("signup.do?command=idChk&id=" + doc.value, "",
					"width=200,height=200");
		}

	}

	function idChkConfirm() {
		var chk = document.getElementsByName("id")[0].title;
		if (chk === "n") {
			alert("아이디 중복체크를 먼저 해주세여.");
			document.getElementsByName("id")[0].focus();
		}

	}
</script>

</head>
<body>

<%@ include file="./form/pstm_header.jsp"%>

<h1>트레이너 가입</h1>
<br/>

<form action="signup.do" method="post">
	<input type="hidden" name="command" value="signupT"/>

		<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="id" placeholder="아이디 입력" class="inputSize" required="required" title="n"/>
					<input type="button" value="중복체크" onclick="idChk();" id="DupChk" /></td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password" placeholder="비밀번호 입력" class="inputPw" onclick="idChkConfirm();" 
					required="required"  />				
					</td>	
				</tr>
				
				<tr>
					<th></th>
					<td><input type="password" name="password_key" placeholder="비밀번호 입력확인" class="inputPw"  />
					<span id="alert-success" style="display: none;">비밀번호가 일치합니다.</span>
    				<span id="alert-danger" style="display: none; color: red; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span>
					</td>			
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type="text" name="name" placeholder="이름" class="inputSize" onclick="idChkConfirm();" required="required" /></td>				
				</tr>
				
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value="M"/>남자
						<input type="radio" name="gender" value="F"/>여자
					</td>				
				</tr>				
				
				<tr>
					<th>휴대폰번호</th>
					<td><input type="text" name="phone" placeholder=" ex) 010-xxxx-xxxx" class="inputSize" /></td>				
				</tr>
				
				<tr>
					<th>주소</th>
					<td><input type="text" name="addr" class="inputSize" required="required"/></td>
				</tr>
				
				<tr>
					<th>상세주소</th>
					<td><input type="text" name="detailaddr" class="inputSize" required="required"/></td>
				</tr>
								
				<tr>
					<th>경력사항</th>
					<td><textarea rows="10" cols="55" name="career"></textarea></td>				
				</tr>
				
				<tr>
					<th>각오 한마디</th>
					<td><textarea rows="5" cols="55" name="mycomment"></textarea></td>
				</tr>
				
				<tr>
					<td colspan="2"><input type="submit" value="가입완료" id="signUpBtn"/></td>
				</tr>
								
		</table>

</form>

<%@ include file="./form/pstm_footer.jsp"%>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

$(".inputPw").focusout(function () {
    var pwd1 = $("input[name=password]").val();
    var pwd2 = $("input[name=password_key]").val();

    if ( pwd1 != "" && pwd2 == "" ) {
        null;
        
    } else if (pwd1 != "" || pwd2 != "") {

        if (pwd1 == pwd2) {
        	$("#alert-success").css('display', 'inline-block'); 	
            $("#alert-danger").css('display', 'none');
            
        } else {
        	alert("비밀번호가 일치하지 않습니다. 비밀번호를 재확인해주세요.");
            $("#alert-success").css('display', 'none');
            $("#alert-danger").css('display', 'inline-block');
        }
    }
});

</script>


</body>
</html>