/**
 * 
	1. 타겟 셀렉팅. document.getElementById(), document.querySelector()
	2. 이벤트 종류 결정
	3. 이벤트 핸들러 구현
	4. 타겟에 이벤트 핸들러 바인드
	모든 이벤트 핸들러는 이벤트를 발생시킨 타겟의 레퍼런스를 가지고 있다 -> event.target 으로 가져올수있다, 화살표 함수는 this가 바인딩이 되지 않는다
	fecth는 resolve함수와 reject함수를 만들어 줘야 한다 (성공, 실패) => .then().catch()
 */
document.getElementById("lyricBtn").addEventListener('click', function() {
	fetch("/WebStudy01/eta")
		.then(response => {
			if(response.ok) {
				return response.text();
			}else {
				throw new Error("처리 실패");
			}
		})
		.then(data => {		
		    lyricArea.innerText = data;	
		})
		.catch((err) => console.log(err));
		
});
