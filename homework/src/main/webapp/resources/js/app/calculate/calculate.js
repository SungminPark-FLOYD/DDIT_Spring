/**
 * 
 */
document.addEventListener("DOMContentLoaded", () => {
	calform.addEventListener("submit", e => {
		e.preventDefault();
		
		let url = calform.action;
		let method = "post";
		let headers = {
			"content-type" : "application/x-www-form-urlencoded",
			"accept" : "text/html"
		};
		
		let formData = new FormData(calform);
		console.log(new URLSearchParams(formData).toString());
		let body = new URLSearchParams(formData).toString();
		let options = {
				method : method,
				headers : headers,
				body : body
			};
		 fetch(url, options)
		.then(resp=>{
			if(resp.ok) {
				
				return resp.text();
			}else{
				throw new Error(`요청 처리 실패, 상태코드 : ${resp.status}`);
			}
		})
		.then(html => {
			resultArea.innerHTML = html;
		})
		.catch(err => alert(err))
		
	})
})