package co.yedam.emp.service;

import java.util.List;

import co.yedam.emp.vo.EmpVO;

//업무에 대한 정의 : interface 에 정의 하고 구현하는 클래스를 통해 실현.
public interface EmpService {
	public List<EmpVO> empList();//목록.
	public int addEmp(EmpVO emp); //등록.
	public EmpVO getEmp(int empId);//조회.
	public int updateEmp(EmpVO emp);//수정
	
}
