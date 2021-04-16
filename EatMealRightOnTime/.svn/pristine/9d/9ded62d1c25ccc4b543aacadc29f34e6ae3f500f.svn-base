/**
유저가 입력한 정보 get방식으로 다 가져와서 substr을 가지고 와서 val값을 리턴하는 유틸을 만들것.
 */


//strUrl = "http://localhost:9090~~~"
//strkey = "userId"

function getValue(strUrl, strkey){
	var val = "";
	//strUrl에서 strKey에 해당하는 값을 추출해서 함수로 만들어라.
	val ="~";
	return val;	
}
function isEmpty(val){
	//이거 하면 validation check 할때 편하다. 
	//val이 빈 값이거나 null이거나 undefined이거나 " ", "     " 이런 메서드 하나.
	if(val == undefined) return true;
	if(val == null) return true;
	if(val == "null") return true;

	val = jQuery.trim(val);
	if(val.length == 0) return true;
	
	return false;	
}
function changeEmptyVal(val) {
	if(isEmpty(val)) return "";
	else return val;
}
function format(val, type){
	//어떤 값을 어떤 형식으로 바꿔 줄 것인가
	val = 01012341234;
	if(type == "hpno")
		val = val.replaceAll("-","").replaceAll(" ", "");
		val = val.replace(/(\d{3})(\d{3,4})(\d{4})/,"$1-$2-$3");
/*		val.replace(/(\d{3})(\d{3,4})(\d{4})/,"$1/$2/$3");
		val.replace(/(\d{3})(\d{3,4})(\d{4})/,"$1년$2월$3일");
*/		 //()()(),$1$2$3 앞에거를 뒤모양으로 바꿔주겠다. $1 = 1번 파라미터라는 뜻. ()안에 들어가는 것은 1번 파라미터.
		//핸폰뿐만아니라 날짜에도 많이 쓰인다. 참고해라.
	return val;
}

function formatHp(val) {
	//010-1234-1234 or 010-1234-1234, 010-12341234, 0101234-1234 등
	//010 1234  1234 or 010 123341234
	val = val.replaceAll("-","").replaceAll(" ",""); 
	
	val = val.replace(/(\d{3})(\d{3,4})(\d{4})/, "$1-$2-$3"); //문법이다. 
	console.log(">>>" + val);
	return val;
}



function chkRegExp(){
		
}