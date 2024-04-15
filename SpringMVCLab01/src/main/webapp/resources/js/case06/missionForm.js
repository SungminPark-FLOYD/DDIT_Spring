/**
 * 
 */
 const $resultArea = $("#result-area");
$("[name='calForm']").on("submit", function(e) {
	e.preventDefault();
	let url = this.action;
	let method = this.method;
	let data = $(this).serialize();
	$.ajax({
		url : url
		,method : method
		,data : data
		,dataType:"json"
		,success:function(outer) {
			$resultArea.html(outer.result);
		}
	});
});