// 회원 관리 탭 클릭 시 회원 리스트 영역 출력
document.getElementById('memberManage').addEventListener('click', function() {
    fetch('/admin/mList/')
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            MemberLists(data);
        })
        .catch(err => {
            console.error(err);
        });
});

function MemberLists(members) {
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (members.length === 0) {
        adminMain.innerHTML = '<p>회원이 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    members.forEach(member => {
        const mList = document.createElement('p');
        
        const formattedDate = member.lastLoginDate ?
        		new Date(member.lastLoginDate).toLocaleDateString('ko-KR') : '날짜 없음';
        
        mList.textContent = `${member.userNo} ${member.userId} ${formattedDate} ${member.enabled} ${member.auth}`;
        list.appendChild(mList);
    });

    adminMain.appendChild(list);
}

 