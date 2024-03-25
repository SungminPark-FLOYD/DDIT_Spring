/**
 * 
 */
const cPath = document.body.dataset.contextPath;
const log = console.log;
//$(document).ready(function() {
//$("#tree").fancytree({
//        // Fancytree 설정 옵션들
//        // 예: 트리 데이터 소스 설정 등
//        source: {
//            // 서버에서 데이터를 비동기적으로 로드할 경우 설정
//            url: `&{cPach}/case2/serverFile`, // 데이터를 가져올 URL
//            dataType: "json" // 데이터 형식 (JSON)
//        },
//        // Fancytree 이벤트 핸들러 추가
//        activate: function(event, data) {
//            var node = data.node;
//            var eventType = event.originalEvent.type;
//
//            // 클릭 이벤트 처리
//            if (eventType === "click") {
//                handleSingleClick(node);
//            }
//            // 이중 클릭 이벤트 처리
//            else if (eventType === "dblclick") {
//                handleDoubleClick(node);
//            }
//        }
//    });
//});
//function handleSingleClick(node) {
//    // 클릭된 노드가 폴더인 경우에만 실행
//    if (!node.data.isFile) {
//        let url = `${node.data.href}&type=${node.data.click}`;
//        let method = "get";
//        let headers = {
//            'accept': 'application/json'
//        };
//
//        fetch(url, {
//            method: method,
//            headers: headers
//        }).then(resp => resp.json())
//            .then(outter => {
//                let jsonObj = outter.wrapperList;
//                let code = "<ul class='col-6'>";
//                console.log(jsonObj);
//                jsonObj.forEach(data => {
//                    let de = "";
//                    if (data.file == true) de = "file";
//                    else de = "folder";
//                    code += `<li data-name="${data.name}" id="${data.path}" class="${de}">
//                                ${data.name}</li>`;
//                });
//                code += '</ul>';
//                let card = document.querySelector(".card");
//                card.innerHTML = code;
//            })
//            .catch(e => console.error(e));
//    }
//}
//
// 이중 클릭 이벤트 처리 함수
function handleDoubleClick(node) {
    let url = `${node.data.href}&type=${node.data.dblclick}`;
    window.location.href = url;
}
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
window['tree'].addEventListener("click", e => {
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