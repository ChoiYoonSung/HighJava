<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO bv = (BoardVO) request.getAttribute("boardVO");
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
		
		<form action="insert.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th width="20%" class="align-middle">제 목</th>
						<td colspan="5"><%=bv.getBoardTitle() %></td>
					</tr>
					<tr>
						<th width="15%" class="align-middle">작성자</th>
						<td><%=bv.getBoardWriter() %></td>
						<th width="15%" class="align-middle">작성일</th>
						<td><%=bv.getBoardDate() %></td>
						<th width="15%" class="align-middle">조회수</th>
						<td><%=bv.getBoardHits() %></td>
						
					</tr>
					<tr>
						<th colspan="6">내 용</th>
					</tr>
					<tr>
						<td colspan="6">
						<img id="atchFile" class="mb-5" width="300px" src="<%=request.getContextPath() %>/filedownload.do?fileId=<%=bv.getAtchFileId() %>">
						<%
							if(bv.getAtchFileId() == -1) {
						%>
							<script type="text/javascript">
								$("#atchFile").hide();
							</script>
						<%		
							}
						%>
						<br><%=bv.getBoardContent() %>
						</td>
					</tr>
				</tbody>
			</table>
			<hr>
			<a class="btn btn-secondary mt-4 ml-4 float-right" href="list.do">목록으로</a> 		
			<a class="btn btn-outline-danger mt-4 ml-4 float-right" onclick="fn_delete('<%=bv.getBoardSeq() %>')">삭제</a>
			<a class="btn btn-outline-primary mt-4 float-right" onclick="fn_update('<%=bv.getBoardSeq() %>')">수정</a>
		</form>
	</div>
</div>

<form id="fm" method="post">
	<input type="hidden" name="boardSeq" value="<%=bv.getBoardSeq() %>" />
</form>

<script type="text/javascript">
	
	function fn_delete(seq) {
		if(confirm('게시글을 삭제하시겠습니까?')) {
			$('#fm').attr('action', '<%=request.getContextPath()%>/delete.do');
			$('#fm').submit();
		}
	}
	
	function fn_update(seq) {
		$('#fm').attr('method', 'get');
		$('#fm').attr('action', '<%=request.getContextPath()%>/update.do');
		$('#fm').submit();
	}

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>