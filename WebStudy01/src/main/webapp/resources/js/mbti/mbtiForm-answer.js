/**
 * 
 */


console.log(window['mbti-form']);
window['mbti-form'].addEventListener("submit", (e)=>{ //[] : 연산배열구조
	e.preventDefault();
	let form = e.target;
	let url = form.action;
	let method = form.method;
	let headers = {
		"content-type" : form.enctype ,
		"accept" : "text/html"
	}
	let fd = new FormData(form);
	let queryString = new URLSearchParams(fd).toString();
	let body = queryString;
	
	fetch(url, {
		method:method,
		headers:headers,
		body:body
	}).then(resp=>resp.text())//리절브함수
	.then(html=>{
		//let newDocument = new DOMParser().parseFromString(html, headers.accept) //이거때문에 모듈화한 jsp에 <h4>가 안보였던것.
		//let element = newDocument.querySelector("pre");
		//window['mbti-area'].append(element)
		window['mbti-area'].innerHTML = html; 
	})
	.catch()//리젝트함수
	
	
})