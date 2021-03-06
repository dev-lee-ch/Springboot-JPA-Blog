<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- 스프링 시큐리티 태그  -->
<!-- https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html -->
<!-- https://www.baeldung.com/spring-security-taglibs -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
	<!-- <script>
		alert("로그인된 사용자 입니다.");
	</script> -->
</sec:authorize>


<!DOCTYPE html>
<html lang="en">
<head>
	<title>Dev-CH :: Spring Boot JPA</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
	  <a class="navbar-brand" href="/">Dev-CH</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  
	  <div class="collapse navbar-collapse" id="collapsibleNavbar">
	    <c:choose>
	    	<c:when test="${empty principal}">
	    		<ul class="navbar-nav mr-auto">
			      <li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
			      <li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
			    </ul>
	    	</c:when>
	    	<c:otherwise>
	    		<ul class="navbar-nav mr-auto">
			      <li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
			      <li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
			      <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
			    </ul>
	    		<div class="form-inline my-2 my-lg-0">
	    			<button class="btn btn-light" type="button">
					    ${principal.user.username }
				  	</button>
			    </div>
	    	</c:otherwise>
	    </c:choose>
	    
	  </div>  
	</nav>
	<br>