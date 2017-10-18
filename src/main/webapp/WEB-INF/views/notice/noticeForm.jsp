<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function fn_formSubmit() {
		/* 여기서 유효성체크 하면됨 */
		var form1 = document.form1;
		document.form1.submit();
	}
</script>
</head>
<body>
	<div align="center">
		<form action="noticeSave" name="form1" method="post"
			enctype="multipart/form-data">
			<table border="1" style="width: 600px">
				<caption>공지사항</caption>
				<colgroup>
					<col width='15%' />
					<col width='*%' />
				</colgroup>
				<tbody>
					<tr>
						<td>작성자</td>
						<td><input type="text" name="writer" size="20" maxlength="20"
							value="<c:out value="${noticeInfo.writer}"/>"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td><input type="text" name="title" size="70" maxlength="250"
							value="<c:out value="${noticeInfo.title}"/>"></td>
					</tr>
					<tr>
						<td>파일 첨부</td>
						<td><c:forEach var="listview" items="${listview}"
								varStatus="status">
								<a
									href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>">
									<c:out value="${listview.filename}" />
								</a>
								<br />
							</c:forEach> <input type="file" name="uploadfile" multiple="" /></td>

					</tr>
					<tr>
						<td>내용</td>
						<td><textarea name="memo" rows="5" cols="60"><c:out value="${noticeInfo.memo}"/></textarea></td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="ntcNo"
				value="<c:out value="${noticeInfo.ntcNo}"/>"> <a href="#"
				onclick="fn_formSubmit()">저장</a>
		</form>
	</div>
</body>
</html>
