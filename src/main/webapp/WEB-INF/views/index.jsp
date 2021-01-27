<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="layout/header.jsp" %>
	
	<div class="container">
		<c:forEach var="board" items="${boards.content }">
			<div class="card mb-2">
				<div class="card-body">
			    	<div class="mb-2">
			    	글 번호: <span id="id"><i>${board.id } </i></span>
					/ 작성자: <span id="username"><i>${board.user.username } </i></span>
					</div>
			    	<h4 class="card-title">${board.title }</h4>
			    	<a href="/board/${board.id }" class="btn btn-primary">상세보기</a>
			  	</div>
			</div>			
		</c:forEach>
		
		<ul class="pagination justify-content-center">			
		  	<li class="page-item ${boards.first ? 'disabled' : '' }"><a class="page-link" href="/?page=${boards.number - 1 }">Previous</a></li>
		  	<li class="page-item ${boards.last ? 'disabled' : '' }"><a class="page-link" href="/?page=${boards.number + 1 }">Next</a></li>
		</ul>
	</div>
	
	
<%@ include file="layout/footer.jsp" %>
	