/**
 * 
 */
document.forms[0].addEventListener("submit", function (e) {
		e.preventDefault();
		let leftOp = this.leftOp.value;
		let rightOp = this.rightOp.value;
		let operator = this.operator.value;
		
		let form = e.target;
		let url = form.url;
		let method = form.method;
		let headers = {
				"content-type" : "application/json",
				"accept" : "application/json"
			}
		
		let data = {
				"leftOp" : leftOp,
				"rightOp" : rightOp,
				"operator" : operator
		}
		
		fetch("${pageContext.request.contextPath}/mission/case08_9",{
			method : method,
			headers : headers,
			body : JSON.stringify(data)
		}).then(resp => resp.json()
		).then(result => {
			console.log(result);
		})
})