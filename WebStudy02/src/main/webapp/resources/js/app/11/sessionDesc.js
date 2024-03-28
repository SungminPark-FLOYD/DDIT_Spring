/**
 * 
 */
const SPEED = 100;
//상수를 이용한 경로 가져오기
const contentPath = document.body.dataset.contentPath;

class SessionTimer2 {
	constructor(timeout, element) {
		this.timeout = timeout;
		//span태그
		this.element = element;
		this.element.sessionTimer = this;
		this.init();
	}
	
	createMessageArea() {
		//라이브러리에서는 몇개 생성할지 모르기 때문에 id속성은 쓰지 않고 data속성을 사용한다
		let source = `
			<div style='display:none'>
				세션 연장 메시지
				<button class="control" data-ts-state="true">예</button>
				<button class="control">아니오</button>
			</div>
		`;
		
		//text를 파식하기위해 dom객체 생성
		this.msgArea = new DOMParser().parseFromString(source, 'text/html').body.children[0];
		
		this.msgArea.addEventListener("click", function(e) {
			if(e.target.dataset.tsState) {
				this.reset();
			}
			this.msgArea.style.display = "none";
		}.bind(this));
		
		this.element.after(this.msgArea);
	}
	
	init() {
		//body도 필요없고 내용도 필요없기때문에 헤더에만 데이터를 교환하는 head 메소드를 사용한다
		fetch("", {method : "head"});
		this.timer = this.timeout;
		this.element.innerHTML = this.timeout;
		this.createMessageArea();
		//내부 함수에서 this는 사용하면 위험하기때문에 예약어가 아닌 변수로 this를 만들어서 사용한다.
		let obj = this;
		
		//시간의 1분전까지 기다린다.
		this.timeoutId = setTimeout(function() {
			obj.msgArea.style.display = "block";
		}, (this.timeout-60)*SPEED);
		
		//인터벌 아이디 반환
		this.intervalId = setInterval(function() {
			if(obj.timer > 0) {
				obj.timer--;		
			}else {
				obj.destroy();
			}
			obj.element.innerHTML = obj.timer;
		}, SPEED);
	}
	
	//삭제
	destroy() {
		clearInterval(this.intervalId);
		clearTimeout(this.timeoutId);
		//body가 가지고 있는 모든 자식 찾기
		//document.body.children.length
		this.msgArea.remove();
	}
	
	reset() {
		this.destroy();
		this.init();
	}
}

const SessionTimer = function(timeout, element) {
	this.timeout = timeout;
	//span태그
	this.element = element;
	this.element.sessionTimer = this;
	
	this.createMessageArea = function() {
		//라이브러리에서는 몇개 생성할지 모르기 때문에 id속성은 쓰지 않고 data속성을 사용한다
		let source = `
			<div style='display:none'>
				세션 연장 메시지
				<button class="control" data-ts-state="true">예</button>
				<button class="control">아니오</button>
			</div>
		`;
		
		//text를 파식하기위해 dom객체 생성
		this.msgArea = new DOMParser().parseFromString(source, 'text/html').body.children[0];
		
		this.msgArea.addEventListener("click", function(e) {
			if(e.target.dataset.tsState) {
				this.reset();
			}
			this.msgArea.style.display = "none";
		}.bind(this));
		
		this.element.after(this.msgArea);
	}
	
	//생성자
	this.init = function() {
		//body도 필요없고 내용도 필요없기때문에 헤더에만 데이터를 교환하는 head 메소드를 사용한다
		fetch("", {method : "head"});
		this.timer = this.timeout;
		this.element.innerHTML = this.timeout;
		this.createMessageArea();
		//내부 함수에서 this는 사용하면 위험하기때문에 예약어가 아닌 변수로 this를 만들어서 사용한다.
		let obj = this;
		
		//시간의 1분전까지 기다린다.
		this.timeoutId = setTimeout(function() {
			obj.msgArea.style.display = "block";
		}, (this.timeout-60)*SPEED);
		
		//인터벌 아이디 반환
		this.intervalId = setInterval(function() {
			if(obj.timer > 0) {
				obj.timer--;		
			}else {
				obj.destroy();
			}
			obj.element.innerHTML = obj.timer;
		}, SPEED);
	}
	
	//삭제
	this.destroy = function() {
		clearInterval(this.intervalId);
		clearTimeout(this.timeoutId);
		//body가 가지고 있는 모든 자식 찾기
		//document.body.children.length
		this.msgArea.remove();
	}
	
	this.reset = function() {
		this.destroy();
		this.init();
	}
	
	this.init();
}

document.querySelectorAll("[data-ts-timeout]").forEach(element => {
	let timeout = element.dataset['tsTimeout'];
	console.log(timeout);
	new SessionTimer2(timeout, element);
});

stopBtn.addEventListener("click", () => {
	document.querySelectorAll("[data-ts-timeout]").forEach(element => {
		element.sessionTimer.destroy();
	})
});