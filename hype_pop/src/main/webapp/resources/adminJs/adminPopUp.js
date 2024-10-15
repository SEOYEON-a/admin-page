// 활성화된 탭을 추적하기 위한 변수
{let activeTab = 'popUp';}

//form 객체 가져오기
{const form = document.forms[0];}

// **** 관리자 Header 영역 (공통) ****
// 탭 클릭 시 해당 기능 활성화
document.getElementById('popUpManage').addEventListener('click', function() {
    activeTab = 'popUp'; // 현재 활성화된 탭 업데이트
    localStorage.setItem('activeTab', activeTab);  // 상태 저장
    activatePopUpTab(); // 팝업 관리 기능 활성화
    document.getElementById('registerBtn').style.visibility = 'visible'; // 등록 버튼 보이기
});
document.getElementById('storeManage').addEventListener('click', function() {
    activeTab = 'store'; // 현재 활성화된 탭 업데이트
    localStorage.setItem('activeTab', activeTab);  // 상태 저장
    activateStoreTab(); // 쇼핑몰 관리 기능 활성화
    document.getElementById('registerBtn').style.visibility = 'visible'; // 등록 버튼 보이기
});
document.getElementById('memberManage').addEventListener('click', function() {
    activeTab = 'member'; // 현재 활성화된 탭 업데이트
    localStorage.setItem('activeTab', activeTab);  // 상태 저장
    activateMemberTab(); // 회원 관리 기능 활성화
});

// **** 관리자 페이지 영역 ****
// **** 팝업스토어 영역 ****
// 팝업스토어 관리하기  활성화
function activatePopUpTab() {
    activeTab = 'popUp'; // 현재 활성화된 탭 업데이트
    fetch('/admin/psList/')	
        .then(response => {
            if (!response.ok) {
                throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
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

}

// 팝업스토어 관리하기 버튼 클릭 시 팝업 스토어 리스트 영역 출력
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

    	// 팝업스토어 이름 클릭 시 링크로 이동
        const link = document.createElement('a');
        link.href = `popUpUpdate?psNo=${store.psNo}`;
        link.textContent = store.psName; // 팝업스토어 이름에만 링크 걸리게 설정
        link.style.color = 'black'; // 링크 색상 변경
        link.style.textDecoration = 'none'; // 밑줄 제거

        psList.appendChild(document.createTextNode(` ${store.psNo} `));
        psList.appendChild(link); // p 태그에 a 태그 추가
        psList.appendChild(document.createTextNode(` ${store.psStartDate} ${store.psEndDate}`)); // 나머지 텍스트 추가

        list.appendChild(psList);
    });

    adminMain.appendChild(list);
}

//**** 쇼핑몰(상품) 영역 ****
//쇼핑몰 관리하기 활성화
function activateStoreTab() {
 activeTab = 'store'; // 현재 활성화된 탭 업데이트
 fetch('/admin/gList/')
     .then(response => {
         if (!response.ok) {
             throw new Error('리스트가 출력되지 않았습니다: ' + response.statusText);
         }
         return response.json();
     })
     .then(data => {
         GoodsLists(data);
         console.log(data);
     })
     .catch(err => {
         console.log(err);
     });
}

//쇼핑몰 관리하기 버튼 클릭 시 상품 리스트 영역 출력
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
		
		// 굿즈(상품) 이름 클릭 시 굿즈 정보 수정 페이지로 이동
     const link = document.createElement('a');
     link.href = `goodsUpdate?gNo=${store.gno}`;
     link.textContent = store.gname; // 굿즈(상품) 이름에만 링크 걸리게 설정
     link.style.color = 'black'; // 링크 색상 변경
     link.style.textDecoration = 'none'; // 밑줄 제거
    
     gsList.appendChild(document.createTextNode(` ${store.gno} `));
     gsList.appendChild(link); // p 태그에 a 태그 추가
     gsList.appendChild(document.createTextNode(`${store.sellDate} ${store.gprice}`));

     list.appendChild(gsList);
	});
	
	adminMain.appendChild(list);
}

//**** 회원 영역 ****
//회원 관리하기 활성화
function activateMemberTab() {
	 activeTab = 'member'; // 현재 활성화된 탭 업데이트
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
}

//회원 관리하기 버튼 클릭 시 회원 리스트 출력
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

//등록하기 버튼 클릭 시 팝업스토어 등록
//AdminController의 psRegister로 전송
function popStoreRegister(f) {
//	console.log(f.snsAddress.value);  확인용
	
 if (!f.psLatitude.value) {
     alert('위도를 입력해주세요');
     return false; 
 }
 if (!f.psLongitude.value) {
     alert('경도를 입력해주세요');
     return false; 
 }
 if (!f.storeName.value) {
     alert('이름을 입력해주세요');
     return false; 
 }
 if (!f.storeCat.value) {
     alert('카테고리를 입력해주세요');
     return false; 
 }
 if (!f.startDate.value) {
     alert('시작일을 입력해주세요');
     return false; 
 }
 if (!f.endDate.value) {
     alert('종료일을 입력해주세요');
     return false; 
 }
 if (!f.address.value) {
     alert('주소를 입력해주세요');
     return false; 
 }
 if (!f.snsAddress.value) {
     alert('SNS주소를 입력해주세요');
     return false; 
 }
 if (!f.company.value) {
     alert('주최사 정보를 입력해주세요');
     return false; 
 }
 if (!f.transfer.value) {
     alert('교통편을 입력해주세요');
     return false; 
 }
 if (!f.parking.value) {
     alert('주차장정보를 입력해주세요');
     return false; 
 }
 if (!f.storeExp.value) {
     alert('설명글을 입력해주세요');
     return false; 
 }
 if (!f.uploadFile.files.length) {
     alert('이미지 파일을 업로드해주세요');
     return false; 
 }

 // 모든 검증이 통과한 경우
 const formData = new FormData(f); // 폼 데이터 수집

 // AJAX 요청
 fetch('/admin/psRegister', {
     method: 'POST',
     body: formData
 })
 .then(response => {
     if (!response.ok) {
         throw new Error('등록 실패: ' + response.statusText);
     }
     return response.json();
 })
 .then(data => {
     // 성공적으로 등록된 경우의 처리
     alert('팝업스토어가 등록되었습니다.');
     // 필요한 경우 페이지를 새로 고침하거나 다른 작업 수행
 })
 .catch(error => {
     console.error('Error:', error);
 });
 
 // 모든 검증이 통과한 경우
// return true; 
}


//**** 팝업스토어 등록 페이지 영역 ****
//팝업스토어 이미지 클릭 시 파일(이미지) 첨부 기능
document.querySelector('#popUpimg').addEventListener('click', function() {
	document.querySelector('#fileInput').click(); // 클릭 시 파일 선택 창 열기
});

//document.querySelector('#fileInput').addEventListener('change', function(event) {
//    const files = event.target.files;
//    const uploadedImagesDiv = document.getElementById('uploadedImages');
//
//    // 기존의 이미지 미리보기를 초기화
//    uploadedImagesDiv.innerHTML = '';
//
//    if (files.length > 0) {
//        const formData = new FormData();
//        for (let i = 0; i < files.length; i++) {
//            if (!checkFile(files[i].name, files[i].size)) {
//                return; // 파일 검증 실패 시 종료
//            }
//            formData.append('uploadFile', files[i]);
//
//            // 이미지 미리보기 생성
//            const reader = new FileReader();
//            reader.onload = function(e) {
//                const img = document.createElement('img');
//                img.src = e.target.result; // 파일의 Data URL
//                img.style.width = '300px'; // 이미지 크기 조절
//                img.style.marginRight = '10px'; // 간격 조정
//                uploadedImagesDiv.appendChild(img); // 미리보기 div에 추가
//            }
//            reader.readAsDataURL(files[i]); // 파일을 Data URL로 읽기
//        }
//
//        // fetch 호출을 Promise 체인으로 처리
//        fetch('/admin/uploadAsyncAction', {
//            method: 'POST',
//            body: formData
//        })
//        .then(response => {
//            if (response.ok) {
//                return response.json(); // JSON으로 변환
//            } else {
//                throw new Error('파일 업로드 실패: ' + response.statusText);
//            }
//        })
//        .then(fileInfo => {
//            showUploadFile(fileInfo); // 파일 정보 표시
//            document.querySelector('#fileInput').value = ''; // 파일 선택 초기화
//        })
//        .catch(error => {
//            console.error('Error during upload:', error);
//        });
//    }
//});	

document.querySelector('#fileInput').addEventListener('input', function(event) {
    const files = event.target.files;
    const uploadedImagesDiv = document.getElementById('uploadedImages');

    // 기존의 이미지 미리보기를 초기화
    uploadedImagesDiv.innerHTML = '';

    if (files.length > 0) {
        const formData = new FormData();

        // 새로 선택한 파일만 미리보기 생성
        Array.from(files).forEach(file => {
            if (!checkFile(file.name, file.size)) {
                return; // 파일 검증 실패 시 종료
            }
            formData.append('uploadFile', file);

            // 이미지 미리보기 생성
            const reader = new FileReader();
            reader.onload = function(e) {
                const img = document.createElement('img');
                img.src = e.target.result; // 파일의 Data URL
                img.style.width = '300px'; // 이미지 크기 조절
                img.style.marginRight = '10px'; // 간격 조정
                uploadedImagesDiv.appendChild(img); // 미리보기 div에 추가
            };
            reader.readAsDataURL(file); // 파일을 Data URL로 읽기
        });

        // 파일 업로드 함수 호출
        uploadFiles(formData); // 선택된 파일들 업로드 함수 호출
    }
});

// 업로드된 이미지 클릭 시 파일 선택 창 열기
document.getElementById('uploadedImages').addEventListener('click', function() {
    document.querySelector('#fileInput').click(); // 클릭 시 파일 선택 창 열기
});

// 파일 업로드 함수
function uploadFiles(formData) {
    fetch('/admin/uploadAsyncAction', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            return response.json(); // JSON으로 변환
        } else {
            throw new Error('파일 업로드 실패: ' + response.statusText);
        }
    })
    .then(fileInfo => {
        document.querySelector('#fileInput').value = ''; // 파일 선택 초기화

        // 업로드가 성공적으로 완료되면 '팝업스토어 이미지'텍스트 사라짐
        document.querySelector('#popUpimg').textContent = '';
    })
    .catch(error => {
        console.error('Error during upload:', error);
    });
}


// 사용안하는 코드 (보류)
//document.querySelector('#psRegister').addEventListener('click', async function() {
// const formData = new FormData(document.querySelector('form')); // 전체 폼 데이터 가져오기
//
// try {
//     const registerResponse = await fetch('/admin/psRegister', {
//         method: 'POST',
//         body: formData
//     });
//
//     if (registerResponse.ok) {
//         console.log('팝업스토어 등록 성공');
//         window.location.href = '/admin/psList'; // 성공 후 목록 페이지로 이동
//     } else {
//         console.error('팝업스토어 등록 실패');
//     }
// } catch (error) {
//     console.error('Error during registration:', error);
// }
//});

// 파일 검증
const regex = new RegExp("(.*?)\\.(exe|sh|zip|alz)$");
const MAX_SIZE = 5242880; // 5MB

function checkFile(fileName, fileSize) {
    if (fileSize >= MAX_SIZE) {
        alert("파일 사이즈가 너무 큽니다.");
        return false;
    }
    if (regex.test(fileName)) {
        alert("잘못된 파일 확장자입니다.");
        return false;
    }
    return true;
}