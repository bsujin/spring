<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<title>userModify.Jsp</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%@include file="/WEB-INF/views/common/common_lib.jsp"%>

<script>
$(function(){
	$('#addrBtn').on('click', function(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	$('#addr1').val(data.roadAddress); //도로주소
				$('#zipcode').val(data.zonecode);  //우편번호
			//사용자 편의성을 위해 상세주소 입력 input 태그로 focus설정
			$('#addr2').focus();
			
	        }
	    }).open();
		
	});
});

</script>

</head>

<body>
		<div class="row">

		
   			  <form class="form-horizontal" role="form"  action="${cp }/userbody/modifyUserTiles" method="post"
   			  enctype="multipart/form-data">
               
               <input type="hidden" name="userid" value="${user.userid }" />
               
               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
                  <div class="col-sm-8">
                     <label class="control-label">${user.userid }</label>
                  </div>
               </div>
               
					<div class="form-group">
						<label for="profile" class="col-sm-2 control-label">사용자 사진</label>
						<div class="col-sm-10">
<%-- 							<img src="${cp }/profile/${user.userid }.png" /> <br> --%>
							<img src="/user/profile?userid=${user.userid }" /> <br> <br>
							<input type="file" class="form-control" name="profile"/>
						</div>
					</div>

               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
                  <div class="col-sm-8">
                     <input type="text" class="form-control" id="usernm" name="usernm" placeholder="사용자 이름" value="${user.usernm }">
                  </div>
               </div>
                  
               <div class="form-group">
                  <label for="userNm" class="col-sm-2 control-label">별명</label>
                  <div class="col-sm-8">
                     <input type="text" class="form-control" id="alias" name="alias" placeholder="별명" value="${user.alias }">
                  </div>
               </div>
                     
               <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">비밀번호</label>
                  <div class="col-sm-8">
                     <input type="password" class="form-control" id="pass" name="pass" placeholder="비밀번호" value="${user.pass }">
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">등록일자</label>
                  <div class="col-sm-8">
                     <input type="text" class="form-control" id="reg_dt" name="reg_dt" placeholder="등록일자" value="<fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd"/>">
<%--                      <input type="text" class="form-control" id="reg_dt" name="reg_dt" placeholder="등록일자" value="${user.reg_dt }"> --%>
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">도로주소</label>
                  <div class="col-sm-8">
                     <input type="text" class="form-control" id="addr1" name="addr1" placeholder="도로주소" value="${user.addr1 }" readonly>
                  </div>
                  <div class="col-sm-2">
                     <button type="button" id="addrBtn"class="btn btn-default">주소검색</button>
                  </div>
               </div>
               
               <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">상세주소</label>
                  <div class="col-sm-8">
                     <input type="text" class="form-control" id="addr2" name="addr2" placeholder="상세주소" value="${user.addr2 }">
                  </div>
               </div>
               <div class="form-group">
                  <label for="pass" class="col-sm-2 control-label">우편번호</label>
                  <div class="col-sm-8">
                     <input type="text" class="form-control" id="zipcode" name="zipcode" placeholder="우편번호" value="${user.zipcode }" readonly>
                  </div>
               </div>

               <div class="form-group">
                  <div class="col-sm-offset-2 col-sm-10">
                     <button type="submit" class="btn btn-default">수정</button>
                  </div>
               </div>
            </form>
         </div>
