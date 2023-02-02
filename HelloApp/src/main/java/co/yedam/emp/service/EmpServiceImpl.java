package co.yedam.emp.service;

import java.util.List;

import co.yedam.emp.dao.EmpDAO;
import co.yedam.emp.vo.EmpVO;

public class EmpServiceImpl implements EmpService {
	//jdbc 활용 db 처리.
	EmpDAO dao = EmpDAO.getInstance();
	
	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}
}
