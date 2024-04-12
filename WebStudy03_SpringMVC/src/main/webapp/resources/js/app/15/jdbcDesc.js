/**
 * 
 */
document,addEventListener("DOMContentLoaded", () => {
	const url = document.body.dataset.contextPath;

	let method = "get";
	let headers ={
		"accept" : "application/json"
	}
	
	fetch(`${url}/15/jdbcDesc.do`, {	//json data를 수신하고, csr방식으로 html 소스생성
			method : method,
			headers : headers
		}).then(resp => resp.json())
		//구조분해 분법
		.then(({headers, list}) => {
			let header = headers;
			let resList = list;
			let trTag = `<tr>${headers.map(cn => `<th>${cn}</th>`).join("")}</tr>`;		
			window['head-area'].innerHTML = trTag;

			
			let trTags = `<tr>${list.map(d => `<td>${d.propertyName}</td>
												<td>${d.propertyValue}</td>
												<td>${d.description}</td>`)}</tr>`;
			
						
			let code1 = "";
			resList.forEach(data => {
				code1 += `<tr>
							<td>${data.propertyName}</td>
							<td>${data.propertyValue}</td>
							<td>${data.description}</td>
						</tr>`;
			})
			window['data-area'].innerHTML = code1;
			
		})
		.catch(e => console.error(e))
})
