<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="kr.or.ddit.comm.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");

	PagingVO pagingVO = (PagingVO)request.getAttribute("pagingVO");
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>

</head>
<body>

	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
		</tr>
		
	<%
		int boardSize = boardList.size();
		
		if(boardSize > 0){
			for(int i = 0; i < boardSize; i++){
	%>		
		<tr>
			<td><a href="select.do?boardNum=<%=boardList.get(i).getBoardNum() %>"><%= boardList.get(i).getBoardNum() %></a></td>
			<td><%=boardList.get(i).getBoardTitle() %></td>
			<td><%=boardList.get(i).getBoardWriter() %></td>
			<td><%=boardList.get(i).getBoardDate() %></td>
		</tr>
	<% 	} %>
		
		<!-- 페이징 처리 시작 -->
		<%if(pagingVO.getTotalCount() > 0) {%>
			<tr>
				<td colspan="4" align="center">
					<%if(pagingVO.getFirstPageNo() > pagingVO.getPageSize()) { %>
					<a href="list.do?pageNo=<%=pagingVO.getFirstPageNo() - pagingVO.getPageSize() %>">[이전]</a>
					<%} %>
					<%for(int pNo = pagingVO.getFirstPageNo(); pNo <= pagingVO.getLastPageNo(); pNo++) { %>
						<a <%if(pNo == pagingVO.getCurrentPageNo()){ %> style="color:orange;"<%} %> href="list.do?pageNo=<%=pNo %>">[<%=pNo %>]</a>
					<%} %>
					<%if(pagingVO.getLastPageNo() < pagingVO.getTotalPageCount()) {%>
					<a href="list.do?pageNo=<%=pagingVO.getFirstPageNo() + pagingVO.getPageSize() %>">[다음]</a>
					<%} %>
				</td>
			</tr>
		<%} %>
		<!-- 페이징 처리 끝 -->	
			
			
	<% 	}else{ %>
		<tr>
			<td colspan="5">정보가 없습니다.</td>
		</tr>
	<%
		}
	%>
	<tr align="center">
		<td colspan="5"><a href="insert.do">[게시글 등록]</a></td>
	</tr>
	</table>
</body>
</html>