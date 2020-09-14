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
		// 1. � ��û���� �ľ��ϴ� �۾� -> *.do�̹Ƿ� ���� ������ �ľ�
		String reqUrl = request.getRequestURI();
		System.out.println(reqUrl);
		String ctxPath = request.getContextPath();
		System.out.println(ctxPath);
		// Ŭ���̾�Ʈ�� ��û�� ���
		String command = reqUrl.substring(ctxPath.length()); // endindex �� ���� ������
		System.out.println(command);
		// �� ��û�� ���� ó�� �ϱ�(�б��۾�)
		Controller controller = null;
		String nextView = null;
		HandlerMapping mappings = new HandlerMapping();
		controller = mappings.getController(command);
		nextView = controller.requestHandle(request, response);
		// �ڵ鷯����
		// /list.do ---> MemberListController
		// /insert.do--> MemberInsertController
		// /insertForm.do--> MemberInsertFormController
		// /delete.do--> MemberDeleteController
/*		if(command.equals("/list.do")) { // ������ ��
//			ArrayList<MemberVO> list = dao.memberAllList();
//			// member/memberList.jsp
//			request.setAttribute("list", list);
//		    RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
//		    rd.forward(request, response);
			controller = new MemberListController();
			nextView = controller.requestHandle(request, response);
			//RequestDispatcher rd = request.getRequestDispatcher(nextView);
			//rd.forward(request, response);	// ������ : ��ü���ε����� ������ ������(�ּ� �� �ٲ�)
		}else if(command.equals("/insert.do")) { // ������ ��
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
        	//response.sendRedirect(nextView);	// �����̷�Ʈ : ��ȭ������, ������ �ٲٱ�(�۾��� ������ �ѱ� �� ����, �ּ� �ٲ�)
		}else if(command.equals("/insertForm.do")) {
			////response.sendRedirect("member/member.html");
			controller = new MemberInsertFormController();
			nextView = controller.requestHandle(request, response);
			//RequestDispatcher rd = request.getRequestDispatcher(nextView); // ��� �� �ٲ��
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
		// View �������� �����ϴ� �κ�
		if(nextView!=null) {
			if(nextView.indexOf("redirect:")!=-1) { // ���ڿ��� �ִ��� ������ ã��
				String[] sp = nextView.split(":"); // : �������� ���ø�(sp[0] : sp[1])
				response.sendRedirect(sp[1]);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/"+nextView);
				rd.forward(request, response);
			}
		}
	}
}
