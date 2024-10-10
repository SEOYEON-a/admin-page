// 팝업스토어 관리하기 버튼 클릭 시 팝업 스토어 리스트 영역 출력
document.getElementById('popUpManage').addEventListener('click', function() {
    fetch('/admin/psList/') 
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            PopUpStoreLists(data); 
            console.log(data);
        })
        .catch(err => {
        	console.log(err);
        });
});
function PopUpStoreLists(popUpStores) {
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (popUpStores.length === 0) {
        adminMain.innerHTML = '<p>팝업 스토어가 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    popUpStores.forEach(store => {
        const psList = document.createElement('p');
        psList.textContent = `${store.psNo} ${store.psName} ${store.psStartDate} ${store.psEndDate}`;
        list.appendChild(psList);
    });

    adminMain.appendChild(list);
}

 