package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample") //*라는 요청으로 들어 오는 것은 아래 서블릿으로 처리하겠다
public class sampleServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//서블릿.생명주기 : 인스턴스 ->inint -> service ->destroy
		
		}
		
	
	
public sampleServlet() {
	System.out.println("생성자 호출");
}
@Override
public void init() throws ServletException {
	System.out.println("init 실행: 서버 실행 후 한 번만 실행됨.");
}
@Override
protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	System.out.println("service실행: 해당url을 호출 할 때 마다 실행");
}
@Override
public void destroy() {
	System.out.println("서버가 종료괸 때 한 번 실행됨.");
}

}
