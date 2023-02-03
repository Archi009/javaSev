package co.yedam.emp.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class EmpModifyControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// service:modEmp(EmpVO) -> serviceImpl: modEmp(EmpVO)-> dao : updateEmp(EmpVO)
		// 입력

		RequestDispatcher rd = null;
		
		String eid = req.getParameter("eid");
		String fName = req.getParameter("First_name");
		String lName = req.getParameter("Last_name");
		String job = req.getParameter("job");
		String hire = req.getParameter("hire_date");
		String mail = req.getParameter("email");

		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(eid));
		emp.setLastName(lName);
		emp.setFirstName(fName);
		emp.setEmail(mail);
		emp.setHireDate(hire);
		emp.setJobId(job);

		// 서비스 로직
		EmpService service = new EmpServiceImpl();
		int r = service.addEmp(emp);

		// 요청 재지정.
		try {
			if (r > 0) {
				resp.sendRedirect("empList.do");
//							rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
//							rd.forward(req, resp);//페이지 재지정
			} else {
				// resp.sendRedirect("WEB-INF/result/errorResult.jsp");
				rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
				rd.forward(req, resp);// 페이지 재지정
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
