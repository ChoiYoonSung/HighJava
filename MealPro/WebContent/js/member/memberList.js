/**
 * 회원 정보를 검색하는 JS
 * jquery7 - ex02_jsonsubmit.html 참고
 */

$(document).ready(function(){
	/* 목록 시작 시 전체 회원 수를 불러옴 */
	  $("#btnSearch").click(function(){
		// 서버로 전달할 데이터 만들기
	    $.ajax({
	    	url : "/MealPro/member/memberListJson.do"
	    	, type : "post"
	    	, dataType : "json"
	    	,success : function(data){
	    		alert("로드 성공");
	    		makeTable(data);
	    		
	    	}
	    	,error : function(xhr){
	    		console.log(xhr);
	    		alert("로드 오류");
	    	}
	    });
	  });
	  
	  function makeTable(data){
		  console.log("data.length : " + data.length);
		  var str = "";
		  if(data.length > 0){
			  for(var i = 0 ; i < data.length ; i++){
				  str += "<tr>"
					  + "<td>" + (i + 1) + "</td>"
					  + "<td>" + data[i].memId + "</td>"
					  + "<td>" + data[i].memPw + "</td>"
					  + "<td>" + data[i].memNm + "</td>"
					  + "<td>" + data[i].memRegNo + "</td>"
					  + "<td>" + data[i].memNumber + "</td>" // 01012341234
					  + "<td>" + data[i].memZip + "</td>"
					  + "<td>" + data[i].memAdd + "</td>"
					  + "</tr>";
			  }
			  console.log("str : " + str);
		  } else {
			  str = "검색결과가 없습니다.";
		  }
		  
		  $("#tbResult tbody").html(str);
	  }
	  
});