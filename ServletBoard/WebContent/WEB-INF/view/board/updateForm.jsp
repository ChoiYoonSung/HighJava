<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.comm.vo.AtchFileVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO boardVO = (BoardVO)request.getAttribute("boardVO");

	List<AtchFileVO> atchFileList = 
		(List<AtchFileVO>) request.getAttribute("atchFileList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 변경</title>
</head>
<body>
	<form action="update.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="boardNum" value="<%=boardVO.getBoardNum() %>">
		<input type="hidden" name="boardWriter" value="<%=boardVO.getBoardWriter() %>">
		<input type="hidden" name="atchFileId" value="<%=boardVO.getAtchFileId()%>">
		<table>
			<tr>
				<td>제목:</td>
				<td><input type="text" name="memName" value="<%=boardVO.getBoardTitle()%>"></td>
			</tr>
			<tr>
				<td>내용:</td>
				<td><textarea rows="5" cols="20" name="memAddr"><%=boardVO.getBoardContent()%></textarea> </td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
				<%if(atchFileList != null) {
					for(AtchFileVO atchFileVO : atchFileList){
				%>
					<div><a href="<%=request.getContextPath() %>/filedownload.do?fileId=<%=atchFileVO.getAtchFileId() %>&fileSn=<%=atchFileVO.getFileSn()%>">
					<%=atchFileVO.getOrignlFileNm() %></a></div>
				<%
					}
				}
				%>	
				</td>
			</tr>
			<tr>
				<td>새로운 첨부파일</td>
				<td><input type="file" name="atchFile"></td>
			</tr>
		</table>
		<input type="submit" value="게시글 수정">
		<a type="buttom" href="list.do">목록으로 돌아가기</a>
	</form>
</body>
</html>