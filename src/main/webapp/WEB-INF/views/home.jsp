<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/png" href="/img/favicon.png" />
<link rel="stylesheet"  href="/css/common.css" />
<style>
   
</style>
</head>
<body>
  <main>
	<h2>Home</h2>
	<div><a href="/Menus/WriteForm">새 메뉴 추가</a></div>
	<div><a href="/Menus/WriteForm2">새 메뉴 추가2</a></div>
	<div><a href="/Menus/List">메뉴 목록</a></div>	
	<div>&nbsp;</div>
	<div><a href="/Users/List">사용자 목록</a></div>
	<div><a href="/Users/WriteForm">사용자 추가</a></div>
	<div>&nbsp;</div>	
	<div><a href="/BoardPaging/List?menu_id=MENU01">게시물 목록(페이징)</a></div>
	<div><a href="/BoardPaging/WriteForm?menu_id=MENU01">게시물 등록(페이징)</a></div>
	
	<c:if test = "${sessionScope.login == null || login ==''}"> 
		<div><a href="/LoginForm">로그인 창</a></div>
	</c:if>
	<div>
	 <h2>${login.username } 님 환영합니다</h2> <br>
	 <div>당신의 가입일은${login.indate} 입니다. </div>
	 <div><a href="/Logout">로그아웃</a></div>
	</div>
  </main>	
</body>
</html>






