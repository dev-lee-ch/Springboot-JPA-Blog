<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
	
	<div class="container">
		<div class="mb-2">
			<button class="btn btn-sm btn-primary" onclick="history.back();">돌아가기</button>
			<c:if test="${board.user.id eq principal.user.id }">
				<a class="btn btn-sm btn-warning" href="/board/${board.id}/updateForm">수정</a>
				<button id="btn-delete" class="btn btn-sm btn-danger">삭제</button>
			</c:if>
		</div>
		<div class="mb-3">
			글 번호: <span id="id"><i>${board.id } </i></span>
			작성자: <span id="username"><i>${board.user.username } </i></span>
		</div>
		<div class="mb-3">
			<h3>${board.title }</h3>
		</div>
		<hr/>
		<div>
			<div>${board.content }</div>
		</div>
		<hr/>
	</div>

    <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
