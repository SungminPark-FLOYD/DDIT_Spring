/**
 * 폼을 전송할때 페이자가 전환되는 걸 중단하고 , 
선택한 이미지를 페이지의 하단에 (body append 구조)에 출력.
1. submit 이벤트 중단
2. 선택한 option value확보
3. 새로운 이미지 태그 생성
4. img 태그의 src 속성을 선택한 이미지로 대체
5. body에 new img를 추가(append)
 */
//=> form이 하나밖에 없으므로 0번째		
document.forms[0].addEventListener("submit", (event)=> {
	event.preventDefault();
	let url = event.target.action;
	let imageName = event.target.name.value;
	let newImg = document.createElement("img");
	newImg.src = `${url}?name=${imageName}`;
	document.body.appendChild(newImg);
}) 
		
	