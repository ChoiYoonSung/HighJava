<%@page import="ourhouse.common.vo.MemberVO"%>
<%@page import="ourhouse.common.vo.NoticeVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	NoticeVO noticeVO = (NoticeVO) request.getAttribute("noticeVO");
	MemberVO mv = (MemberVO) session.getAttribute("session");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>우리의집 | 공지사항</title>

    <meta name="author" content="3team">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, maximum-scale=1, minimum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">


    <meta property="og:type" content="website">
    <meta property="og:site_name" content="wehouse">
    <meta property="og:title" content="Introduce your home, together">
    <meta property="og:description" content="Deadock MiddleProject">
    <meta property="og:image" content="img/logo__github.png">
    <meta property="og:url" content="https://wehouse.com">

    <!-- 대표 로고 -->
    <link rel="icon" href="../../image/PNG/favicon.png">
    <link rel="apple-touch-icon" href="../../image/PNG/favicon.png">
    
    <link rel="stylesheet" href="../../css/reset.css">
    <link rel="stylesheet" href="../../css/noticepost.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />

    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    $(function(){
        loginTrue();//로그인 되었을때
     });
     function loginTrue(){
        <%if(mv == null){%> //로그인 상태가 아닐때
          $("#loginTag").show();//로그인태그나타남
           $("#logOutTag").hide();//로그아웃상태숨김
           $("#signUpTag").show();//회원가입나타남
           $("#myAction").show();//나의활동기록나타남
           $("#myPage").hide();//마이페이지숨김
        <%}else{%>
           $("#loginTag").hide();//로그인태그 숨김
           $("#logOutTag").show();//로그아웃상태나타남
           $("#signUpTag").hide();//회원가입숨김
           $("#myAction").hide();//나의활동기록숨김
           $("#myPage").show();//마이페이지나타남
           
        <%}%>
     }
    </script>
</head>
<body>
    <!-- body 태그에 직접적으로 css는 부여하지 않는다. 컨테이너를 따로 지정하자. -->
    <!-- 언더바 두개를 사용하는 이유 -->
<div class="body__container"> <!-- body의 일부분인 컨테이너임을 의미 -->

    <!-- 기존 HEADER -->
    <!-- <header class="section">
        콘텐츠가 들어가는 inner영역
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="#">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="POST" action="">고유한 개체에 id 명시
                    <span class="search-icon"></span>
                    <input type="text" id="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit">화면엔 있지만 숨길것임.
                </form>

                <ul class="sub-menu">
                    <li><a href="./signin.html">로그인</a></li>
                    <li><a href="./signup.html">회원가입</a></li>
                </ul>

                <div class="btn-group">
                    <a href="" class="btn">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </a>
                </div>
            </div>
        </div>
    </header> -->
    
    <!-- HEADER -->
    <header class="section section--header">
        <!-- 콘텐츠가 들어가는 inner영역 -->
        <div class="inner clearfix">

            <div class="menu-group float--left">
                
                <div class="logo">
                    <a href="/main.do">우리의집</a>
                </div>

            </div>

            <div class="sign-group float--right">

                <form id="search-form" method="GET" action="/searchTotal.do"><!-- 고유한 개체에 id 명시 -->
                    <span class="search-icon"></span>
                    <input type="text" id="search" name="search" class="input--text" placeholder="우리의집 통합검색">
                    <input type="submit" value="Submit"><!-- 화면엔 있지만 숨길것임. -->
                </form>

                <ul class="sub-menu">
                    <li><a href="/member/signin.do" id="loginTag">로그인</a></li>
                    <li><a href="/views/signin/logOut.jsp" id="logOutTag">로그아웃</a></li>
                    <li><a href="/member/signup.do" id="signUpTag">회원가입</a></li>
                </ul>

                <div class="btn-group dropdown--writelist">
                    <button type="button" class="btn btn-write">글쓰기
                        <i style="margin-left: 10px;"class="fas fa-angle-down"></i>
                    </button>
                    
                    <ul class="dropdown-write">
                        <li class="write-item" onclick="writePhoto()">
                            <img src="../../image/PNG/picture.png" alt=""> 

                            <a class="write-info">
                                <span class="write--title">사진 올리기</span>
                                <span class="write--description">우리집 사진을 공유해보세요.</span>
                            </a>
                        </li>
                        <li class="write-item" onclick="writeQna()">
                            <img src="../../image/PNG/question.png" alt=""> 

                            <a class="write-info">
                                 <span class="write--title">인테리어 질문하기</span>
                                 <span class="write--description">인테리어 고수들에게 질문해보세요.</span>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
    </header>

    <!-- 서브 메뉴 집들이(사진), 질문과 답변, 이벤트 -->
    <header class="section sub-section">
            <div class="inner clearfix">

                <div class="section-memu float--left">
                    <ul class="section-group active">
                        <li><a href="/main.do">사진</a></li>
                        <li><a href="/qna/list.do">질문과답변</a></li>
                        <li><a href="/coupon/list.do">쿠폰</a></li>
                        <li><a href="/notice/noticeHome.do">공지사항</a></li>
                    </ul>
                </div>

                <div class="sign-group float--right">
                    <a href="" class="textbold" id="myAction">마이페이지
                    <a href="/member/mypage.do" class="textbold" id="myPage">마이페이지
                    </a>
                </div>
                
            </div>
    </header>

    <!-- 최근 올라온 공지사항.
         가장 최근에 게시된 공지게시글 1개 -->
    <section class="section section--notice">
        <div class="inner">

            <div class="notice-container">

                <span>💡</span>
                <span class="notice-title">[공지사항] 질문 게시글 올바른 사용 안내 - 게시판별 부적합한 게시글 및 댓글 처벌에 대한 안내</span>

                 
            </div>

        </div>
    </section>

    <section class="section section--noticeboard">

        <div class="inner">

            <div class="board--title">
                <div class="title--text">
                	📢 공지사항
                </div>
                <div class="list--prev">
                    <a href="/notice/noticeHome.do" class="btn btn--prev">
                        <i class="fas fa-bars"></i>

                        <div class="vertical--bar"></div>

                        목록으로 돌아가기</a>
                </div>
            </div>
			
            <div class="boardcontent--wrap">
                <ul class="tr">
                    <li class="th"><%=noticeVO.getNoticeNo() %></li>
                    <li class="th"><%=noticeVO.getNoticeTitle() %></li>
                    <li class="th">작성일 : <%=noticeVO.getNoticeDate().substring(0, 10) %></li>
                    <li class="th">수정일 : <%=noticeVO.getUpdateDate().substring(0, 10) %></li>
                </ul>

                <div class="board--content">
 					<%=noticeVO.getNoticeContent() %>
                </div>
            </div>

<!--             <ul class="prevorNext"> -->
<!--                 <li class="prev--container"> -->
<!--                     <a> <i class="fas fa-angle-up"></i>  -->
<!--                         <span class="note">이전글</span>  -->
<!--                         <span class="title">11월 불건전한 채팅 이용자 제재 안내</span> -->
<!--                     </a> -->
<!--                 </li> -->
<!--                 <li class="next--container"> -->
<!--                     <a> <i class="fas fa-angle-down"></i>  -->
<!--                         <span class="note">다음글</span>  -->
<!--                         <span class="title" href="#">EACC AUTUNN 2020 생중계 쿠폰 안내</span> -->
<!--                     </a> -->
<!--                 </li> -->
<!--             </ul> -->

        </div>

    </section>

    <!-- FOOTER -->
    <footer class="section">
            <div class="inner clearfix">

                <ul class="site-links float--right">
                    <li>@Copyright © 2020 ~ 2020 by Daedockplace, Inc All rights reserved</li>
                </ul>

                <ul class="site-links float--left">
                    <li><a href="#">브랜드 스토리</a></li>
                    <li><a href="#">이용약관</a></li>
                    <li><a href="#">개인정보처리방침</a></li>
                    <li><a href="#">고객센터</a></li>
                    <li><a href="#">공지사항</a></li>
                </ul>
            </div>
    </footer>

    <script src="../../js/qnahome.js"></script>
    <script src="../js/writebtn.js"></script>
    <script src="../js/headerfixed.js"></script>
</body>
</html>












