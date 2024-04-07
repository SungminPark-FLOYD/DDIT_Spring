/**
 * 
 */
let cPath = document.body.dataset.contextPath;
let prodId = $('#prodId').text();

$('#updateBtn').on('click', function() {
	location.href = `${cPath}/prod/prodUpdate.do?what=${prodId}`;
})