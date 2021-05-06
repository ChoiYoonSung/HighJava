<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.ATHROW"%>
<%@page import="kr.or.ddit.comm.vo.AtchFileVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	BoardVO boardVO = (BoardVO) request.getAttribute("boardVO");
	
	List<AtchFileVO> atchFileList = 
			(List<AtchFileVO>) request.getAttribute("atchFileList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>번호</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>제목</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>작성자</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>작성일</td>
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
			<td colspan="2">
				<a href="list.do">[목록]</a>
				<a href="update.do?boardNum=<%=boardVO.getBoardNum()%>">[회원정보 수정]</a>
				<a href="delete.do?boardNum=<%=boardVO.getBoardNum()%>">[회원정보 삭제]</a>
			</td>
		</tr>
	</table>
</body>
</html>