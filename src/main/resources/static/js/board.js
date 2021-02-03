let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function() {} X -() -> {} 이유는 this를 바인딩하기위해서!!
			this.save();
		});
		
		$("#btn-delete").on("click", () => {
			this.deleteById();
		});
		
		$("#btn-update").on("click", () => {
			this.update();
		});
	},
	
	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		//console.log("data : ", data);
		
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),	
			contentType: "application/json;charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			// 정상
			alert("글쓰기가 완료되었습니다.");
			location.href="/"
		}).fail(function(error) {
			// 실패
			alert(JSON.stringify(error));
		});
	},
    
    deleteById: function() {
    	let id = $('#id').text(); 

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json"
		}).done(function(resp) {
			// 정상
			alert("글 삭제가 완료되었습니다.");
			location.href="/"
		}).fail(function(error) {
			// 실패
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),	
			contentType: "application/json;charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			// 정상
			alert("글수정이 완료되었습니다.");
			location.href="/"
		}).fail(function(error) {
			// 실패
			alert(JSON.stringify(error));
		});
	},
}

index.init();