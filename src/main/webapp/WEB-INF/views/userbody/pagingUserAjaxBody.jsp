<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(function() {

		
		pagingUserAjax(1,5);
		
		// .user 로하면 안되고 문서가 로딩되었을 때 항상 있는 것으로 해야됨 ** 
		$("#userTbody").on("click",".user", function() {
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
	

	function pagingUserAjax(page, pageSize) {
		//ajax를 통해 사용자 리스트를 가져온다 : 1page, 5pageSize
		$.ajax({
			// 방법2
			url : "/ajaxUser/pagingUserAjaxHtml",
			data : "page=" + page + "&pageSize=" + pageSize,
			//			 			data : {page : 1 , pageSize : 5}
			// 서버쪽에서 보내준 데이터를 인자로 받는다 - 콜백 (호출해준 함수를 등록 ) 
			success : function(data) {
				console.log(data);
				//			 				$.each(루프돌 대상, 처리해줄 함수(인덱스,값) ) 
				//			 					userTbody에 넣기 (문자를 처리해주기 위해 '' 또는 escape 문자 사용 )
				//			 				$('#userTbody').html(data);
				//			 				####을 기준으로 구분하기
				var html = data.split("####################");
				$('#userTbody').html(html[0]);
				$('#pagination').html(html[1]);
// 					$('#pagination').html(data);
			}
		});
	}
</script>
<%-- head, body :   layout에 head와 body 가 들어가므로 지워야한다 --%>
	<form id="frm" action="${cp}/userbody/detailUserTiles">
		<input type="hidden" id="userid" name="userid" value="/">
	</form>
					<div class="row">
						<div class="col-sm-8 blog-main">
							<h2 class="sub-header">사용자(ajaxView)</h2>
							<div class="table-responsive">
								<table class="table table-striped">
									<tr>
										<th>사용자 아이디</th>
										<th>사용자 이름</th>
										<th>사용자 별명</th>
										<th>등록일시</th>
									</tr>
									<tbody id="userTbody"/>
								</table>
							</div>

							<a id="insertBtn" class="btn btn-default pull-right">사용자 등록</a>
							<div class="text-center">
								<ul id="pagination" class="pagination">
									
								</ul>
							</div>
						</div>
					</div>