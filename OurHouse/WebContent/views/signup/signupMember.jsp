<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

Object obj = request.getAttribute("result");

String result = "";

if(obj == null){
	result = "ðĄė°ëĶŽė ė§ðĄ  íėėī ëė ęąļ íėíĐëëĪð";
}else{
	result = "íėę°ėėĪíĻ..ðĨ";
}

%>

["<%=result %>"]