/**
 * 
 */

alert("Dummy");

let divTag = document.getElementById('mainArea');
let handler = function(event){
	let target = event.target;
	console.log(target === divTag)
	target.style.backgroundColor = "red";
	
};
divTag.addEventListener("click", handler);

