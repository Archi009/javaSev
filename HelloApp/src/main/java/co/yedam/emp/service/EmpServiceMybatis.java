package co.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import co.yedam.common.DataSource;
import co.yedam.emp.vo.EmpVO;

//EmpServiceImpl : jdbc
//EmpServiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService {
	
	
	SqlSessionFactory sessionFactory = DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(); //괄호안에 ture삽입시 자동 커밋

	@Override
	public List<EmpVO> empList() {
		return session.selectList("co.yedam.emp.mapper.EmpMapper.empList");
	}

	@Override
	public int addEmp(EmpVO emp) {
		//session.commit();
		int r = session.insert("co.yedam.emp.mapper.EmpMapper.addEmp",emp);
		if(r>0) {
			session.commit();
		}else {
			session.rollback();
		}
		return r ;
	}

	@Override
	public EmpVO getEmp(int empId) {		
		return session.selectOne("co.yedam.emp.mapper.EmpMapper.getEmp",empId);
	}

	@Override
	public int updateEmp(EmpVO emp) {
		int r = session.update("co.yedam.emp.mapper.EmpMapper.modEmp",emp);
		if(r>0) {
			session.commit();
		}else {
			session.rollback();
		}
		return r ;
	}

	@Override
	public Map<String, String> jobList() {		
		return null; // session.selectMap("co.yedam.emp.mapper.EmpMapper.jobList");
	}

	@Override
	public int delEmp(int id) {
		int r =  session.delete("co.yedam.emp.mapper.EmpMapper.delEmp",id);
		if(r>0) {
			session.commit();
		}else {
			session.rollback();
		}
		return r ;
	}
	
}
