/**
 * 
 */
document.forms[0].addEventListener("submit", (e)=>{ //[] : 연산배열구조
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
	}).then(resp=>resp.text())
	.then(html=>{
		window['bts-area'].innerHTML = html; 
	})
	.catch(e => console.error(e))
})