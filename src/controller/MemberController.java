package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.ActionForward;
import service.IdCheck;
import service.Login;
import service.MemberInsert;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("*.do")
public class MemberController extends HttpServlet {
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURI= request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		System.out.println("RequestURI=" + RequestURI);
		System.out.println("contextPath=" + contextPath);
		System.out.println("command=" + command);
		
		Action action=null;
		ActionForward forward=null;
		//회원가입
		if(command.equals("/MemberInsert.do")) {
			try {
				action =  new MemberInsert();//upcasting
				forward = action.execute(request, response);
			}catch(Exception e){
				System.out.println("MemberInsert fail");
			}
		//로그인	
		}else if(command.equals("/Login.do")) {
			try {
				action =  new Login();//upcasting
				forward = action.execute(request, response);
			}catch(Exception e){
				System.out.println("MemberInsert fail");
			}
		}else if(command.equals("/IdCheck.do")) {
			
			try {
				action =  new IdCheck();//upcasting
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(forward!=null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
