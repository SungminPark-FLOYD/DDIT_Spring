/**
 * 
 */
//html에서는 기본적으로 element속성이 모슨 function을 가지고 있다.
$(document).on('click', 'tr[data]', function () {
	console.log($(this))
})
document.addEventListener("click", e => {
	if (e.target.tagName != 'TR') return false;
	let memId = e.target.parentNode.dataset.memId;
	let url = document.body.dataset.url;
	let myInput = document.getElementById('myInput')
	$.ajax({
		type: 'get',
		url : url + "/member/memberDetail.do",
		data: {
        	who: memId 
    	},
		contentType:'application/json;charset=utf-8',
		dataType:'json',
		success: function(result){
			let member = result.member;
			
			let code = '<table>'
			for(let i in member) {
				code += `<tr>
				<td>${i}</td>
				<td>${member[i]}</td>
				</tr>`
			}
			code += '</table>'
			document.querySelector(".modal-body").innerHTML = code;
		},
		error:function(xhr, status, error) {
	        console.error("AJAX 요청 실패:", status, error);
	        // 에러에 대한 적절한 처리
    	}
	})
})