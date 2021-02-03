<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>PagingUser.Jsp</title>

<%@include file="/WEB-INF/views/common/common_lib.jsp"%>
<link href="${cp }/css/dashboard.css"
	rel="stylesheet">
<link href="${cp }/css/blog.css"
	rel="stylesheet">
</head>
<script type="text/javascript">
	// 문서 로딩이 완료되고 나서 실행되는 영역
	$(function() {
		$(".user").on("click", function() {
			// this :   해당 버튼을 클릭한 객체 - user를 뜻함 (클릭 이벤트가 발생한 element)

			// data-속성명 : 지정한 속성명만 넣어주면 된다 data-userid --> userid
			//	속성명은 대소문자를 무시하고 소문자로 인식한다
			// data-userId ==> data-userid 로 인식하므로 애초에 대문자를 쓰지않는것이 좋음

			// console.log : 최근 브라우저에서 나온 기능- 출력해주는 기능 : 예전에는 alert를 사용함  - 요즘은 로그를 확인할 목적으로 잘 사용하지 않는다 
			// 		console.log($(this).data("userid"));
			var userid = $(this).data("userid");

			//select를 해서 값을 userid
			$("#userid").val(userid);

			//form태그를 이용하여 전송
			$("#frm").submit();

		});
		$('#insertBtn').on('click', function() {
			location.href = "/user/registUser"
		})

	})
</script>
<body>
	<!-- 	단순정보이므로 get방식 , method를 따로 지정 안해도 된다 -->
	<%@include file="/WEB-INF/views/common/header.jsp"%>
	<form id="frm" action="${cp}/user/detailUser">
		<input type="hidden" id="userid" name="userid" value="/">
		<div class="container-fluid">
			<div class="row">

				<div class="col-sm-3 col-md-2 sidebar">
					<%@include file="/WEB-INF/views/common/left.jsp"%>
				</div>

				<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


					<div class="row">
						<div class="col-sm-8 blog-main">
							<h2 class="sub-header">사용자</h2>
							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>사용자 아이디</th>
										<th>사용자 이름</th>
										<th>사용자 별명</th>
										<th>등록일시</th>
									</tr>

									<!-- import유의 -->
									<c:forEach items="${userList }" var="user">
									<tr class="user" data-userid="${user.userid }">
										<td>${user.userid }</td>
										<td>${user.usernm }</td>
										<td>${user.alias }</td>
										<td><fmt:formatDate value="${user.reg_dt}" pattern="yyyy.MM.dd"/></td>
									</c:forEach>
								</table>
							</div>

							<a id="insertBtn" class="btn btn-default pull-right">사용자 등록</a>
							<div class="text-center">
								<ul class="pagination">
									<%-- pagination 값이 4이므로 1부터 4까지 4번 반복된다
								     전체 사용자수 : 16명
								     페이지 사이즈 : 5
								     전체 페이지 수 : 4페이지
								 --%>
									<!-- 가장 이전페이지 -->
									<li class="prev"><a href="${cp }/user/pagingUser?page=1&pageSize=${pageVo.pageSize }">«</a>
									</li>
									<c:forEach begin="1" end="${pagination }" var="i">
										<c:choose>
											<c:when test="${pageVo.page == i}">
												<li class="active"><span>${i }</span></li>
											</c:when>
											<c:otherwise>
												<li><a href="${cp }/user/pagingUser?page=${i }&pageSize=${pageVo.pageSize }">${i }</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- 가장 마지막 페이지 -->
									<li class="next"><a
										href="${cp }/user/pagingUser?page=${pagination }&pageSize=${pageVo.pageSize }">»</a>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

