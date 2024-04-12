/**
개발할때 하단처럼 의사코드 작성하고 시작하기
1. 대상이되는 form을 selecting 한다
2. form에 대해 이벤트 핸들러 생성(submit)
3. submit 핸들러 안에서 submit 기본액션 취소 시키기(preventdefault)--동기요청 중단
4. fetch를 위한 line header body(메소드와 컨텐트타입)
5. 리절브/리젝트함수 이용해서 응답 받고.. div안에 innerHTML로 체인지 하기
 */


//1. 대상이되는 form을 selecting 한다
let forms = document.forms;
//2. form에 대해 이벤트 핸들러 생성(submit)
forms[0].addEventListener("submit", e=>{
//3. submit 핸들러 안에서 submit 기본액션 취소 시키기(preventdefault)--동기요청 중단
	e.preventDefault();
	let form = e.target;
	
	//request line
	let url = form.action;
	let method = form.method;
	
	//request header
	let headers = {
		"content-type" : form.enctype,
		"accpet" : "text/html"
	}
	
	let formData = new FormData(form);
	console.log(new URLSearchParams(formData).toString())
	let body = new URLSearchParams(formData).toString()
	
	let options = {
		method : method,
		headers : headers
	}
	
	if(method=="get"){
		url = `${url}?${body}`
	}else{
		options.body = body
	}
	
	fetch(url, options)
		.then((resp)=>{
			if(resp.ok){
				return resp.text();
			}else{
				throw new Error(`에러발생, 상태코드 : ${resp.status}`);
			}
		}).then(html=>{
			//document.body.innerHTML = document.body.innerHTML + html;
			mbtiarea.innerHTML=html; //(안좋은 코드임)
			})
		.catch(err=>console.log(err));
});
	
