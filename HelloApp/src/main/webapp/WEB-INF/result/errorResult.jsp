<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- jstl을 사용하기위해 import-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result/addResult.jsp</title>
</head>
<body>
	<h3>처리중 에러 발생.</h3>
	<c:forEach items="${empList }" var="emp">
	<p>사번: ${emp.employeeId},이름: ${emp.firstName },이메일: ${emp.email }, 직무: ${emp.jobId }</p>	
	</c:forEach>
	
	<h3>Exptession Language (EL)</h3>
	<p>${3>5 }</p>
	<p>${'test'=='test' }</p>
	<p>${5-4 }</p>
	<p>${5*4 }</p>
	<p>${id }EL 안에서 ${id }=>getAttribute()</p>
	<p>${id eq null }</p>
	<p>${empty id }</p>
	<p>${obj }</p>
	<!-- 	toString 덕에 값이 출력됨 -->
	<p>ID : ${obj.memberId }</p>
	<p>Name: ${obj.memberName }</p>
	<p>${empList}</p>


	<c:set var="myName" value="홍길동"></c:set>
	<!-- 위와 같은 의미 -->
	<%
	String name = "홍길동";
	%>
	<c:out value="${myName }"></c:out>
	<p>${myName }</p>

	<c:set var="score" value="75"></c:set>
	<c:if test="${score>70 }">
		<p>합격</p>
	</c:if>
	<c:if test="${score<70 }">
		<p>불합격</p>
	</c:if>

	<!-- if else 조건문 같은것 -->
	<c:choose>
		<c:when test="${score>90 }">
			<p>A학점</p>
		</c:when>
		<c:when test="${score >80 }">
			<p>B학점</p>
		</c:when>
		<c:when test="${score >70 }">
			<p>C학점</p>
		</c:when>
		<c:otherwise>
			<p>C학점</p>
		</c:otherwise>
	</c:choose>

	<!-- 반복문 -->
	<c:forEach begin="1" end="10" var="num">
		<p>
			class='<c:out value="${num }" />'
		</p>
	</c:forEach>
	
	<p>3단 출력하기</p>
	<c:set value="4" var="dan"></c:set>
	<table border = "1">
	<c:forEach begin="1" end="9" var="num">
		<p>
			<tr>
			<td>${dan }</td>
			<td>*</td>
			<td><c:out value="${num }"/></td>
			<td>=</td>
			<td><c:out value="${num*3 }"/></td>
			</tr>
		</p>
	</c:forEach>
	</table>
	<c:forEach begin="1" end="9" var="num">
		<p>
			3*${num }=${num*3 }
		</p>
	</c:forEach>

	<!-- 	jstl 사용을 위해서 라이브러리 추가 필요 -->
	<p>html 안에 자바코드 제거 => jstl 연습.</p>
</body>
</html>