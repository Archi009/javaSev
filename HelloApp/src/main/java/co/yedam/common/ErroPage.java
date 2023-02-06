package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErroPage implements Command{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
//	새로고침 하면	
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
