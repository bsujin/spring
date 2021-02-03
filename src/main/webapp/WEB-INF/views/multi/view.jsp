<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/mvc/multi/param" method="post">
	userid : <input type="text" name="userid" value="brown"/> <br>
	userid : <input type="text" name="userid" value="sally"/> <br>
	
	<input type="submit" value="전송"/>
	</form>
	
		
	<h3>List&lt;userVo&gt;</h3>
					<!-- name :	감싸고 있는 필드객체, 인덱스, 값 -->
	<form action="/mvc/multi/param" method="post">
		userid : <input type="text" name="userVoList[0].userid" value="brown" /> <br>
		userid : <input type="text" name="userVoList[1].userid" value="sally" /> <br>
		
		usernm : <input type="text" name="userVoList[0].usernm" value="브라운" /> <br>
		usernm : <input type="text" name="userVoList[1].usernm" value="샐리" /> <br>
		 <input type="submit" value="전송">
	</form>
	
</body>
</html>