package kr.mem.pojo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.mem.model.MemberDAO;
import kr.mem.model.MemberVO;

public class MemberUpdateController implements Controller {

	@Override
	public String requestHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String cpath = request.getContextPath();
		int num = Integer.parseInt(request.getParameter("num"));
		String addr = request.getParameter("addr");
		String phone = request.getParameter("phone");
		MemberVO vo = new MemberVO();
		vo.setNum(num);
		vo.setAddr(addr);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
		int cnt = dao.memberUpdate(vo);
		String page = null;
		if(cnt>0) {
			page = "redirect:"+cpath+"/list.do";
		}else {
			throw new ServletException("error");
		}
		return page;
	}
}
