<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>noticeForm</title>
</head>
<body>
	<div align="center">
		<table border="1" style="width: 600px">
			<caption>공지사항</caption>
			<colgroup>
				<col width='8%' />
				<col width='*%' />
				<col width='15%' />
				<col width='15%' />
				<col width='10%' />
				<col width='10%' />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="listview" items="${listview}" varStatus="status">
					<c:url var="link" value="noticeRead">
						<c:param name="ntcNo" value="${listview.ntcNo}" />
					</c:url>

					<tr>
						<td><c:out value="${listview.ntcNo }" /></td>
						<td
							style="max-width: 100px; border: 1px solid black; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
							<a href="${link}"><c:out value="${listview.title}" /></a>
						</td>
						<td><c:out value="${listview.writer}" /></td>
						<td><c:out value="${listview.ntcDate}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="noticeForm">글쓰기</a>
	</div>
</body>
</html>
