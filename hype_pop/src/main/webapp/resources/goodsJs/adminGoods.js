// 쇼핑몰 관리 탭 클릭 시 굿즈 리스트 영역 출력
document.getElementById('storeManage').addEventListener('click', function() {
    fetch('/admin/gList/')
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            GoodsLists(data);
//            console.log(data);
        })
        .catch(err => {
            console.error(err);
        });
});

function GoodsLists(goods) {
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (goods.length === 0) {
        adminMain.innerHTML = '<p>상품이 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    goods.forEach(store => {
        const gsList = document.createElement('p');
        
        const formattedDate = store.sellDate ? 
        		new Date(store.sellDate).toLocaleDateString('ko-KR') : '날짜 없음';
        
        gsList.textContent = `${store.gno} ${store.gname} ${formattedDate} ${store.gprice}`;
        list.appendChild(gsList);
    });

    adminMain.appendChild(list);
}

 