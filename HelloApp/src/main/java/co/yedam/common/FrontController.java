package co.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.command.EmpControl;
import co.yedam.emp.command.EmpDetailControl;
import co.yedam.emp.command.EmpForm;
import co.yedam.emp.command.EmpList;
import co.yedam.emp.command.EmpModFormControl;
import co.yedam.emp.command.EmpModifyControl;
import co.yedam.emp.command.ServiceControl;
import co.yedam.member.command.LoginControl;
import co.yedam.member.command.LoginFormControl;
import co.yedam.member.command.LogoutControl;
import co.yedam.member.command.MemberListControl;
import co.yedam.member.command.SignOnControl;
import co.yedam.member.command.SignOnFormControl;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	//url 패턴과 실행할 프로그램과 매핑
	Map<String,Command> map ;
	
	public FrontController()	{
		map = new HashMap<>();
	}
	@Override
	public void init() throws ServletException {
		//첫페이지 지정.
		map.put("/main.do", new MainControl());
		
		map.put("/service.do",new ServiceControl());
		// get : 목록출력(json)요청, post : 입력처리.
		map.put("/employee.do", new EmpControl());
		//xxxForm.do : 페이지 오픈. <= 폴더를 WEB-INF로 옮겨서 톰캣이 url방식으로의 접근을 막았다
		map.put("/empForm.do", new EmpForm());
		map.put("/empList.do", new EmpList());//목록페이지
		map.put("/empDetail.do", new EmpDetailControl()); 
		map.put("/empModForm.do", new EmpModFormControl());// 수정 화면 페이지
		map.put("/empModify.do", new EmpModifyControl());// 
		map.put("/errorPage.do", new ErroPage());// 
		map.put("/empRemove.do", new EmpRemoveControl());// 삭제 처리 페이지
		
		map.put("/loginForm.do", new LoginFormControl());// 로그인 화면
		map.put("/login.do", new LoginControl());// 로그인 페이지
		map.put("/logout.do", new LogoutControl());// 로그아웃 처리
		map.put("/signOnForm.do", new SignOnFormControl());// 회원가입 화면 
		map.put("/signon.do", new SignOnControl());// 회원가입 화면 
		map.put("/memberList.do", new MemberListControl());// 회원가입 화면 
		
		
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");//요청에 한글
		resp.setCharacterEncoding("utf-8");//응답 한글
		resp.setContentType("text/html;charset=utf-8");
		
		//url 패턴을 확인 => 요청페이지 어떤건지?
		String uri =req.getRequestURI();//http://localhost:8081/HelloApp/service.do
		String context = req.getContextPath();//  /HelloApp
		String page = uri.substring(context.length());
		
		
//		System.out.println("uri:"+ uri );
//		System.out.println("context: "+context);
		System.out.println(page);
		
		Command command = map.get(page);
		command.exec(req,resp);
		
	}
	@Override
	public void destroy() {
	}
}
