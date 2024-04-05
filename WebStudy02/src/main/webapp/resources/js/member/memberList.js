/**
 * 
 */
//html에서는 기본적으로 element속성이 모슨 function을 가지고 있다.
//$(document).on("ready", function() {
//	
//})

const cPath = document.body.dataset.contextPath;

//간소화 시킨 코드
$(function() {
	const $modal = $("#exampleModal").on("show.bs.modal", function(event) {
		let tr = event.relatedTarget;
		let memId = $(tr).data("memId") 
		let url = `${cPath}/member/memberDetail.do`;
//		let url = `/member/memberDetail.do`;
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
				});
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
	});	
	
	//이벤트 강제로 발생
	$("tr[data-mem-id].active").trigger("click");
	
	//디센던트 구조
//	$(document).on('click', 'tr[data-mem-id]', function () {
//		//jQuery객체로 다시 만들기
//		
//		this.dataset.newKey="value";
//		//객체 형식으로도 저장할 수 있다
//		$(this).data("newKey", {})
//		//html의 dataset을 data형식으로 가져올 수 있따
//		
//	})

	
});

//document.addEventListener('DOMContentLoaded', function() {
//	const modal = document.getElementById("exampleModal");
//
//    modal.addEventListener("show.bs.modal", function(event) {
//        const tr = event.relatedTarget;
//        const memId = tr.dataset.memId;
//        const url = `${cPath}/member/memberDetail.do?who=${memId}`;
//        const method = "GET";
//
//        fetch(url, {
//            method: method,
//            headers: {
//				"accept" : "application/json",
//                "Content-Type": "application/json"
//            }
//        })
//        .then(response => {
//            if (!response.ok) {
//                throw new Error("Network response was not ok");
//            }
//            return response.json();
//        })
//        .then(data => {
//            const member = data.member;
//            console.log(member?.memId);
//            const tdElements = modal.querySelectorAll("td[id]");
//            tdElements.forEach(td => {
//                const propName = td.id;
//                td.innerHTML = member[propName];
//            });
//        })
//        .catch(error => {
//            console.error("Fetch Error:", error);
//        });
//    });
//
//    modal.addEventListener("hidden.bs.modal", function(event) {
//        const tdElements = modal.querySelectorAll("td[id]");
//        tdElements.forEach(td => {
//            td.innerHTML = "";
//        });
//    });
//});

