/**
 * 
 */
//let aTags = document.querySelectorAll("a.asyncA");

document.addEventListener("click", (e) => {
	if(e.target.classList.contains("asyncA")) {
	e.preventDefault();
	let aTag = e.target;
	let url = aTag.href;
	//자바 스크립트는 동적타입언어이기 때문에 데이터가 있으면 '참' null이거나 undefind면 "거짓"
	//let method = aTag.dataset.method ? aTag.dataset.method : "get";
	//새로운 문법 조건와 true 내용이 같으면 ??로 처리한다
	let method = aTag.dataset.method ?? "get";
	let headers = {
		"accept" : "text/html"
	}
	let options = {
		method : method,
		headers : headers
	}
	fetch(url, options)
		.then(resp => {
			if(resp.ok){
				return resp.text();
			}else {
				throw new Error(`처리 실패 상태코드 : ${resp.status}`);
			}
		})
		.then(html => {
			msgArea.innerHTML = html;
		})
		.catch(err => console.log(err));
	}else if(e.target.classList.contains("btn")) {
		naviBtn();
	}
});

function naviBtn() {
	var agent = window.navigator.userAgent.toUpperCase();
		var contents = null;
		if(agent.indexOf("EDG") != -1) {											
			contents = "엣지";
		}
		else if(agent.indexOf("WHALE") != -1) { 										
			contents = "웨일";
		}	
		else if(agent.indexOf("CHROME") != -1) {										
			contents = "크롬";
		}
		else if(agent.indexOf("SAFARI") != -1) {	 
			contents = "사파리";		
		}
		else {
			contents = "기타";
		}
		var message = `<h4>현재 당신이 사용하는 브라우저는 [${contents}] 입니다.</h4>`;
		msgArea.innerHTML = message;
}
