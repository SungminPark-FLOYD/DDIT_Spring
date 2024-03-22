/**
 * 
 */
const cPath = document.body.dataset.contextPath;
const log = console.log;
//li태그 안에 folder클래스의 자식 태그의 a태그를 select
document.querySelectorAll("li.folder>a").forEach(a => {
	a.addEventListener("click", e => {
		e.preventDefault();
		let type = a.dataset.click;
		let url = `${ a.href}&type=${type}`;
		let method = "get";
		let headers = {
			'accept' : 'application/json'
		};
		fetch(url, {
			method : method,
			headers : headers
		}).then(resp => resp.json())
		.then(outter => {
			let jsonObj = outter.wrapperList;
			let code = "<ul class='col-6'>";				
			log(jsonObj)
			jsonObj.forEach(data => {
				let de = "";
				if(data.file == true) de = "file";
				else de = "folder";
				code += `<li data-name="${data.name}" id="${data.path }" class="${de}">
						${data.name }</li>`
			})	
			code += '</ul>';
			let card = document.querySelector(".card");
			card.innerHTML = code;		
		})
		.catch(e => console.error(e))
	});
	a.addEventListener("dblclick", e=> {
		e.preventDefault();
		let type = a.dataset.dblclick;
		let url = `${ a.href}&type=${type}`;
		window.location.href = url;
	})
});
window['right-area'].addEventListener("click", e => {
	if(!e.target.classList.contains("file")) return false;
	let url = `${cPath}/case2/fileInfo`;
	let method = "get";
	let headers = {
		"accept" : "application/json"
	};
	let urlParams = new URLSearchParams();
	urlParams.append("path", e.target.id);
	let queryString = urlParams.toString();
	
	fetch(`${url}?${queryString}`, {
		method:method,
		headers:headers
	}).then(resp => resp.json())
	.then(jsonObj => {
		log(jsonObj.size);
		log(e.target.dataset.name);
		e.target.innerHTML = e.target.dataset.name + ", " + jsonObj.size;
	})
	.catch(e => console.error(e)
	)
});