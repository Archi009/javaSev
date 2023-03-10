package com.yedam.java.practice;

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
import com.yedam.emp.EmpDAO;
import com.yedam.emp.EmpVO;

@WebServlet("/SwimListJson")
public class SwimListJson extends HttpServlet {

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//
//		String id = req.getParameter("id");
//		String name = req.getParameter("name");
//		String email = req.getParameter("email");
//		String hire = req.getParameter("hire");
//		String job = req.getParameter("job");
//
//		ManVO vo = new ManVO();
//		vo.setEmployeeId(Integer.parseInt(id));
//		vo.setLastName(name);
//		vo.setEmail(email);
//		vo.setHireDate(hire);
//		vo.setJobId(job);
//
//		System.out.println(vo);
//
//		resp.getWriter().print("compliete");
//
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String param = req.getParameter("param");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String hire = req.getParameter("hire");
		String job = req.getParameter("job");

		ManVO vo = new ManVO();
		vo.setUserId(id);
		vo.setUserNm(name);
		vo.setUserAd(email);
		vo.setUserPh(hire);
		vo.setUserCl(job);

		Managing dao = new Managing();
		//param=update 
//		if(param.equals("update")){
//			if(dao.updateEmp(vo)>0) {
//				resp.getWriter().print("{\"retCode\":\"Success\"}");
//			}else {
//			resp.getWriter().print("{\"retCode\":\"Fail\"}");
//			}
//		}else {
			if (dao.addMan(vo) > 0) {
				resp.getWriter().print("{\"retCode\":\"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\":\"Fail\"}");
			}
			
		}

//	}

	// ????????? ?????? (IoC (inversion of control))
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setCharacterEncoding("utf-8");
//		resp.setContentType("text/json;charset=utf-8");
//		String id = req.getParameter("del_id"); // ????????????????????? del_id??? ???????????? ??????.
//		
//		//{id: 101, retcode:Success/Fail}
//		Map<String, Object> map = new HashMap<>	();
//		map.put("id", id);
//		
//		EmpDAO dao = new EmpDAO();
//		if (dao.delEmp(Integer.parseInt(id)) > 0) {
//			// {"retCode":"Success"}
////			resp.getWriter().print("{\"retCode\":\"Success\"}");
//			map.put("retCode", "Success");
//		} else {
////			resp.getWriter().print("{\"retCode\":\"Fail\"}");
//			// {"retCode":"Fail"}
//			map.put("retCode", "Fail");
//		}
////	map=> json
//		Gson gson = new GsonBuilder().create();
//		resp.getWriter().print(gson.toJson(map));
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");

		Managing dao = new Managing();
		List<ManVO> list = dao.manVoList();
		// [{"id":100,"firstName":"Hong","email":"hong"..../..},{},{},{},{},{},{}]
		String json = "[";

		for (int i = 0; i < list.size(); i++) {
			json += "{\"id\":\"" + list.get(i).getUserId() +"\",\"lastName\":\"" + list.get(i).getUserNm()
					+ "\",\"email\":\"" + list.get(i).getUserAd() + "\",\"hireDate\":\""
					+ list.get(i).getUserPh() + "\",\"job\":\"" + list.get(i).getUserCl() + "\"}";
			if (i + 1 != list.size()) {
				json += ","; // json??? ????????? ?????? ????????? ?????? ?????????
			}
		}
		json += "]";
		resp.getWriter().print(json);
	}

}
