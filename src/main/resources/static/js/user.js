let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function() {} X -() -> {} 이유는 this를 바인딩하기위해서!!
			this.save();
		});
		
//		$("#btn-login").on("click", () => { // function() {} X -() -> {} 이유는 this를 바인딩하기위해서!!
//			this.login();
//		});
	},
	
	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		//console.log("data : ", data);
		
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json;charset=utf-8",	//body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" //응답데이터가 어떤 타입인지
		}).done(function(resp) {
			// 정상
			alert("회원가입이 완료되었습니다.");
			console.log('resp : ', resp);
			location.href="/"
		}).fail(function(error) {
			// 실패
			alert(JSON.stringify(error));
		});
	},
	/*
	login: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
		};
		//console.log("data : ", data);
		
		// ajax 호출 시 default가 비동기 호출
		$.ajax({
			// 회원가입 수행 요청
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data),	// http body 데이터
			contentType: "application/json;charset=utf-8",	//body 데이터가 어떤 타입인지 (MIME)
			dataType: "json" //응답데이터가 어떤 타입인지
		}).done(function(resp) {
			// 정상
			alert("로그인이 완료되었습니다.");
			console.log('resp : ', resp);
			location.href="/"
		}).fail(function(error) {
			// 실패
			alert(JSON.stringify(error));
		});
	}
	*/
    
}

index.init();