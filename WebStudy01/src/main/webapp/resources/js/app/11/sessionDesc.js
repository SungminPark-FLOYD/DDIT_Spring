/**
 * 
 */
//상수를 이용한 경로 가져오기
const contentPath = document.body.dataset.contentPath;
const SessionTimer = function(timeout, element) {
	this.timeout = timeout;
	this.element = element;
	this.timer = this.timeout;
	this.element.innerHTML = this.timeout;
	//내부 함수에서 this는 사용하면 위험하기때문에 예약어가 아닌 변수로 this를 만들어서 사용한다.
	let obj = this;
	
	//인터벌 아이디 반환
	this.intervalId = setInterval(function() {
		if(obj.timer > 0) {
			obj.timer--;		
		}else {
			clearInterval(obj.intervalId);
		}
		obj.element.innerHTML = obj.timer;
	}, 100)
}
document.querySelectorAll("[data-ts-timeout]").forEach(element => {
	let timeout = element.dataset['tsTimeout'];
	console.log(timeout);
	new SessionTimer(timeout, element);
});