package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceMybatis;
import co.yedam.member.vo.MemberVO;

public class ErroPage implements Command{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		MemberVO vo = new MemberVO();
		vo.setMemberId("user1");
		vo.setMemberName("Hong");
		vo.setMemberPhone("010-1111");
		vo.setMemberAddr("대구 중구 10번지");
		
		req.setAttribute("obj", vo);
		EmpService service = new EmpServiceMybatis();
		req.setAttribute("empList", service.empList());
		
//	새로고침 하면 (db수정 작업이 발생함 => sendRedirect()	
		try {
			req.getRequestDispatcher("WEB-INF/result/errorResult.jsp").forward(req,resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
