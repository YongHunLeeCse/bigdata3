package kr.mem.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;
import kr.mem.pojo.*;

public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		// 1. 어떤 요청인지 파악하는 작업 -> *.do이므로 어디로 가는지 파악
		String reqUrl = request.getRequestURI();
		System.out.println(reqUrl);
		String ctxPath = request.getContextPath();
		System.out.println(ctxPath);
		// 클라이언트가 요청한 명령
		String command = reqUrl.substring(ctxPath.length()); // endindex 안 쓰면 끝까지
		System.out.println(command);
		// 각 요청에 따라 처리 하기(분기작업)
		Controller controller = null;
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		// 핸들러매핑
		// /list.do ---> MemberListController
		// /insert.do--> MemberInsertController
		// /insertForm.do--> MemberInsertFormController
		// /delete.do--> MemberDeleteController
/*		if(command.equals("/list.do")) { // 포조로 뺌
//			ArrayList<MemberVO> list = dao.memberAllList();
//			// member/memberList.jsp
//			request.setAttribute("list", list);
//		    RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
//		    rd.forward(request, response);
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response);
			//RequestDispatcher rd = request.getRequestDispatcher(nextView);
			//rd.forward(request, response);	// 포워딩 : 객체바인딩으로 정보를 가져감(주소 안 바뀜)
		}else if(command.equals("/insert.do")) { // 포조로 뺌
//			String name = request.getParameter("name");
//	        String phone = request.getParameter("phone");
//	        String addr = request.getParameter("addr");
//	        
//	        MemberVO vo = new MemberVO();
//	        vo.setName(name);
//	        vo.setPhone(phone);
//	        vo.setAddr(addr);
//	        
//	        int cnt = dao.memberInsert(vo);
//	        
//	        if(cnt>0) {
//	        	response.sendRedirect("/mvc1/list.do");
//	        }else {
//	        	throw new ServletException("error");
//	        }
			controller = new MemberInsertController();
			nextView = controller.requestHandle(request, response);
        	//response.sendRedirect(nextView);	// 리다이렉트 : 전화돌리기, 페이지 바꾸기(작업한 정보를 넘길 수 없음, 주소 바뀜)
		}else if(command.equals("/insertForm.do")) {
			////response.sendRedirect("member/member.html");
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response);
			//RequestDispatcher rd = request.getRequestDispatcher(nextView); // 경로 안 바뀌게
			//rd.forward(request, response);
		}else if(command.equals("/delete.do")) {
//			int num=Integer.parseInt(request.getParameter("num"));
//	        int cnt = dao.memberDelete(num);
//	        if(cnt>0) {
//	           response.sendRedirect("/mvc1/list.do");
//	        }else {
//	           throw new ServletException("error");
//	        }
			controller = new MemberDeleteController();
			nextView = controller.requestHandle(request, response);
        	//response.sendRedirect(nextView);
		}*/
		// View 페이지로 연동하는 부분
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!=-1) { // 문자열이 있는지 없는지 찾음
				String[] sp = nextView.split(":"); // : 기준으로 스플릿(sp[0] : sp[1])
				response.sendRedirect(sp[1]);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
	}
}
