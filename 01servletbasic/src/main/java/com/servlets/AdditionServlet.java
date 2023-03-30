package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AdditionServlet extends GenericServlet {



	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String Num1 = req.getParameter("num1");
		String Num2 = req.getParameter("num2");
		
		if(Num1==null || Num2==null || Num1.isBlank() || Num2.isBlank() || Num1.isEmpty() || Num2.isEmpty()) {
			out.println("Input cannot be null");
		}else {
		try {
		int n1=Integer.parseInt(Num1);
		int n2=Integer.parseInt(Num2);
		int result=n1+n2;
		
		out.println("The result is :"+(result));
		
	}catch(NumberFormatException e) {
		out.println("Enter a valid input");
	}

}}
}