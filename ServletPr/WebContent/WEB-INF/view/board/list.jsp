<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" crossorigin="anonymous">
	<script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
	<script src="<%=request.getContextPath()%>/assets/js/jquery-3.6.0.js"></script>
	<title>JINNY's BOARD</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary d-flex justify-content-between">
	<a class="navbar-brand" href="list.do"><i class="fas fa-chalkboard mr-2"></i>JINNY's BOARD</a>
	<form class="d-flex">
		<input class="form-control mr-2" type="search" placeholder="Search" aria-label="Search">
		<button class="btn btn-outline-light" type="submit">Search</button>
	</form>
</nav>

<div class="container">

	<div class="col-md-12">
		<h1 class="m-5"><i class="fas fa-laptop-code mr-3"></i>JINNY's BOARD</h1>
		
		<table class="table table-hover text-center">
			<thead>
				<tr class="table-info">
					<th scope="col" width="10%">#</th>
					<th scope="col">제목</th>
					<th scope="col" width="15%">작성자</th>
					<th scope="col" width="15%">작성일</th>
					<th scope="col" width="15%">조회수</th>
				</tr>
			</thead>
			<tbody>
			<%	
				int size = list.size();
					if(size > 0) {
						for(int i = 0; i < size; i++) {
			%>
							<tr>
								<td><%=list.get(i).getBoardSeq() %></td>
								<td><a href="select.do?boardSeq=<%=list.get(i).getBoardSeq()%>">
									<%=list.get(i).getBoardTitle() %>
								</a></td>
								<td><%=list.get(i).getBoardWriter() %></td>
								<td><%=list.get(i).getBoardDate() %></td>
								<td><%=list.get(i).getBoardHits() %></td>
							</tr>
			<%		
						}
					} else {
			%>
							<tr>
								<td colspan="5">등록된 게시글이 없습니다.</td>
							</tr>
			<%		
				}
			%>
				
			</tbody>
		</table>
		<hr>
		
		<a class="btn btn-primary btn-sm float-sm-right mt-5" href="insert.do">글쓰기</a>		
	</div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>