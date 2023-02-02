package co.yedam.emp.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.yedam.common.Command;
import co.yedam.emp.service.EmpService;
import co.yedam.emp.service.EmpServiceImpl;
import co.yedam.emp.vo.EmpVO;

public class ServiceControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("서비스 컨트롤......");
		EmpService service = new EmpServiceImpl();
		List<EmpVO> list = service.empList();
		
		//json목록.
		Gson gson = new GsonBuilder().create();
		try {
			resp.getWriter().print(gson.toJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
