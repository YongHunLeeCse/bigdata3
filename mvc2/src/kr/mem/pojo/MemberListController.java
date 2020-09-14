package kr.mem.pojo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberListController implements Controller {
	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전체리스트를 가져오기
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberAllList();
		// 2. 객체바인딩
		request.setAttribute("list", list);
		// View -> member/memberList.jsp(포워딩까지 하면 각 포조마다 한 포워딩이 난립함)
		//return "/WEB-INF/member/memberList.jsp"; // 포워딩 할 뷰페이지 리턴
		return "member/memberList.jsp";
	}
}
