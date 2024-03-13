<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.top{
		border: 3px solid black;
		width: 400px;
		height: 400px;
		text-align: center;
	}
	.middle{
		border: 3px solid red;
		width: 200px;
		height: 200px;
	}
	:focus {
		border:3px solid blue;
	}
</style>
</head>
<body>
<pre>
	DOM 트리구조의 노드로 구성되는 HTML 엘리먼트를 대상으로 발생하는 이벤트
	해당 엘리먼트의 부모나 자식 노드쪽으로 이벤트가 전파(propagation)되는 구조를 가짐
	bubbling propagation : 자식 노드에서 부모 노드쪽으로 전파되는 구조
	capturing propagation : 부모 노드에서 자식 노드쪽으로 전파되는 구조
</pre>
<div id="bubbleTop" class="top bubbling" tabindex="-1">
	<div id="bubbleMiddle" class="middle bubbling" tabindex="-1">
		<button id="bubbleBottom" class="bubbling" tabindex="-1">최하위 자식버튼- Event Bubbling</button>
	</div>
</div>
<div id="captureTop" class="top capturing" tabindex="1">
	<div id="captureMiddle" class="middle capturing" tabindex="2">
		<button id="captureBottom" class="capturing" tabindex="3">최하위 자식버튼- Event Capturing</button>
	</div>
</div>
<script type="text/javascript">
	document.querySelectorAll(".bubbling").forEach((be) => {
		be.addEventListener("click", (e) => {
			console.log("click bubbling to ", be.id, "target :", e.target.id)
			//preventDefault : 해당 액션이 가진것을 중단시킨다 -> 이벤트를 막는 것이 아니다.
			//e.preventDefault();
			//stopPropagation : 이벤트 자체를 중단시킨다.
// 			e.stopPropagation();
		})
		//focus : 이벤트가 전파되지 않는다
		be.addEventListener("focus", (e) => {
			console.log("focus bubbling to ", be.id, "target :", e.target.id)
		})
	});
	
	//익명함수의 3번째 파라미터는 전파구조를 설정한다. false는 bubbling / true 는 capturing
	document.querySelectorAll(".capturing").forEach((ce) => {
		ce.addEventListener("click", (e) => {
			console.log("click capturing from ", ce.id, "target :", e.target.id)
// 			e.stopPropagation();
		}, true)
		//focus : capturing은 이벤트가 전파된다
		ce.addEventListener("focus", (e) => {
			console.log("focus capturing to ", ce.id, "target :", e.target.id)
		}, true)
	});
</script>
</body>
</html>








