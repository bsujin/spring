<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- c:forEach 빼고는 다 불필요하므로 지워준다 -->

<!-- import유의 -->
<c:forEach items="${userList }" var="user">
	<tr class="user" data-userid="${user.userid }">
		<td>${user.userid }</td>
		<td>${user.usernm }</td>
		<td>${user.alias }</td>
		<td><fmt:formatDate value="${user.reg_dt }" pattern="yyyy.MM.dd" /></td>
	</tr>
</c:forEach>


####################
<!-- ul태그 안쪽의 내용 pagination -->
<%-- pagination 값이 4이므로 1부터 4까지 4번 반복된다
								     전체 사용자수 : 16명
								     페이지 사이즈 : 5
								     전체 페이지 수 : 4페이지
								 --%>
<!-- 가장 이전페이지 -->
<li class="prev">
<a href="javascript:pagingUserAjax(1,${pageVo.pageSize });">«</a>
</li>
<c:forEach begin="1" end="${pagination }" var="i">
	<c:choose>
		<c:when test="${pageVo.page == i}">
			<li class="active"><span>${i }</span></li>
		</c:when>
		<c:otherwise>
			<li><a href="javascript:pagingUserAjax(${i },${pageVo.pageSize })">${i }</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
<!-- 가장 마지막 페이지 -->
<li class="next"><a href="javascript:pagingUserAjax(${pagination },${pageVo.pageSize })">»</a>
</li>