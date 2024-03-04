/**
 * 
 */

document.addEventListener('DOMContentLoaded', function() {
		
            // 페이지 로딩 시 이미지 목록을 비동기적으로 가져오는 함수 호출
            fetchImageList();
        });

        function fetchImageList() {
            // fetch API를 사용하여 이미지 목록 가져오기
            fetch('/homework/case5/imageForm.do')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to fetch image list.');
                    }
                    return response.text(); // 텍스트 데이터로 처리
                })
                .then(data => {
                    document.getElementById('imageName').innerHTML = data;
                })
                .catch(error => {
                    // 에러 처리
                    console.error(error.message);
                });
        }

        function renderImage() {
            // 선택된 이미지를 서버로 전송하고 이미지 랜더링 수행
            var selectedImage = document.getElementById('imageName').value;
            fetch('/homework/image.do?name=' + selectedImage)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to render image.');
                    }
                    // 성공적으로 이미지를 받아왔을 때 처리
                    // 동적으로 이미지를 랜더링하거나 페이지 업데이트 수행
                })
                .catch(error => {
                    // 에러 처리
                    console.error(error.message);
                });
        }

/*document.forms[0].addEventListener("submit", (event)=> {
	event.preventDefault();
	let url = event.target.action;
	let imageNmae = event.target.name.value;
	let newImg = document.createElement("img");
	newImg.src = `${url}?name=${imageNmae}`;
	document.body.appendChild(newImg);
}) */