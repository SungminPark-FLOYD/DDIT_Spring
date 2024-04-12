/**
 * 
 */

function logout(e) {
	e.preventDefault();
	let url = e.target.href;
	
	document.logoutform.action = url;
	document.logoutform.requestSubmit();
}
