package com.yedam;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;



@WebServlet("/myinfo")
public class FirstServlet extends HttpServlet { //http통신 요청-응답.

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
		//요청방식: post요청일 경우 tomcat 서버가 실행해줌.
		req.setCharacterEncoding("utf-8"); //한글화  
		
		
		String id = req.getParameter("emp_id");    //form 태그의 name속성의 값을 읽어 들일때.
		String name = req.getParameter("last_name");    
		String mail = req.getParameter("email");   
		String job = req.getParameter("job_id");    
		String hdate = req.getParameter("hire_date");  
		
		EmpVO emp = new EmpVO();
		emp.setEmployeeId(Integer.parseInt(id));  //setEmployeeId(변수 int);
		emp.setLastName(name);
		emp.setEmail(mail);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		System.out.println(emp);
		
		EmpDAO dao = new EmpDAO();
		dao.addEmp(emp);
		
		doGet(req, resp);  // doGet 메소드 호출.
		
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print("<h3>sevlet page</h3>"); //client 출력 스트림.
		resp.getWriter().print("<a href='info/resume.html'>이력서</a><br>");
		resp.getWriter().print("<a href='index.html'>첫페이지로 이동</a>");
		
		//jdbc 연결처리.
		EmpDAO dao = new EmpDAO();
		List<EmpVO>list = dao.empVoList();
		
		resp.getWriter().print("<ul>");
		for(EmpVO emp :list) {
			resp.getWriter().print("<li>"+emp.getEmployeeId()+"/"+emp.getLastName()+"/"+emp.getEmail());
		}
		resp.getWriter().print("</ul>");
		
		
		
	}
		
}
