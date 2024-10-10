// 회원 관리 탭 클릭 시 회원 리스트 영역 출력
document.getElementById('memebrManage').addEventListener('click', function() {
    fetch('/admin/mList/')
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            MemberLists(data);
//            console.log(data);
        })
        .catch(err => {
            console.error(err);
        });
});

function MemberLists(member) {
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (member.length === 0) {
        adminMain.innerHTML = '<p>회원이 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    member.forEach(store => {
        const mList = document.createElement('p');
        
        const formattedDate = store.lastLogindate ? new Date(store.lastLogindate).toLocaleDateString('ko-KR') : '날짜 없음';
        
        mList.textContent = `${store.userno} ${store.userid} ${formattedDate} ${store.enabled} ${store.auth}`;
        list.appendChild(mList);
    });

    adminMain.appendChild(list);
}

 