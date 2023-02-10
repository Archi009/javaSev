package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 성공하면 mypage로 이동하고
		// 로그인 실패하면 다시 로그인 화면으로 이동할때 "아이디와패스워드를 확인" 하도록.

		String method = req.getMethod();
		System.out.println("요청방식 : " + method);
		String id = req.getParameter("mid"); // form>input:name속성.
		String pw = req.getParameter("mpw");
		String link = "";

		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pw);

		MemberService service = new MemberServiceMybatis();
		MemberVO mvo = service.login(member);
		if (mvo != null) {
			HttpSession session = req.getSession();

			session.setAttribute("id", mvo.getMemberId());
			session.setAttribute("name", mvo.getMemberName());
			session.setAttribute("Auth", mvo.getResponsibility());
			
			req.setAttribute("vo", mvo)
			;
			link = "mypageForm.do";
		} else {
			req.setAttribute("result", "회원정보를 확인하세요");
			link = "member/login.tiles";
		}

		return link;
	}

}
