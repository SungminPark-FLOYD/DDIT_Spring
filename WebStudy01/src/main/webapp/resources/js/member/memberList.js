/**
 * 
 */
//html에서는 기본적으로 element속성이 모슨 function을 가지고 있다.
//$(document).on("ready", function() {
//	
//})

const cPath = document.body.dataset.url;

//간소화 시킨 코드
$(function() {
	const $modal = $("#exampleModal").on("show.bs.modal", function(event) {
		let tr = event.relatedTarget;
		let memId = $(tr).data("memId")
		let url = `${cPath}/member/memberDetail.do`;
		let method = "get";

		$.ajax({
			url :url ,
			method :method, 
			dataType : "json",
			data: {
				who : memId
			},
			success:function({member}, status, jqXHR) {
				console.log(member?.memId);
				$modal.find("td[id]").each(function(index, td) {
					let propName = td.id;
					td.innerHTML = member[propName];
				})
			},
			error:function(jqXHR, status, errorText) {
				console.log(jqXHR,status, errorText);
			}
		});
	}).on("hidden.bs.modal", function(event) {
		$modal.find("td[id]").html("");
//		$modal.find("td[id]").each(function(index, td) {
//			$(td).empty();
//		})
	})	
	
	//디센던트 구조
//	$(document).on('click', 'tr[data-mem-id]', function () {
		//jQuery객체로 다시 만들기
		
//		this.dataset.newKey="value";
//		//객체 형식으로도 저장할 수 있다
//		$(this).data("newKey", {})
		//html의 dataset을 data형식으로 가져올 수 있따
//		
//	})
});

document.addEventListener('DOMContentLoaded', function() {
	
});

