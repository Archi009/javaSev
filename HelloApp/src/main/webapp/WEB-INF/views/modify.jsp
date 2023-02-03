<%@page import="co.yedam.emp.vo.EmpVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<%
EmpVO emp = (EmpVO) request.getAttribute("searchVO");
Integer age = (Integer) request.getAttribute("myAge");
String id = (String) request.getAttribute("loginId");
%>


<%=age %>, <%=id %>
<h3>현재 페이지는 empDetail.do의 결과 empDetail.jsp입니다.</h3>
<form>
<table class="table">
	<tr>
		<th>사원번호</th>		
		<td><%=emp.getEmployeeId()%></td>
	</tr>
	<tr>
		<th>Firstname</th>
		<td><input type="text" value="<%=emp.getFirstName()%>"></td>
	</tr>
	<tr>
		<th>Lastname</th>
		<td><input type="text" value="<%=emp.getLastName()%>"></td>
	</tr>
	<tr>
		<th>Email</th>
		<td><input type="text" value="<%=emp.getEmail()%>"></td>
	</tr>
	<tr>
		<th>Job</th>
		<td><input type="text" value="<%=emp.getJobId()%>"></td>
	</tr>
	<tr>
		<th>HireDate</th>
		<td><%=emp.getHireDate()%></td>
	</tr>
	<tr>
	<td colsapn="2" align="center">
	<button class="btn btn-primary" onclick="location.href='empModForm.do?<%=emp.getFirstName()%>'">변경</button>
	<button class="btn btn-warning" onclick="location.href='empModForm.do?<%=emp.getFirstName()%>'">변경</button>
	</td>
	</tr>
</table>
</form>






<jsp:include page="../includes/footer.jsp"></jsp:include>