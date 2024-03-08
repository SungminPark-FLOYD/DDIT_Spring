/**
 * 
 */
//case1 : a태그의 요청을 비동기로
let aTags = document.querySelectorAll("a");
console.log(aTags);
aTags.forEach(v => {
	v.addEventListener("click", e=> {
		e.preventDefault();
		let url = e.target.href;
		let options = {
			headers : {
				"Accept" : "application/json"
			}
		};
		//fetch 함수를 쓰면 하나의 promise함수가 생성된다 promise함수는 resolve함수와 reject함수를 가지고 있다
		//resp의 mime타입이 octet-stream이면 0과1의 집합이기 때문에 arrayBuffer또는 Blob로 받는다
		fetch(url, options)
		.then(resp => {
			if(resp.ok){
				return resp.json();
			}else{
				//reject홤수 호출 하기위해 Error객체를 생성한다.
				throw new Error(`요청 처리 실패, 상태코드 : ${resp.status}`);
			}
		})
		//chaining 구조
		.then(obj => console.log(obj))
		.catch(err => console.log(err));
	})
});

//case2 : form의 전송을 비동기로
//어떤 form태그든 적용할수 있는 라이브러리
let forms = document.forms;
forms[0].addEventListener("submit", e => {
	e.preventDefault();
	let form = e.target;
	
	//request line
	let url = form.action;
	let method = form.method;
	
	
	//request header
	//content-type은 form에 enctype이 가지고 있다
	let headers = {
		"content-type" : form.enctype,
		"accept" : "text/html"
	};
	
	//request body
	//FormData의 keys와 values함수를 사용하여 name값과 파라미터 값을 추출할 수 있다
	let formData = new FormData(form);
	console.log(new URLSearchParams(formData).toString());
	let body = new URLSearchParams(formData).toString();
	let options = {
		method : method,
		headers : headers
	};
	
	if(method=="get"){
		url = `${url}?${body}`;
	}else {
		//js는 동적 타입언어이기때문에 동적으로 메소드를 처리할 수 있다
		options.body = body;
	}
	
	fetch(url, options)
	.then(resp=>{
		if(resp.ok) {
			return resp.text();
		}else{
			throw new Error(`요청 처리 실패, 상태코드 : ${resp.status}`);
		}
	})
	.then(html => {
		//document.body.append(html);
		document.body.innerHTML = document.body.innerHTML + html;
	})
	.catch(err => console.log(err));

});