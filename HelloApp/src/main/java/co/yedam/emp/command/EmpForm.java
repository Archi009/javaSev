package co.yedam.emp.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;

public class EmpForm implements Command {
	//링크 접속을 막기위해 jsp 파일의 위치를 WEB_INF(톰캣이 url을 통해 접속 하지 못하게 만든 폴더)
//	로 이동시켰다.
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher rd =req.getRequestDispatcher("WEB-INF/views/emp.jsp");
		try {
			rd.forward(req,resp);
		}catch(Exception e ) {
			e.printStackTrace();
		}
	}

}
