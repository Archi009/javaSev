package co.yedam.emp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@AllArgsConstructor
//@ToString
//@NoArgsConstructor
//위에꺼 귀찮으면 아래꺼 하면 다 만들어줌
//lombok 의 기능
//기본값 빈값 만 만들어주고 결국
//캡슐화를 위한 조건문을 건 Setter나 매개변수 생성자는 직접 만들어야 한다.
@Data
public class EmpVO {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String jobId;
	private String hireDate;
	
}
