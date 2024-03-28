/**
 * 
 */

setInterval(()=>{
	clientArea.innerHTML = Date.now();

	fetch("/WebStudy01/now")
		.then(response => response.text())
		.then(data => {		
		    serverArea.innerHTML = data;	
		});
}, 1000);
setInterval(()=>{

}, 1000);	