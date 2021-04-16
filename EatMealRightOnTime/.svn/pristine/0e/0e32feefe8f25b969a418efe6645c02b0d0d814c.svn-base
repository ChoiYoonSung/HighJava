$(document).ready(function() {
	//화면 초기화 작업들 진행

	//1. '직업코드' 세팅
	initJobSelect();
	//2. '기념일 코드' 세팅
	initMemorialSelect();
	//3. '광고메일 수신 여부' 기본값 세팅 -미수신
	$("#recvEmailYnN").prop("checked", true);
	//4. '취미코드' 세팅
	initHobbyCheck();
	//5. '우편번호찾기 화면-시' 세팅
	initCitySelect();
	
/*	$("#tbZipResult tbody").click(function() {
		//테이블이 없기때문에 바인딩이 되지 않는다. 
	})//동적요소 포함 on, 미포함 dblclick
*/	
//	$("#tbZipResult tbody tr").on("dblclick", function() {
//		
//	});
	//자식요소 필터링 거는 방법. 가운데에 하나의 필터 추가 "tbody tr"
	//자꾸 같은 요소를 줘서 바인딩을 하면 여러번 일한다. 한번만 딱 동작하게 바꿔주면 된다. 
	$("#tbZipResult").on("dblclick", "tbody tr", function() {
//		this ==> tr
//		console.log($(this));
//		console.log($(this).children);
		var zipcode = $(this).children("td:eq(0)").text();
		var addr = $(this).children("td:eq(1)").text();
		
		//메인화면의 우편번호, 주소 input에 데이터 세팅
		$("#memZip").val(zipcode);
		$("#memAdd1").val(addr);
		
		
	});
	
	
});


function openZip() {
	//주소창(모달창) 닫기
//	$("#zipModal").modal("hide");
	//시 셀렉박스 조회하고 초기화
	initCitySelect();
	//테이블 초기화 하고 뭔가 더 들어간다.
	$("#tbZipResult tbody").empty();
	//주소창(모달창) 열기
	$("#myModal").modal();
}

function save() {
	//validation check
	//필수값.
//	var result = validate();
/*	if(result){
		
		
	}else{
		return;
	}
	*/
	//저장할꺼다. 진짜 저장할꺼냐고 함 물어봐라.
	if(confirm("저장하시겠습니까?")){
		//db에 저장하는 ajax호출
		$("#formFlag").val("C");
		$.ajax({
			url : "/JqueryPro/MemberServlet"
			,type : "post"
			,data : $("#fm").serialize() //인풋요소만 전송이 됐다. name을 써준것만. name은 vo랑 동일하게 써라.
			,dataType : "json"
			,success : function() {
				alert("저장되었습니다.");
				
				//페이지 이동
//				changePage();
				
			}
			,error : function(xhr) {
				console.log(xhr);
				alert("저장중 오류발생")
			}
			
			
			
		});
	} else{
		return;
	}
	
}

function changePage() {
	//방법 1.
	//window.location.href ="/Jquerypro/html/member/memberList2.html";
	
	//방법 2.
	var fm = document.getElementById("fm");
	fm.action = "/Jquerypro/html/member/memberList2.html"//서블릿을 호출하기도 함
	fm.method = "post";
	fm.submit();
	
}

function validate() {

	//...
	return false;
	
	//체크 끝나면
	return true;
}



function initJobSelect() {
	//직업코드 조회해서 세팅하기
	$.ajax({
		url : "/JqueryPro/CodeServlet",
		type : "post",
		data : {
			"groupCode" : 'A02'
		} //sql에서 쳐봐라. 화면에서 던질 데이터는 준비 되었다. 
		,
		dataType : "json",
		success : function(data) {
//			console.log(data);
			//data  []{"value" : "08", "name" : "전업주부"},{}] 이런모양으로 온다.

			makeJobSelect(data);

		},
		error : function(xhr) {
			console.log(xhr); //까보고 싶으면 xhr만 찍어라.
			alert("오류 ");
		}
	});
}

function makeJobSelect(data) {
	//방법 1)
	var str = "";
	//		<option value="01">프로그래머</option>
	//		<option value="02">퍼블리셔</option>
	str += '<option value="">' + '선택해 주세요' + '</option>'
	for (var i = 0; i < data.length; i++) {
		str += '<option value="' + data[i].value + '">' + data[i].name
				+ '</option>'
	}
	$("#memJob").html(str); //<select>

	//방법 2)
	//		$("#memJob").empty();
	//		$("#memJob").append(ele1);
	//		$("#memJob").append(ele2);
}

//[중복검사]버튼에 클릭 이벤트
function chkId() {
	var memId = $("#memId").val();
	//빈 값 확인
	if (isEmpty(memId)) { //myUtil
		alert("ID값이 입력되지 않았습니다.");
		$("#memId").focus();
	}

	//유효성 검사
	var regExp = /^[a-z0-9]{3,10}$/;
	if (!regExp.test(memId)) {
		alert("ID값이 유효하지 않습니다.");
		$("#memId").focus();
		$("#spMemId").show();
	}

	// DB에서 중복검사 (화면은 변환없이 백단에서 왔다 간다. 이럴때 Ajax)
	$.ajax({
		url : "/JqueryPro/MemberServlet",
		type : "post",
		data : {
			"memId" : memId,
			"flag" : "CHKID"
		},
		dataType : "json",
		success : function(data) {
//			console.log("data" + data);

			if (data.resultCnt == 1) {
				alert("중복된 아이디가 있습니다.");
				return;
			} else {
				alert("중복된 아이디가 없습니다.");
				$("#spMemId").hide();

			}

			alert("성공")
		},
		error : function(xhr) {
			console.log(xhr);
			alert("ID중복 검사 중 오류발생")
		}
	});

}


function initMemorialSelect() {
	$.ajax({
		url : "/JqueryPro/CodeServlet",
		type : "post",
		data : {
			"groupCode" : 'A03'
		} //sql에서 쳐봐라. 화면에서 던질 데이터는 준비 되었다. 
		,
		dataType : "json",
		success : function(data) {
//			console.log(data);

			makeMemorialSelect(data);

		},
		error : function(xhr) {
			console.log(xhr); //까보고 싶으면 xhr만 찍어라.
			alert("오류 ");
		}
	});
}
function makeMemorialSelect(data) {
	var str = "";
	//		<option value="01">프로그래머</option>
	str += '<option value="">' + '선택해 주세요' + '</option>'
	for (var i = 0; i < data.length; i++) {
		str += '<option value="' + data[i].value + '">' + data[i].name
				+ '</option>'
	}
	$("#memMemorialType").html(str); //<select>

}

function initHobbyCheck() {
	$.ajax({
		url : "/JqueryPro/CodeServlet",
		type : "post",
		data : {
			"groupCode" : 'A01'
		} //sql에서 쳐봐라. 화면에서 던질 데이터는 준비 되었다. 
		,
		dataType : "json",
		success : function(data) {
//			console.log(data);

			makeHobbySelect(data);

		},
		error : function(xhr) {
			console.log(xhr); //까보고 싶으면 xhr만 찍어라.
			alert("오류 ");
		}
	});
}
function makeHobbySelect(data) {
	var str = "";
	//		<option value="01">프로그래머</option>
	//      <label class='checkbox-inline'><input type="checkbox" value""> 옵션1 
	  
	for (var i = 0; i < data.length; i++) {
		str += '<label class="checkbox-inline"><input type="checkbox" value="' + data[i].value + '">' + data[i].name
				+ '</label>'
	}
	$("#memLike").html(str); //<select>
	
}

function initCitySelect() {
	$.ajax({
		url : "/JqueryPro/ZipServlet",
		type : "post"
//		,data : {"groupCode" : 'A01'} //sql에서 쳐봐라. 화면에서 던질 데이터는 준비 되었다. 
		,dataType : "json"
		,success : function(data) {
//			console.log(data);

			makeCitySelect(data);

		}
		,error : function(xhr) {
			console.log(xhr); //까보고 싶으면 xhr만 찍어라.
			alert("오류 ");
		}
	});
}

function makeCitySelect(data) {
	var str = "";
	
		str += '<option value="">' + '선택해 주세요' + '</option>'
	for (var i = 0; i < data.length; i++) {
		str += '<option value="' + data[i].sido+ '">' + data[i].sido + '</option>'
	}
	$("#city").html(str);

	setGu();
	//방법3 trigger로 change 이벤트 호출
	
}

function setGu() {
	var param={
			'sido' : $("#city").val()
			,'flag' : 'GU'
		};
	
	$.ajax({
		url : "/JqueryPro/ZipServlet",
		type : "post"
		,data : param //구정보를 조회할때는 파라미터가 필요하다. success에서 받아 쓰는data타입이다. 아래dataType 혼동하지 말자. 
		,dataType : "json"
		,success : function(data) {
			console.log("여기 아래가 gugun data");
			console.log(data);

			makeGugunSelect(data);

		}
		,error : function(xhr) {
			console.log(xhr); //까보고 싶으면 xhr만 찍어라.
			alert("오류 ");
		}
	});
}

function makeGugunSelect(data) {
	var str = "";
	//		<option value="01">프로그래머</option>
	str += '<option value="">' + '선택해 주세요' + '</option>'
	for (var i = 0; i < data.length; i++) {
		str += '<option value="' + data[i].gugun+ '">' + data[i].gugun + '</option>'
	}
	$("#gu").html(str); //<select>
	$("#gu").prop("disabled", false);
	setDong();
}

function setDong() {
	var param={
			'sido' : $("#city").val()
			,'gugun' : $("#gu").val()
			,'flag' : 'Dong'
		};
	
	$.ajax({
		url : "/JqueryPro/ZipServlet",
		type : "post"
		,data : param //구정보를 조회할때는 파라미터가 필요하다. success에서 받아 쓰는data타입이다. 아래dataType 혼동하지 말자. 
		,dataType : "json"
		,success : function(data) {
			
			console.log("이거 동");
			console.log(data);

			makeDongSelect(data);

		}
		,error : function(xhr) {
			console.log(xhr); //까보고 싶으면 xhr만 찍어라.
			alert("오류 ");
		}
	});
}

function makeDongSelect(data) {
	var str = "";
	//		<option value="01">프로그래머</option>
	str += '<option value="">' + '선택해 주세요' + '</option>'
	for (var i = 0; i < data.length; i++) {
		str += '<option value="' + data[i].dong+ '">' + data[i].dong+ '</option>'
	}
	$("#dong").html(str); //<select>
	$("#dong").prop("disabled", false);
}

/*function setZip() {
	var param={
			'sido' : $("#city").val()
			,'gugun' : $("#gu").val()
			,'dong' : $("#dong").val()
			,'flag' : 'Zip'
	};
	
	$.ajax({
		url : "/JqueryPro/ZipServlet",
		type : "post"
			,data : param //구정보를 조회할때는 파라미터가 필요하다. success에서 받아 쓰는data타입이다. 아래dataType 혼동하지 말자. 
			,dataType : "json"
				,success : function(data) {
					
					console.log("이거 집");
					console.log(data);
					
					makeZipSelect(data);
					
				}
	,error : function(xhr) {
		console.log(xhr); //까보고 싶으면 xhr만 찍어라.
		alert("오류 ");
	}
	});
}

function makeZipSelect(data) {
	var str = "";
	//		<option value="01">프로그래머</option>
	str += '<tr>' + '<th value="">' + '우편번호' + '</th>'+ '<th>' + '주소'+ '</th>' + '</tr>' + '<tr>';
	for (var i = 0; i < data.length; i++) {
		str += '<td value="' + data[i].dong+ '">' + data[i].dong+ '</td>'
		str += '<td value="' + data[i].zipcode+ '">' + data[i].zipcode+ '</td>'
		str += '</tr>'
	}
	console.log(str);
	$("#zip").html(str); //<select>
}*/
function searchZipCode(){
	var sido = $("#city").val();
	var gu = $("#gu").val();
	var dong= $("#dong").val();
	
	if(isEmpty(sido) || isEmpty(gu) || isEmpty(dong)) {
		alert("시, 구, 동을 선택하고 검색 버튼을 누르세요.");
		return;
	}
	
	var param = {
			'sido' : sido
			,'gugun' : gu
			,'dong' : dong
			,flag : 'Zip'
	};
	
	$.ajax({
		url : "/JqueryPro/ZipServlet"
		,type : "post"
		,data : param
		,dataType : "json"
		,success : function(data){
			console.log("이거 시구동");
			console.log(data);
			makeZipTable(data);
		}
		,error : function(xhr){
			console.log(xhr);
			alert("오류");
		}
	});
	
}
function makeZipTable(data){
	$("#divZipResult").show();
	$("#tbZipResult tbody").empty();
	
	var strHtml = "";
	strHtml += "<tr>" + "<th>" + "우편번호 " + "</th>" +" " + "<th>" + "주소 " + "</th>" + "</tr>" 
	for(var i=0 ; i<data.length ; i++) {
		console.log(data[i]);
		// <tr onclick = 'fntest("300-801", "대전", "중구", "문화1동", "1번지");'>
		
//		strHtml += "<tr onclick='fntest(\"" + data[i].zipcode + "\",  \"" + data[i].sido + "\");'>"
		strHtml += "<tr>"
				+ "<td>" + data[i].zipcode + "</td>"
				+ "<td>" + data[i].sido + " "
				+ data[i].gugun + " "
				+ data[i].dong + " " 
				+ changeEmptyVal(data[i].bunji) + "</td>"
				+ "</tr>";
	}
	
	$("#tbZipResult tbody").html(strHtml);
}

function fntest(str1, str2) {
	console.log(str1);
	console.log(str2);
}

function fntest1(obj) {
	console.log(obj);
}