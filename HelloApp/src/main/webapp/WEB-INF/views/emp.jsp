<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<form name= "myFrm" action= "employee.do" method = "post">
		<table class="table">
		<tr>
		<td><label>사원번호 :</label></td>
		<td><input type="number" name= "eid"></td>
		</tr>
		<tr>
		<td><label>FirstName :</label></td>
		<td><input type="text" name= "First_name"></td>
		</tr>
		<tr>
		<td><label>LastName :</label></td>
		<td><input type="text" name= "Last_name"></td>
		</tr>
		<tr>
		<td><label>이메일 :</label></td>
		<td><input type="email" name= "email"></td>
		</tr>
		<tr>
		<td><label>입사일자 :</label></td>
		<td><input type="date" name= "hire_date"></td>
		</tr>
		<tr>
		<td><label>직무 :</label></td>
		<td>
		<select name = "job">
			<option value = "IT_PROG">개발자</option>
			<option value = "SA_REP" selected>영업사원</option>
			<option value = "SA_MAN">영업팀장</option>
		</select>
		</td>
		</tr>
		</table>
		<br>
		<input type = "submit" value="전송">
		<input type = "button" value="조회" onclick="empGetFnc()">
		<input type = "button" value="다음" onclick="daumGetFnc()">

		<a href = "../result/empList.jsp">조회.</a>
	</form>
	<script>
		//console.log(document.forms[0].method);
		function empGetFnc(){
			document.forms.myFrm.method = 'get';
			document.forms.myFrm.action = 'empList.do';
			document.forms.myFrm.submit();//submit
		
		}
		function daumGetFnc(){
			console.log(document.forms);
			document.forms.myFrm.method='delete'
			document.forms.myFrm.submit();
		}
	</script>
	 <jsp:include page="../includes/footer.jsp"></jsp:include>
