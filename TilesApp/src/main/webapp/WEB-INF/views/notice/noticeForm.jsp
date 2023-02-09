<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<h3 style="text-align:center">글등록</h3>
<form action="noticeAdd.do" enctype="multipart/form-data" method="post">
	<table class="table">
	
	
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input cols="50" rows="10" type="textarea" name="subject"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer" value = "${id }"></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="attach"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input class="btn btn-primary" type="submit" value="저장">
			<input class="btn btn-warning" type="reset" value="취소">
		</tr>

	</table>
</form>