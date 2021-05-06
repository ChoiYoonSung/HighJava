<%@page import="kr.or.ddit.common.vo.AtchFileVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO bv = (BoardVO) request.getAttribute("boardVO");
	List<AtchFileVO> atchFileList = (List<AtchFileVO>) request.getAttribute("atchFileList");

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
		
		<form action="update.do" method="post" enctype="multipart/form-data">
			<table class="table">
				<tbody>
					<tr>
						<th width="20%" class="align-middle">제목</th>
						<td>
							<input type="text" class="form-control align-middle" name="boardTitle" placeholder="제목을 입력하세요" value="<%=bv.getBoardTitle() %>" required="required">
						</td>
					</tr>
					<tr>
						<th class="align-middle">작성자</th>
						<td><%=bv.getBoardWriter() %></td>
					</tr>
					<tr>
						<th>기존첨부파일</th>
						<td>
					<%
						if(atchFileList != null) {
							for(AtchFileVO atchFileVO : atchFileList){
					%>
						<div>
							<a href="<%=request.getContextPath() %>/filedownload.do?fileId=<%=atchFileVO.getAtchFileId() %>&fileSn=<%=atchFileVO.getFileSn()%>">
							<%=atchFileVO.getOrignlFileNm() %></a>
						</div>
					<%
							}
						}
					%>
						</td>
					</tr>
					<tr>
						<td>새로운 첨부파일</td>
						<td><input type="file" name="atchFileId"></td>
					</tr>
					<tr>
						<th colspan="2">내용</th>
					</tr>
				</tbody>
			</table>
			<textarea class="form-control" name="boardContent" rows="10" placeholder="내용을 입력하세요." required="required"><%=bv.getBoardContent() %></textarea>
			<input type="hidden" name="boardSeq" value="<%=bv.getBoardSeq()%>" />
			<a class="btn btn-secondary mt-4 ml-4 float-right" href="list.do">목록으로</a> 		
			<button class="btn btn-outline-primary mt-4 float-right" type="submit">수정</button>
		</form>
	</div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>