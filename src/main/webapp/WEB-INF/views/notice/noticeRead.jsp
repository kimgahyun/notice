<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<table border="1" style="width: 600px">
			<caption></caption>
			<colgroup>
				<col width='15%' />
				<col width='*%' />
			</colgroup>
			<tbody>
				<tr>
					<td>작성자</td>
					<td><c:out value="${noticeInfo.writer}" /></td>
				</tr>
				<tr>
					<td>제목</td>
					<td><c:out value="${noticeInfo.title}" /></td>
				</tr>
				<tr>
					<td>첨부 파일</td>
					<td><c:forEach var="listview" items="${listview}"
							varStatus="status">
							<a
								href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>">
								<c:out value="${listview.filename}" />
							</a>
							<br />
						</c:forEach></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><c:out value="${noticeInfo.memo}" escapeXml="false" /></td>
				</tr>
			</tbody>
		</table>
		<a href="#" onclick="history.back(-1)">돌아가기</a> <a
			href="noticeDelete?ntcNo=<c:out value="${noticeInfo.ntcNo}"/>">삭제</a>
		<a href="noticeForm?ntcNo=<c:out value="${noticeInfo.ntcNo}"/>">수정</a>
	</div>
</body>
</html>
