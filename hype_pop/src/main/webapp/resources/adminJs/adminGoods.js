// *** 상품 상태 조회 페이지 영역 *** 
//function formatDate(dateString) {
//    const date = new Date(dateString);
//    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
//    return date.toLocaleDateString('ko-KR', options); // 한국 날짜 형식으로 변환
//}
//
//function fetchPurchaseList() {
//    fetch('/admin/getPurchaseList')
//        .then(response => {
//            if (!response.ok) {
//                throw new Error('Network response was not ok: ' + response.statusText);
//            }
//            return response.json();
//        })
//        .then(data => {
//        	
//            renderPurchaseList(data); // 데이터 렌더링 함수 호출
//        })
//        .catch(error => {
//            console.error('Error fetching purchase list:', error);
//        });
//}
//
//function renderPurchaseList(purchaseList) {
//    const goodsListBody = document.getElementById('goodsListBody');
//    goodsListBody.innerHTML = ''; // 기존 내용 초기화
//
//    purchaseList.forEach(purchase => {
//        const row = document.createElement('tr');
//        row.innerHTML = `
//            <td>${purchase.gno}</td>
//            <td>${purchase.gamount}</td>
//            <td>${purchase.gprice}</td>
//            <td>${formatDate(purchase.gbuyDate)}</td> <!-- 날짜 포맷 적용 -->
//            <td>
//                <select id="goodsSts">
//                    <option value="구매완료">구매완료</option>
//                    <option value="배송중">배송중</option>
//                    <option value="배송완료">배송완료</option>
//                </select>
//            </td>
//        `;
//        goodsListBody.appendChild(row);
//    });
//}
//
//// 페이지 로드 후 호출
//window.onload = fetchPurchaseList;

window.onload = function() {
    // 예시로 두 카테고리와 이미지 파일명을 설정
    const category1 = "goodsBanner"; // 굿즈 배너 이미지
    const category2 = "goodsDetail"; // 굿즈 상세 이미지
    
    // 서버에서 파일명을 가져옴 (popStore 객체에서 가져오는 방식)
    const fileName1 = document.querySelector('input[name="beforeFileName1"]').value;
    const fileName2 = document.querySelector('input[name="beforeFileName2"]').value;
    
    // fetchImage 함수 호출하여 두 이미지를 각각 불러오기
    fetchImage(category1, fileName1, "beforeImg1");
    fetchImage(category2, fileName2, "beforeImg2");
//    console.log(fileName1, fileName2);
};

function fetchImage(category, fileName, imgElementId) {
    const imageUrl = `/admin/getImages/${fileName}/${category}/`;    

    fetch(imageUrl)
        .then(response => {
            if (response.ok) {
                return response.blob(); // 이미지 파일을 blob 형태로 받음
            } else {
                throw new Error('이미지를 불러오는 데 실패했습니다.');
            }
        })
        .then(blob => {
            const imageObjectURL = URL.createObjectURL(blob);
            document.getElementById(imgElementId).src = imageObjectURL;
//            document.getElementById("beforeImg1").src = imageObjectURL; // 해당 ID의 이미지에 표시
//            document.getElementById("beforeImg2").src = imageObjectURL; // 해당 ID의 이미지에 표시
        })
        .catch(error => {
            console.error('이미지 로딩 실패:', error);
        });
}


// *** 상품(굿즈) 등록 페이지 영역 ***
// 등록하기 버튼 클릭 시 상품(굿즈) 등록
// psNo 가져오기
document.addEventListener('DOMContentLoaded', function() {
    const storeList = document.getElementById('storeList');
    if (storeList) {
        storeList.addEventListener('change', setStorePsNo);
    }
});

function setStorePsNo() {
    const storeList = document.getElementById("storeList");
    const selectedOption = storeList.options[storeList.selectedIndex];
    console.log('모든 팝업스토어 출력: ', selectedOption); // 선택된 psNo 출력
    const psNo = selectedOption.getAttribute("data-psno"); // data-psno 속성에서 psNo 가져오기
    console.log('선택된 psNo 출력: ', psNo); // 선택된 psNo 출력
    document.querySelector('input[name="psno"]').value = psNo;  // psNo를 readonly input에 설정
}

// 파일 검증
const regex = new RegExp("(.*?)\\.(jpg|jpeg|png|gif)$");
const MAX_SIZE = 5242880; // 5MB

function checkFile(fileName, fileSize) {
    if (fileSize >= MAX_SIZE) {
        alert("파일 사이즈가 너무 큽니다.");
        return false;
    }
    if (!regex.test(fileName)) {
        alert("잘못된 파일 확장자입니다.");
        return false;
    }
    return true;
}

// 배너 이미지 클릭 시 파일 선택
document.querySelector('#gBannerImg').addEventListener('click', function() {
    document.querySelector('#gBannerImageFile').click(); // 클릭 시 파일 선택 창 열기
});

// 상세 이미지 클릭 시 파일 선택
document.querySelector('#gDetailImg').addEventListener('click', function() {
    document.querySelector('#gDetailImageFile').click(); // 클릭 시 파일 선택 창 열기
});

// 배너 이미지 파일 미리보기 및 검증
document.querySelector('#gBannerImageFile').addEventListener('input', function(event) {
    const files = event.target.files;
    const uploadedBannerImagesDiv = document.getElementById('uploadedBannerImages');

    // 기존 이미지 미리보기를 초기화
    uploadedBannerImagesDiv.innerHTML = '';

    // 최대 한 개의 배너 이미지 파일만 선택하도록 제한
    Array.from(files).slice(0, 1).forEach((file) => {
        if (!checkFile(file.name, file.size)) {
            return; // 파일 검증 실패 시 종료
        }

        // 이미지 미리보기 생성
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result; // 파일의 Data URL
            img.style.width = '150px'; // 이미지 크기 조절
            img.style.marginRight = '10px'; // 간격 조정
            uploadedBannerImagesDiv.appendChild(img); // 미리보기 div에 추가
        };
        reader.readAsDataURL(file); // 파일을 Data URL로 읽기
    });
});

// 상세 이미지 파일 미리보기 및 검증
document.querySelector('#gDetailImageFile').addEventListener('input', function(event) {
    const files = event.target.files;
    const uploadedDetailImagesDiv = document.getElementById('uploadedDetailImages');

    // 기존 이미지 미리보기를 초기화
    uploadedDetailImagesDiv.innerHTML = '';

    // 최대 두 개의 상세 이미지 파일만 선택하도록 제한
    Array.from(files).slice(0, 2).forEach((file) => {
        if (!checkFile(file.name, file.size)) {
            return; // 파일 검증 실패 시 종료
        }

        // 이미지 미리보기 생성
        const reader = new FileReader();
        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result; // 파일의 Data URL
            img.style.width = '150px'; // 이미지 크기 조절
            img.style.marginRight = '10px'; // 간격 조정
            uploadedDetailImagesDiv.appendChild(img); // 미리보기 div에 추가
        };
        reader.readAsDataURL(file); // 파일을 Data URL로 읽기
    });
});

// 상품(굿즈) 등록 버튼 클릭 이벤트
function goodsRegister() {
	const form = document.forms[0];
	
	// FormData 객체 생성
    const formData = new FormData(form);

    // 예외처리
    // 체크박스
    const checkboxes = form.querySelectorAll('input[type="checkbox"][name^="gcat"]');
    const selectedCategories = Array.from(checkboxes).filter(checkbox => checkbox.checked);
    
    const gBannerImageFile = document.getElementById('gBannerImageFile');
    const gDetailImageFile = document.getElementById('gDetailImageFile');

    
    if (gBannerImageFile.files.length === 0) {
    	alert('상품 배너 이미지를 입력해주세요');
    	return;
    }
    if (gDetailImageFile.files.length === 0) {
    	alert('상품 상세 이미지를 입력해주세요');
    	return;
    }
    if (selectedCategories.length === 0) {
        alert('최소 한 개의 카테고리를 선택해야 합니다.');
        return;
    }
    if (selectedCategories.length > 3) {
        alert('최대 세 개의 카테고리만 선택할 수 있습니다.');
        return;
    }
    if (!form.gname.value) {
        alert('상품 이름을 입력해주세요');
        return;
    }
    if (!form.gprice.value) {
        alert('상품 가격을 입력해주세요');
        return;
    }
    if (!form.sellDate.value) {
        alert('상품 판매 종료일을 입력해주세요');
        return;
    }
    if (!form.gexp.value) {
        alert('설명글을 입력해주세요');
        return;
    }
	    
    // 폼 제출
    form.submit();
}

// **** 상품(굿즈) 수정/삭제 페이지 영역 ****
// 수정하기 버튼 클릭 시 업데이트
// 신규 이미지 선택 시 미리보기
document.getElementById("gBannerImageFile").addEventListener("change", function(event) {
 const file = event.target.files[0];
 if (file) {
     const reader = new FileReader();
     reader.onload = function(e) {
    	 const previewContainer = document.getElementById("uploadedBannerImages");
         previewContainer.innerHTML = ''; // 이전 미리보기 내용 초기화
         const img = document.createElement('img');
         img.src = e.target.result;
         img.width = 400; // 이미지 크기 조정 (필요시)
         img.height = 500; // 이미지 크기 조정 (필요시)
         previewContainer.appendChild(img); // 새로운 이미지 요소 추가
     };
     reader.readAsDataURL(file);
 }
});
document.getElementById("gDetailImageFile").addEventListener("change", function(event) {
	const file = event.target.files[0];
	if (file) {
		const reader = new FileReader();
        reader.onload = function(e) {
            const previewContainer = document.getElementById("uploadedDetailImages");
            previewContainer.innerHTML = ''; // 이전 미리보기 내용 초기화
            const img = document.createElement('img');
            img.src = e.target.result;
            img.width = 400; // 이미지 크기 조정 (필요시)
            img.height = 500; // 이미지 크기 조정 (필요시)
            previewContainer.appendChild(img); // 새로운 이미지 요소 추가
        };
		reader.readAsDataURL(file);
	}
});

function goodsUpdate() {
	const f = document.forms[0];
	
	const formData = new FormData(f);
		
    const gBannerImageFile = document.getElementById('gBannerImageFile');
    const gDetailImageFile = document.getElementById('gDetailImageFile');
    
	// 예외처리
    const checkboxes = f.querySelectorAll('input[type="checkbox"][name^="gcat"]');
    const selectedCategories = Array.from(checkboxes).filter(checkbox => checkbox.checked);
    if (selectedCategories.length === 0) {
        alert('최소 한 개의 카테고리를 선택해야 합니다.');
        return;
    }
    if (selectedCategories.length > 3) {
        alert('최대 세 개의 카테고리만 선택할 수 있습니다.');
        return;
    }
    
    if (gBannerImageFile.files.length === 0) {
        alert('상품 배너 이미지를 입력해주세요');
        return;  // 배너 이미지가 없으면 폼 제출하지 않음
    }
    
    if (gDetailImageFile.files.length === 0) {
        alert('상품 상세 이미지를 입력해주세요');
        return;  // 상세 이미지가 없으면 폼 제출하지 않음
    }
    
    if (f.gname.value == '') {
        alert('상품 이름을 입력해주세요');
        return;
    }
    if (f.gprice.value == '') {
        alert('상품 가격을 입력해주세요');
        return;
    }
    if (f.sellDate.value == '') {
        alert('상품 판매 종료일을 입력해주세요');
        return;
    }
    if (f.gexp.value == '') {
        alert('설명글을 입력해주세요');
        return;
    }
    
    document.getElementById("goodsForm").action = "/admin/gUpdate";  // 수정 요청 경로
    document.getElementById("goodsForm").submit();  // 폼 제출
//    f.submit();
}

// 삭제하기 버튼 클릭 시 업데이트
function goodsDelete() {
	if (confirm("정말 삭제하시겠습니까?")) {
        // 삭제 작업을 위한 폼 액션 설정
        document.getElementById("goodsForm").action = "/admin/gDelete";  // 삭제 요청 경로
        document.getElementById("goodsForm").submit();  // 폼 제출
    }
}
// 취소 및 리스트로 돌아가기 버튼 클릭시 메인페이지로 이동
function backtoGList() {
	window.location.href = "/admin/adminPage";
}

