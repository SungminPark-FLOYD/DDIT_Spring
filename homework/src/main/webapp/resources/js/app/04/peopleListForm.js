/**
 * 
 */
let lis = document.querySelectorAll('li');
lis.forEach(function(li) {
    li.addEventListener('click', function() {
        let memberId = li.id;
        showModal(memberId);
    });
});


let modal = document.getElementById('myModal');
let button = document.getElementById('btn');

button.addEventListener('click', function() {	
    modal.style.display = "none";
    
});

function showModal(memberId) {
	fetch('../../homework/people.do?who=' + memberId)
    .then(response => response.json())
    .then(data => {
        // 모달 내용 갱신
        document.getElementById('name').textContent = data[0];
        document.getElementById('gender').textContent = data[1];
        document.getElementById('age').textContent = data[2];
        document.getElementById('addr').textContent = data[3];
        // 모달 표시
        var modal = document.getElementById('myModal');
        modal.style.display = "block";
    })
    .catch(error => {
        console.error('Error fetching member details:', error);
    });
}
        