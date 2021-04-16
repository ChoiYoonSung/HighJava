$(document).ready(function(){
	$("#btnSearch").click(function(){
		
		var userId = $("#userId").val();
		var userName = $("#userName").val();

		var param = {
				memId : userId
				, memName : userName
				, flag : "L"
		};
		// ==>{memId : "test", :memName:""}
		
		$.ajax({
			url : "/JqueryPro/MemberServlet"
			,type : "post"
			,data : param
			,dataType : "json"
			,success : function(data){
				console.log(data);
				alert("성공");
				makeTable(data);
				//
			}
			,error : function(xhr){
				console.log(xhr);
				alert("오류발생");
			}
		});
		
	});
	
});

	function makeTable(data) {
		console
		console.log(data);
		var str = "";
		
// 					디자인 할꺼 가져다 놓고 하나하나씩 만들어봐라.
//					<tr>
// 						<td>iu</td>
// 						<td>아이유</td>
// 					</tr>
//					data[i].id // ==? {name ="홍길동", id="mem001"}
		for(var i=0; i < data.length; i++){
			str += "<tr>"
				+ "<td>" + (i+1) + "</td>"
				+ "<td>" + data[i].memId + "</td>"
				+ "<td>" + data[i].memName + "</td>"
				+ "<td>" + data[i].memPass + "</td>"
				+ "<td>" + data[i].memBir + "</td>"
//				+ "<td>" + data[i].memHp + "</td>" //01012341234
				+ "<td>" + formatHp(data[i].memHp) + "</td>" //010-1234-1234
				+ "<td>" + data[i].memMail + "</td>"
				+ "<td>" + data[i].memJobName + "</td>"
			
				+ "</tr>";
		}
		
		$("#tbResult tbody").html(str);
	}
	
/*	>>> MyUtil.js
 * function formatHp(val) {
		//010-1234-1234 or 010-1234-1234, 010-12341234, 0101234-1234 등
		//010 1234  1234 or 010 123341234
		val = val.replaceAll("-","").replaceAll(" ",""); 
		
		val = val.replace(/(\d{3})(\d{3,4})(\d{4}) /, "$1-$2-$3"); //문법이다. 
		
		return val;
	}*/
	
	
	
	
