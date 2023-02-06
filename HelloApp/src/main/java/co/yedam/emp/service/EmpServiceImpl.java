package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import co.yedam.emp.dao.EmpDAO;
import co.yedam.emp.vo.EmpVO;

public class EmpServiceImpl implements EmpService {
	//jdbc 활용 db 처리.
	EmpDAO dao = EmpDAO.getInstance();
	
	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}

	@Override
	public int addEmp(EmpVO emp) {
		return dao.insertEmp(emp);
	}

	@Override
	public EmpVO getEmp(int empId) {
		return dao.searchEmp(empId);
	}

	@Override
	public int updateEmp(EmpVO emp) {
		return dao.updateEmp(emp);
	}

	@Override
	public Map<String, String> jobList() {
		return dao.jobList();
	}

	@Override
	public int delEmp(int id) {
		return dao.deleteEmp(id);
	}
}
