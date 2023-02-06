<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
EmpVO emp = (EmpVO) request.getAttribute("modiFyVO");
Integer age = (Integer) request.getAttribute("myAge");
String id = (String) request.getAttribute("loginId");
Map<String, String> list = (Map<String, String>) request.getAttribute("jobList");
%>


<%=age%>,
<%=id%>
<h3>현재 페이지는 empDetail.do의 결과 empDetail.jsp입니다.</h3>
<form action="./empModify.do" method="post">
	<table class="table">
		<tr>
			<th>사원번호</th>
			<td><input value="<%=emp.getEmployeeId()%>" name="id" readonly></td>
		</tr>
		<tr>
			<th>Firstname</th>
			<td><input type="text" value="<%=emp.getFirstName()%>"
				name="First_name"></td>
		</tr>
		<tr>
			<th>Lastname</th>
			<td><input type="text" value="<%=emp.getLastName()%>"
				name="Last_name"></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><input type="text" value="<%=emp.getEmail()%>" name="email"></td>
		</tr>
		<tr>
			<th>Job</th>
			<td><select name="job" id=jb>
					<%
					for (Entry<String, String> ent : list.entrySet()) {
						if (ent.getKey().equals(emp.getJobId())) {
					%>
					<option selected value="<%=ent.getKey()%>"><%=ent.getValue()%></option>
					<%
					}
					%>
					<option value="<%=ent.getKey()%>"><%=ent.getValue()%></option>
					<%
					}
					%>
			</select></td>
			<td></td>
		</tr>
		<tr>
			<th>HireDate</th>
			<td><input value="<%=emp.getHireDate().substring(0, 10)%>"
				name="hire_date" readonly></td>
		</tr>
		<tr>
			<td colsapn="2" align="center">
				<button class="btn btn-primary" type="submit">변경</button>
				<button class="btn btn-warning"
					onclick="location.href='empDetail.do?id=<%=emp.getFirstName()%>'">취소</button>
			</td>
		</tr>
	</table>
</form>






<jsp:include page="../includes/footer.jsp"></jsp:include>