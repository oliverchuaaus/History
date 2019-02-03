package com.tougher.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.text.StringEscapeUtils;

@WebServlet("/xssPost")
public class XssPostServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryStr = request.getParameter("first_name");
		queryStr = StringEscapeUtils.escapeHtml4(queryStr);
		request.setAttribute("queryStr", queryStr);


		ServletContext context = getServletContext();
		RequestDispatcher dispatcher = context.getRequestDispatcher("/xss/forms/form-post-process.jsp");
		dispatcher.forward(request, response);
	}
}
