package co.yedam.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.service.EmpServiceMybatis;

public class EmpRemoveControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		//삭제 처리 EmpService -> EmpServiceImpl ->EmpDAO
//		EmpService service = new EmpServiceImpl();
		EmpService service = new EmpServiceMybatis();
		int r = service.delEmp(Integer.parseInt(id));
		try {
			if (r > 0) {
				resp.sendRedirect("empList.do");//				
			} else {
				resp.sendRedirect("errorPage.do");			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
