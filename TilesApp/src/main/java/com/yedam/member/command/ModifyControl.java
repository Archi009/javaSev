package com.yedam.member.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;


public class ModifyControl implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
	
		
		String id = req.getParameter("member_id");
		String pw = req.getParameter("member_pw");
		String name = req.getParameter("member_name");
		String phone = req.getParameter("member_phone");
		String addr = req.getParameter("member_addr");
		
		MemberVO vo = new MemberVO();
		
		vo.setMemberId(id);
		vo.setMemberPw(pw);
		vo.setMemberName(name);
		vo.setMemberPhone(phone);
		vo.setMemberAddr(addr);

		
		// 서비스 로직
//		EmpService service = new EmpServiceImpl();
		MemberService service = new MemberServiceMybatis();
		service.updateMember(vo);
		// 요청 재지정.
		return "mypageForm.do";
	}

}
