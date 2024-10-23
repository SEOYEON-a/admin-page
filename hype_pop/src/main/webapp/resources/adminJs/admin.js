// 활성화된 탭을 추적하기 위한 변수
let activeTab = 'popUp';

// form 객체 가져오기
const f = document.forms[0];

// 페이징 처리 
//let currentPage = 1;  // 현재 페이지 
let currentPageForPopUp = 1; // 팝업 스토어 현재 페이지
let currentPageForGoods = 1; // 상품 현재 페이지
const amount = 14;	  // 출력되는 개수 
let totalPagesForPopUp = 0; // 팝업 스토어 총 페이지 수
let totalCountForPopUp = 0; // 팝업 스토어 총 아이템 수
let totalPagesForGoods = 0; // 상품 총 페이지 수
let totalCountForGoods = 0; // 상품 총 아이템 수

// **** 관리자 Header 영역 (공통) ****
// 탭 클릭 시 해당 기능 활성화
document.getElementById('popUpManage').addEventListener('click', function() {
    activeTab = 'popUp'; // 현재 활성화된 탭 업데이트    
    currentPageForPopUp = 1; // 초기화
    loadPopUpStores(currentPageForPopUp);     
//    activatePopUpTab(); // 팝업 관리 기능 활성화
//    localStorage.setItem('activeTab', activeTab);  // 마지막 탭 복원
    document.getElementById('registerBtn').style.visibility = 'visible'; // 등록 버튼 보이기
});
document.getElementById('storeManage').addEventListener('click', function() {
    activeTab = 'store'; // 현재 활성화된 탭 업데이트
    currentPageForGoods = 1; // 초기화
    loadGoodsStores(currentPageForGoods); 
//    loadGoodsStores(currentPage); // 원래코드
    
//    activateStoreTab(); // 쇼핑몰 관리 기능 활성화
//    localStorage.setItem('activeTab', activeTab);  // 마지막 탭 복원
    document.getElementById('registerBtn').style.visibility = 'visible'; // 등록 버튼 보이기
});
document.getElementById('memberManage').addEventListener('click', function() {
    activeTab = 'member'; // 현재 활성화된 탭 업데이트
    activateMemberTab(); // 회원 관리 기능 활성화
//    localStorage.setItem('activeTab', activeTab);  // 마지막 탭 복원
});

// **** 관리자 페이지 영역 ****
// **** 팝업스토어 영역 ****
// 검색 버튼 클릭 이벤트 추가
document.getElementById('searchBTN').addEventListener('click', function() {
    if (activeTab === 'popUp') {
    	// 둘이 기능은 똑같아서 하나로 합쳐도 무방할 듯
        //searchStores();
    	loadPopUpStores(currentPageForPopUp);
    } else if (activeTab === 'store') {
//        searchGoods();
        loadGoodsStores(currentPageForGoods);
    } else if (activeTab === 'member') {
    	searchMembers();
    }
});

// 팝업스토어 관리하기 활성화
//function activatePopUpTab() {
//    loadPopUpStores(currentPage); // 현재 페이지의 팝업 스토어 로드
//}

function loadPopUpStores(page = 1) {
	currentPageForPopUp = page; // 현재 페이지 업데이트 (추가된 코드)
	 const searchPs = document.getElementById('adminSearchBox').value;
	    fetch(`/admin/psList?searchPs=${encodeURIComponent(searchPs)}&pageNum=${page}&amount=${amount}`)
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
	            }
	            return response.json();
	        })
	        .then(data => {
//	            console.log('API 응답:', JSON.stringify(data, null, 2));
	            if (data.list && data.total) {
	                PopUpStoreLists(data.list);

	                totalCountForPopUp = data.total; // 전체 아이템 수 저장
	                totalPagesForPopUp = Math.ceil(totalCountForPopUp / amount); // 총 페이지 수 계산
	                
	                createPagination(totalPagesForPopUp, totalCountForPopUp, true); // 페이지네이션 생성
	                
	                // totalCount와 totalPages 계산
//	                const totalCount = data.total;
//	                const totalPages = Math.ceil(totalCount / amount); // 전체 페이지 수 계산
//	                createPagination(totalPages, totalCount); // 페이지 생성 (원래코드)
	            } else {
	                throw new Error('잘못된 데이터 구조입니다. 데이터: ' + JSON.stringify(data));
	            }
	        })
	        .catch(err => {
	            console.error('오류:', err);
	        });
}

// 이전, 다음 버튼 없음
//function loadPopUpStores(page = 1, amount = 10) {
//	const searchPs = document.getElementById('adminSearchBox').value;
//	fetch(`/admin/psList?searchPs=${encodeURIComponent(searchPs)}&pageNum=${page}&amount=${amount}`)
//	    .then(response => {
//	        if (!response.ok) {
//	            throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
//	        }
//	        return response.json();
//	    })
//	    .then(data => {
//	        console.log(data); // API 응답 로그 확인
//	        if (data.list && data.pageMaker) { // 데이터 구조 확인
//	            PopUpStoreLists(data.list);
//	            createPagination(data.pageMaker, data.total);
//	        } else {
////	        	console.log(200);
//	            throw new Error('잘못된 데이터 구조입니다. 데이터: ' + JSON.stringify(data));
//	        }
//	    })
//	    .catch(err => {
//	        console.log(err);
//	    });
//}

// 팝업스토어 관리하기 버튼 클릭 시 팝업 스토어 리스트 영역 출력
function PopUpStoreLists(popUpStores) {
    const popSList = document.querySelector('#AllList');
    const adminMain = document.querySelector('.adminMain');
    popSList.innerHTML = '';
    
    if (popUpStores.length === 0) {
    	adminMain.innerHTML = '<p>팝업 스토어가 없습니다.</p>';
    	return;
    }
    
//    psNo 기준으로 정렬 (db에서 던질 때 정렬하기 때문에 따로 할 필요 x)
//    popUpStores.sort((a, b) => a.psNo - b.psNo);

    popUpStores.forEach(store => {	
    	// 팝업스토어 개별 div, p태그 적용
    	const list = document.createElement('div');
    	const psList = document.createElement('p');

    	// 팝업스토어 이름 클릭 시 링크로 이동
        const link = document.createElement('a');
        link.href = `popUpUpdate?psNo=${store.psNo}`;
        link.textContent = store.psName; // 팝업스토어 이름에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경
        link.style.textDecoration = 'none'; // 밑줄 제거

        psList.appendChild(document.createTextNode(` ${store.psNo} `));
        psList.appendChild(link); // p 태그에 a 태그 추가
        psList.appendChild(document.createTextNode(` ${new Date(store.psStartDate).toLocaleDateString()} ~ ${new Date(store.psEndDate).toLocaleDateString()}`));
        list.appendChild(psList);
        popSList.appendChild(list);
    });
    
}

function createPagination(totalPages, totalCount, isPopUp) {
//	console.log('페이지 수:', totalPages); // 페이지 수 로그 출력
//    console.log('전체 아이템 수:', totalCount); // 전체 아이템 수 로그 출력
//    console.log("paginationContainer의 null 유무 : " + paginationContainer);
	const paginationContainer = document.getElementById('pagination');    
    paginationContainer.innerHTML = ''; // 기존 내용 제거

    // totalPages가 0인 경우 페이지네이션을 비워둡니다.	
    if (totalPages <= 0) {
        console.log('페이지 수가 0이므로 페이지네이션을 표시하지 않습니다.');
        return;
    }
    
    // 이전 버튼 추가
    const prevBtn = document.createElement('button');
    prevBtn.textContent = '<';
    prevBtn.disabled = (isPopUp ? currentPageForPopUp : currentPageForGoods) === 1; // 현재 페이지가 1이면 비활성화
//    prevBtn.disabled = currentPage === 1; // 현재 페이지가 1이면 비활성화 (원래코드)
    prevBtn.addEventListener('click', () => {
    	// 이전 페이지 데이터 로드
    	if (isPopUp) {
            if (currentPageForPopUp > 1) {
                currentPageForPopUp--;
                loadPopUpStores(currentPageForPopUp);
            }
        } else {
            if (currentPageForGoods > 1) {
                currentPageForGoods--;
                loadGoodsStores(currentPageForGoods);
            }
        }
//        if (currentPage > 1) {
//            currentPage--;
//            loadPopUpStores(currentPage); // 이전 페이지 데이터 로드 (원래코드)
//        }
    });
    paginationContainer.appendChild(prevBtn);

    // 페이지 링크 추가
    for (let i = 1; i <= totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.textContent = i;
        pageLink.addEventListener('click', (e) => {
            e.preventDefault();
            if (isPopUp) {
                currentPageForPopUp = i; // 현재 페이지 업데이트
                loadPopUpStores(currentPageForPopUp);
            } else {
                currentPageForGoods = i; // 현재 페이지 업데이트
                loadGoodsStores(currentPageForGoods);
            }
//            currentPage = i; // 현재 페이지 업데이트 (원래코드)
//            loadPopUpStores(currentPage); // 해당 페이지의 데이터를 로드 (원래코드)
        });

        // 현재 페이지 굵게 변경
        if (isPopUp && i === currentPageForPopUp || !isPopUp && i === currentPageForGoods) {
            pageLink.style.fontWeight = 'bold';
        }
        // 원래코드
//        if (i === currentPage) {
//            pageLink.style.fontWeight = 'bold'; 
//        }
        paginationContainer.appendChild(pageLink);
    }

    // 다음 버튼 추가
    const nextBtn = document.createElement('button');
    nextBtn.textContent = '>';
    nextBtn.disabled = (isPopUp ? currentPageForPopUp : currentPageForGoods) === totalPages; // 현재 페이지가 마지막 페이지면 비활성화
//    nextBtn.disabled = currentPage >= totalPages; // 현재 페이지가 마지막 페이지면 비활성화 (원래코드)
    nextBtn.addEventListener('click', () => {
        if (isPopUp) {
            if (currentPageForPopUp < totalPages) {
                currentPageForPopUp++;
                loadPopUpStores(currentPageForPopUp); // 팝업 스토어 다음 페이지 데이터 로드
            }
        } else {
            if (currentPageForGoods < totalPages) {
                currentPageForGoods++;
                loadGoodsStores(currentPageForGoods); // 굿즈 다음 페이지 데이터 로드
            }
        }
        // 원래코드
////    if (currentPage < totalPages) {
////    currentPage++;
////    loadPopUpStores(currentPage); // 다음 페이지 데이터 로드
////}
    });
    paginationContainer.appendChild(nextBtn);
}

// 페이지네이션 생성
//function createPagination(pageMaker, totalCount) {
//    const paginationContainer = document.getElementById('pagination'); // 페이지네이션 UI 요소 선택
//    const totalPages = Math.ceil(totalCount / amount); // 총 페이지 수 계산
//    paginationContainer.innerHTML = ''; // 기존 내용 제거
//    for (let i = 1; i <= totalPages; i++) {
//        const pageLink = document.createElement('a');
//        pageLink.href = '#';
//        pageLink.textContent = i; // 페이지 번호
//        pageLink.addEventListener('click', (e) => {
//            e.preventDefault(); // 기본 링크 동작 방지
//            currentPage = i; // 현재 페이지 업데이트
//            loadPopUpStores(currentPage); // 해당 페이지의 데이터를 로드
//        });
//
//        paginationContainer.appendChild(pageLink);
//}
//    // 현재 페이지 및 총 페이지 수 표시    
////    const psPageNumbers = document.getElementById('ps-page-numbers');
////    psPageNumbers.textContent = `Page ${currentPage} of ${totalPages}`;
//}	

// 검색어에 따라 팝업 스토어 리스트 출력
//function searchStores() {
//    const searchPs = document.getElementById('adminSearchBox').value;
//    if (searchPs) {
//        fetch(`/admin/psList?searchPs=${encodeURIComponent(searchPs)}&pageNum=1&amount=${amount}`)
//            .then(response => {
//                if (!response.ok) {
//                    throw new Error('검색 결과가 없습니다: ' + response.statusText);
//                }
//                return response.json();
//            })
//            .then(data => {
//                PopUpStoreLists(data.list);
//                createPagination(data.pageMaker, data.total); // data.total을 사용 (검색 기능)
////                createPagination(data.pageMaker.total); // 검색 결과에 맞춰 페이지네이션 업데이트
//            })
//            .catch(err => {
//                console.log(err);
//                alert('검색 결과를 불러오는 데 오류가 발생했습니다.');
//            });
//    } else {
//        alert('검색어를 입력하세요.');
//    }
//}

//**** 쇼핑몰(상품) 영역 ****
// 쇼핑몰 관리하기 활성화
//function activateStoreTab() {
//    fetch('/admin/gList/')
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
//            }
//            return response.json();
//        })
//        .then(data => {
//            GoodsLists(data);
//            console.log(data);
//        })
//        .catch(err => {
//            console.log(err);
//        });
//
//    // 검색 기능 연결
//    document.getElementById('searchBTN').onclick = searchGoods;
//}

// 페이징 O
// 굿즈는 너무 많아서 amount 30으로 일단 선언함
function loadGoodsStores(page = 1, amount = 30) {
	 currentPageForPopUp = page; // 현재 페이지 업데이트 (추가된코드임)
	 const searchGs = document.getElementById('adminSearchBox').value;
	    fetch(`/admin/gList?searchGs=${encodeURIComponent(searchGs)}&pageNum=${page}&amount=${amount}`)
	        .then(response => {
	            if (!response.ok) {
	                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
	            }
	            return response.json();
	        })
	        .then(data => {
//	            console.log('API 응답:', JSON.stringify(data, null, 2));
	            if (data.list && data.total) {
	            	GoodsLists(data.list);

	            	totalCountForGoods = data.total; // 전체 아이템 수 저장
	                totalPagesForGoods = Math.ceil(totalCountForGoods / amount); // 총 페이지 수 계산
	                createPagination(totalPagesForGoods, totalCountForGoods, false); // 페이지네이션 생성
	                
	                // totalCount와 totalPages 계산
//	                const totalCount = data.total;
//	                const totalPages = Math.ceil(totalCount / amount); // 전체 페이지 수 계산

//	                createPagination(totalPages, totalCount, false);
//	                createPagination(totalPages, totalCount); // 페이지 생성 (원래코드)
	            } else {
	                throw new Error('잘못된 데이터 구조입니다. 데이터: ' + JSON.stringify(data));
	            }
	        })
	        .catch(err => {
	            console.error('오류:', err);
	        });
}

// 쇼핑몰 관리하기 버튼 클릭 시 상품 리스트 영역 출력
//function GoodsLists(goods) {
//	const adminMain = document.querySelector('.adminMain');
//	adminMain.innerHTML = '';
//	
//	if (goods.length === 0) {
//		adminMain.innerHTML = '<p>상품이 없습니다.</p>';
//		return;
//	}
//	
//	const list = document.createElement('div');
//	
//	goods.forEach(store => {
//		const gsList = document.createElement('p');
//		
//		// 굿즈(상품) 이름 클릭 시 굿즈 정보 수정 페이지로 이동
//        const link = document.createElement('a');
//        link.href = `goodsUpdate?gNo=${store.gno}`;
//        link.textContent = store.gname; // 굿즈(상품) 이름에만 링크 걸리게 설정
//        link.style.color = 'black'; // 링크 색상 변경
//        link.style.textDecoration = 'none'; // 밑줄 제거
//       
//        gsList.appendChild(document.createTextNode(` ${store.gno} `));
//        gsList.appendChild(link); // p 태그에 a 태그 추가
//        gsList.appendChild(document.createTextNode(`${store.sellDate} ${store.gprice}`));
//
//        list.appendChild(gsList);
//	});
//	
//	adminMain.appendChild(list);
//}
function GoodsLists(goods) {
	console.log('굿즈들을 잘 받아오나요??!:', goods); // 데이터 확인
    const gList = document.querySelector('#AllList');
    const adminMain = document.querySelector('.adminMain');
    gList.innerHTML = '';
    
    if (goods.length === 0) {
    	adminMain.innerHTML = '<p>상품이 없습니다.</p>';
    	return;
    }
    
    goods.forEach(store => {	
    	console.log('Item:', store);
    	// 굿즈(상품) 개별 div, p태그 적용
    	const list = document.createElement('div');
    	const gsList = document.createElement('p');

    	// 굿즈(상품) 이름 클릭 시 링크로 이동
        const link = document.createElement('a');
        link.href = `goodsUpdate?gNo=${store.gno}`;
        link.textContent = store.gname; // 팝업스토어 이름에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경
        link.style.textDecoration = 'none'; // 밑줄 제거

        gsList.appendChild(document.createTextNode(` ${store.gno} `));
        gsList.appendChild(link); // p 태그에 a 태그 추가
        gsList.appendChild(document.createTextNode(` ${new Date(store.sellDate).toLocaleDateString()} ${store.gprice}`));
        list.appendChild(gsList);
        gList.appendChild(list);
    });
}

// 검색어에 따라 상품 리스트 출력
//function searchGoods() {
//	var searchText = document.getElementById('adminSearchBox').value;
//	if (searchText) {
//		if (activeTab === 'store') { // 현재 활성화된 탭이 쇼핑몰일 경우
//			fetch(`/admin/gList/?searchGs=${encodeURIComponent(searchText)}`)
//			.then(response => {
//				if (!response.ok) {
//					throw new Error('검색 결과가 출력되지 않았습니다: ' + response.statusText);
//				}
//				return response.json();
//			})
//			.then(data => {
//				GoodsLists(data);
//				console.log(data);
//			})
//			.catch(err => {
//				console.log(err);
//			});
//		} else {
//			alert('현재 활성화된 탭에서는 검색이 불가능합니다.');
//		}
//	} else {
//		alert('검색어를 입력하세요.');
//	}
//}

//**** 회원 영역 ****
// 회원 관리하기 활성화
function activateMemberTab() {
    document.getElementById('registerBtn').style.visibility = 'hidden';  // 회원 관리 탭에서 등록 버튼 숨기기
    // 회원 관리 탭에서는 팝업스토어, 쇼핑 리스트 검색 불가능
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
   
    // 검색 기능 연결
    document.getElementById('searchBTN').onclick = searchMembers;
}

// 회원 관리하기 버튼 클릭 시 회원 리스트 출력
function MemberLists(members) {
	console.log(members);
    const adminMain = document.querySelector('.adminMain');
    adminMain.innerHTML = '';

    if (members.length === 0) {
        adminMain.innerHTML = '<p>회원이 없습니다.</p>';
        return;
    }

    const list = document.createElement('div');

    members.forEach(member => {
        const mList = document.createElement('p');
       
    	// 회원아이디 클릭 시 회원 정보 수정 페이지로 이동
        const link = document.createElement('a');
        link.href = `memberUpdate?userId=${member.userNo}`;
        link.textContent = member.userId; // 회원 아이디에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경	
        link.style.textDecoration = 'none'; // 밑줄 제거
       
        const formattedDate = member.lastLoginDate ?
        		new Date(member.lastLoginDate).toLocaleDateString('ko-KR') : '날짜 없음';
        		
        mList.appendChild(document.createTextNode(` ${member.userNo} `));
        mList.appendChild(link); // p 태그에 a 태그 추가
        mList.appendChild(document.createTextNode(` ${member.userEmail} ${formattedDate} ${member.enabled} ${member.auth}`));
       
        list.appendChild(mList);
    });

    adminMain.appendChild(list);
}

// 검색어에 따라 회원 리스트 출력
function searchMembers() {
    var searchText = document.getElementById('adminSearchBox').value;
    if (searchText) {
        if (activeTab === 'member') { // 현재 활성화된 탭이 팝업스토어일 경우
            fetch(`/admin/mList/?searchMs=${encodeURIComponent(searchText)}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('검색 결과가 출력되지 않았습니다: ' + response.statusText);
                    }
                    return response.json();
                })
                .then(data => {
                	MemberLists(data);
                    console.log(data);
                })
                .catch(err => {
                    console.log(err);
                });
        } else {
            alert('현재 활성화된 탭에서는 검색이 불가능합니다.');
        }
    } else {
        alert('검색어를 입력하세요.');
    }
}

//**** footer 영역 ****
// 문의 리스트 확인 버튼 클릭 시 문의 리스트 확인 페이지로 이동
// const로 변수 설정해서 한 이유는 관리자 페이지를 제외한 페이지에서는
// footer을 쓰지 않지만 admin.js는 전체 페이지에서 사용하므로 
// 변수 설정한 후 기본값이 null이므로 만약에 askList가 null이 아니면
// 즉, askList가 있으면 아래 코드 실행하라는 코드임 (footer 영역 전체에 적용)
const askList = document.querySelector('#askList');
if(askList != null){
	document.querySelector('#askList').addEventListener('click', function() {
		location.href = '/admin/askListCheck'; // JSP 페이지로 이동
	});
}

// 상품 상태 조회 버튼 클릭 시 상품 상태 조회 페이지로 이동
const goodsState = document.querySelector('#goodsState');
if(goodsState !== null){
	document.querySelector('#goodsState').addEventListener('click', checkGoodsState);
	
	function checkGoodsState() {
		window.location.href = '/admin/goodsState'; // JSP 페이지로 이동
	}	
}

// 등록하기 버튼 클릭 시 페이지 이동 함수
function goToPage(url) {
	window.location.href = url;
}

// 등록하기 버튼 클릭 시 이동
const registerBtn = document.querySelector('#registerBtn');
if(registerBtn != null){
	document.getElementById('registerBtn').addEventListener('click', function() {
		if (activeTab === 'popUp') {
			goToPage('/admin/popUpRegister'); // 팝업스토어 관리 탭에서 버튼 클릭 시 팝업 스토어 등록 페이지로 이동 (컨트롤러 경로)
		} else if (activeTab === 'store') {
			goToPage('/admin/goodsRegister'); // 쇼핑몰 관리 탭에서 버튼 클릭 시 상품(굿즈) 등록 페이지로 이동 (컨트롤러 경로)
		}	
	});	
}
