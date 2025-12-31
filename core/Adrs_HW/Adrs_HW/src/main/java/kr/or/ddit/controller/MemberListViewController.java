package kr.or.ddit.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/memberlist")
public class MemberListViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String logicalViewName = "/adrs/adrsForm";
		if (logicalViewName.startsWith("redirect:")) {
			String location = logicalViewName.replace("redirect:", req.getContextPath());
			resp.sendRedirect(location);
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			String view = prefix + logicalViewName + suffix;
			req.getRequestDispatcher(view).forward(req, resp);
		}
	}
}
