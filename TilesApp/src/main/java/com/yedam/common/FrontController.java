package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.command.*;
import com.yedam.notice.command.*;




public class FrontController extends HttpServlet {
	
	private Map<String,Command> map;
	private String charset;
	
	public FrontController() {
		map = new HashMap<String,Command>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		charset = config.getInitParameter("charset");
		
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListWithTables.do", new NoticeListTable());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm());//글등록 페이지
		map.put("/noticeAdd.do", new NoticeAdd());//글등록 처리
		map.put("/noticeAddJson.do", new NoticeAddJson());//datatable 연습용.
		//json 형식의 배열로 데이터를 보내준다.
		map.put("/noticeListJson.do", new NoticeListJson());//datatable 연습용.
		map.put("/noticeListAjax.do", new NoticeListAjax());//datatable 연습용.
		map.put("/noticeDelJson.do", new NoticeDelJson());//datatable 연습용.
		
		//댓글
		map.put("/replyList.do", new ReplyList());//댓글목록
		map.put("/removeReply.do", new RemoveReply());//댓글삭제
		map.put("/addReply.do", new AddReply());//댓글등록

		
		//회원관련
		map.put("/loginForm.do", new LoginForm());//로그인 화면
		map.put("/login.do", new Login());//로그인 처리
		map.put("/logout.do", new LogoutControl());// 로그아웃 처리
		map.put("/mypageForm.do", new MypageControl());//로그인 처리
		map.put("/modify.do", new ModifyControl());//개인정보 수정
		map.put("/imageUploade.do", new ImageUpload());//개인정보 수정
		
		//관리자 회원관리
		map.put("/memberManageForm.do", new MemberManager());//개인정보 수정
		map.put("/memberList.do", new memberList());//개인 정보 리스트 호출
		map.put("/addMember.do", new AddMember());//개인정보 등록
		map.put("/removeMember.do", new DelMember());//개인정보 삭제
		map.put("/modMemCo.do", new ModMemCo());//개인정보 삭제
		
		
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command command = map.get(page);
		String viewPage = command.exec(req, resp);
		//notice/noticeList.tiles
//		System.out.println(viewPage);
		
		if(viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req,resp);
		}else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
		} else if (viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0,viewPage.length()-5));
		}
		
		
	}
}
