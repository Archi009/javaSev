package com.yedam.emp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/empListJson")
public class EmpListJson extends HttpServlet {

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String hire = req.getParameter("hire");
		String job = req.getParameter("job");

		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setEmail(email);
		vo.setHireDate(hire);
		vo.setJobId(job);

		System.out.println(vo);

		resp.getWriter().print("compliete");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String hire = req.getParameter("hire");
		String job = req.getParameter("job");

		EmpVO vo = new EmpVO();
		vo.setEmployeeId(Integer.parseInt(id));
		vo.setLastName(name);
		vo.setEmail(email);
		vo.setHireDate(hire);
		vo.setJobId(job);

		EmpDAO dao = new EmpDAO();
		//param=update 
		if(param.equals("update")){
			if(dao.updateEmp(vo)>0) {
				resp.getWriter().print("{\"retCode\":\"Success\"}");
			}else {
			resp.getWriter().print("{\"retCode\":\"Fail\"}");
			}
		}else {
			if (dao.addEmp(vo) > 0) {
				resp.getWriter().print("{\"retCode\":\"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\":\"Fail\"}");
			}
			
		}

	}

	// ????????? ?????? (IoC (inversion of control))
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");
		String id = req.getParameter("del_id"); // ????????????????????? del_id??? ???????????? ??????.
		
		//{id: 101, retcode:Success/Fail}
		Map<String, Object> map = new HashMap<>	();
		map.put("id", id);
		
		EmpDAO dao = new EmpDAO();
		if (dao.delEmp(Integer.parseInt(id)) > 0) {
			// {"retCode":"Success"}
//			resp.getWriter().print("{\"retCode\":\"Success\"}");
			map.put("retCode", "Success");
		} else {
//			resp.getWriter().print("{\"retCode\":\"Fail\"}");
			// {"retCode":"Fail"}
			map.put("retCode", "Fail");
		}
//	map=> json
		Gson gson = new GsonBuilder().create();
		resp.getWriter().print(gson.toJson(map));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");

		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		// [{"id":100,"firstName":"Hong","email":"hong"..../..},{},{},{},{},{},{}]
		String json = "[";

		for (int i = 0; i < list.size(); i++) {
			json += "{\"id\":" + list.get(i).getEmployeeId() + ",\"lastName\":\"" + list.get(i).getLastName()
					+ "\",\"email\":\"" + list.get(i).getEmail() + "\",\"hireDate\":\""
					+ list.get(i).getHireDate().substring(0, 10) + "\",\"job\":\"" + list.get(i).getJobId() + "\"}";
			if (i + 1 != list.size()) {
				json += ","; // json??? ????????? ?????? ????????? ?????? ?????????
			}
		}
		json += "]";
		resp.getWriter().print(json);

	}

}
