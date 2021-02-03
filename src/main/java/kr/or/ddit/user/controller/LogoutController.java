package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 세션 삭제해주기
		req.getSession().invalidate();
	
//		redirect의 경우 ContextPath를 꼭 붙여야한다
		resp.sendRedirect(req.getContextPath() + "/login.jsp");
	}
}
