<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>AllUserBody.Jsp</title>
<%@ include file="/WEB-INF/views/common/common_lib.jsp"%>
<!-- Custom styles for this template -->


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
								
									
									<c:forEach items="${userList }" var="user">
										<tr>
											<td>${user.userid }</td>
											<td>${user.usernm }</td>											
											<td>${user.alias }</td>
											<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd"/> </td>
										</tr>
									</c:forEach>
							</table>
						</div>

						<form action="${cp }/userbody/registUserTiles" method="get">
						<input type="submit" class="btn btn-default pull-right" value="사용자 등록">
						<a href="${cp }/user/excelDownload" class="btn btn-default pull-right">전체 사용자 엑셀 다운로드</a>
						</form>

						<div class="text-center">
							<ul class="pagination">
								<li><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
							</ul>
						</div>
					</div>
				</div>
