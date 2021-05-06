<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- script -->
<script type="text/javascript" src="/MealPro/js/jquery-3.6.0.js"></script> 
<script type="text/javascript" src="/MealPro/js/member/memberList.js"></script>

<title>멤버 페이지 세팅</title>
</head>
<body>
멤버 페이지 출력
<button type="button" id="btnSearch" name="btnSearch">멤버 출력</button>
<table class="gridtbl tbl" id="tbResult">
	<caption>회원 목록</caption>
	<colgroup>
		<col style="width:60px">
		<col style="width:80px">
		<col style="width:12%">
		<col style="width:12%">
		<col style="width:110px">
		<col style="width:160px">
		<col style="width:auto">
		<col style="width:70px">
		<col>
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>ID</th>
			<th>비밀번호</th>
			<th>회원명</th>
			<th>생년월일</th>
			<th>전화번호</th>
			<th>우편번호</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="8">검색 버튼을 클릭하세요.</td>
		</tr>
	</tbody>
</table>
</body>
</html>