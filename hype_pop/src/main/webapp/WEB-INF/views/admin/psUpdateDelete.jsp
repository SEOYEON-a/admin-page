<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
/* 폼과 입력 필드 스타일 */
form {
    background-color: white; /* 폼 배경색 */
    padding: 20px; /* 폼 내부 여백 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
    margin-bottom: 20px; /* 하단 여백 */
}

/* 입력 박스 스타일 */
input[type="text"] {
    width: 100%; /* 전체 너비 */
    padding: 10px; /* 내부 여백 */
    border: 1px solid #ced4da; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
}

input[type="text"], input[type="file"] {
    width: 100%; /* 전체 너비 */
    padding: 10px; /* 내부 여백 */
    border: 1px solid #ced4da; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
    margin-bottom: 10px; /* 필드 간 여백 축소 */
}

input[type="date"] {
	padding: 10px; /* 내부 여백 */
    border: 1px solid #ced4da; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
}

/* 이미지 업로드 버튼 스타일 */
#popUpimg {
    cursor: pointer;
    padding: 10px 15px;
    background-color: #dc3545;
    color: white;
    border-radius: 5px;
    text-align: center;
    display: inline-block;
    margin-bottom: 10px; /* 필드 간 여백 축소 */
    transition: background-color 0.3s;
}

#popUpimg:hover {
    background-color: #c82333; /* 호버 시 배경색 변경 */
}

/* 필드 섹션 스타일 */
#psLatitude, #psLongitude, #storeName, #storeCat, #startDate, #endDate, 
#address, #snsAddress, #company, #transfer, #parking, #storeExp {
	margin-bottom: 1px; /* 필드 간 여백 */
    padding: 5px;
}

/* 업로드된 이미지 섹션 */
#uploadedImages {
    margin-bottom: 15px;
    display: flex;
    flex-wrap: wrap;
}

/* 버튼 스타일 */
#psCancel, #psDelete, #psUpdate {
    padding: 10px 15px; /* 내부 여백 */
    border-radius: 5px; /* 모서리 둥글게 */
    color: white; /* 글자색 흰색 */
    text-align: center; /* 중앙 정렬 */
    cursor: pointer; /* 커서 변경 */
    border: 3px solid transparent; /* 기본 테두리 제거 */
    margin-top: 10px; /* 상단 여백 축소 */
    display: inline-block;
    transition: background-color 0.3s, color 0.3s, border-color 0.3s; /* 애니메이션 */
}

/* 각각의 버튼에 고유 색상 설정 */
#psCancel {
    background-color: transparent;
    color: #dc3545;
    border-color: #dc3545;
}

#psCancel:hover {
    background-color: #dc3545;
    color: white;
    border-color: #c82333; /* 호버 시 테두리 색상 변경 */
}

#psDelete {
    background-color: transparent;
    color: #dc3545;
    border-color: #dc3545;
}

#psDelete:hover {
    background-color: #dc3545;
    color: white;
    border-color: #c82333; /* 호버 시 테두리 색상 변경 */
}

#psUpdate {
    background-color: transparent;
    color: #dc3545;
    border-color: #dc3545;
}

#psUpdate:hover {
    background-color: #dc3545;
    color: white;
    border-color: #c82333; /* 호버 시 테두리 색상 변경 */
}
</style>
</head>
<body>
	<jsp:include page="layout/adminHeader.jsp"/>
	
	<form id="psUpdateDeleteForm" method="POST" action="/admin/psUpdateDelete" enctype="multipart/form-data">
		<input type="hidden" name="psNo" value="${popStore.psNo}">
		<div id="popUpimg" style="cursor: pointer;">
			팝업스토어 이미지
			<!-- 이미지가 있으면 보여주고, 없으면 '이미지 없음' 텍스트 표시 -->
			<c:choose>
				<c:when test="${not empty popStore.psImg.uploadPath}">
					<img src="${popStore.psImg.uploadPath}/${popStore.psImg.uuid}_${popStore.psImg.fileName}" alt="팝업스토어 이미지" width="200px">
				</c:when>
				<c:otherwise>
					<p>이미지가 없습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- <div id="popUpimg" style="cursor: pointer;">팝업스토어 이미지</div> -->
	    <input type="file" id="imageFile" name="imageFile" style="display: none;">
	    <div id="uploadedImages"></div>
		
		<div id="storeName">팝업스토어 이름 <input type="text" name="psName" value="${popStore.psName}"></div>
		<%-- <div id="storeCat">팝업스토어 이전 카테고리 <input type="text" value="${popStore.psCat}"></div>  --%>
	    <div id="cats">팝업스토어 카테고리
		    <div>
		        <input type="checkbox" name="psCat.healthBeauty" value="1" 
		            <c:if test="${popStore.psCat.healthBeauty == '1'}">checked</c:if>>헬스/뷰티
		        <input type="checkbox" name="psCat.game" value="1" 
		            <c:if test="${popStore.psCat.game == '1'}">checked</c:if>>게임
		        <input type="checkbox" name="psCat.culture" value="1" 
		            <c:if test="${popStore.psCat.culture == '1'}">checked</c:if>>문화
		        <input type="checkbox" name="psCat.shopping" value="1" 
		            <c:if test="${popStore.psCat.shopping == '1'}">checked</c:if>>쇼핑
		        <input type="checkbox" name="psCat.supply" value="1" 
		            <c:if test="${popStore.psCat.supply == '1'}">checked</c:if>>문구
		        <input type="checkbox" name="psCat.kids" value="1" 
		            <c:if test="${popStore.psCat.kids == '1'}">checked</c:if>>키즈
		        <input type="checkbox" name="psCat.design" value="1" 
		            <c:if test="${popStore.psCat.design == '1'}">checked</c:if>>디자인
		        <input type="checkbox" name="psCat.foods" value="1" 
		            <c:if test="${popStore.psCat.foods == '1'}">checked</c:if>>식품
		        <input type="checkbox" name="psCat.interior" value="1" 
		            <c:if test="${popStore.psCat.interior == '1'}">checked</c:if>>인테리어
		        <input type="checkbox" name="psCat.policy" value="1" 
		            <c:if test="${popStore.psCat.policy == '1'}">checked</c:if>>정책
		        <input type="checkbox" name="psCat.character" value="1" 
		            <c:if test="${popStore.psCat.character == '1'}">checked</c:if>>캐릭터
		        <input type="checkbox" name="psCat.experience" value="1" 
		            <c:if test="${popStore.psCat.experience == '1'}">checked</c:if>>체험
		        <input type="checkbox" name="psCat.collaboration" value="1" 
		            <c:if test="${popStore.psCat.collaboration == '1'}">checked</c:if>>콜라보
		        <input type="checkbox" name="psCat.entertainment" value="1" 
		            <c:if test="${popStore.psCat.entertainment == '1'}">checked</c:if>>방송
		    </div>
		</div>
		<div id="startDate">시작일 <br><input type="date" name="psStartDate" value="${popStore.psStartDate}"></div>
		<div id="endDate">종료일 <br><input type="date" name="psEndDate" value="${popStore.psEndDate}"></div>
		<div id="address">주소 <input type="text" name="psAddress" value="${popStore.psAddress}"></div>
		<div id="snsAddress">SNS 주소 <input type="text" name="snsAd" value="${popStore.snsAd}"></div>
		<div id="company">주최사 정보 <input type="text" name="comInfo" value="${popStore.comInfo}"></div>
		<div id="transfer">교통편 <input type="text" name="transInfo" value="${popStore.transInfo}"></div>
		<div id="parking">주차장 정보 <input type="text" name="parkingInfo" value="${popStore.parkingInfo}"></div>
		<div id="storeExp">설명글 <input type="text" name="psExp" value="${popStore.psExp}"></div>
	</form>

	<button type="button" id="psCancel" >취소 및 리스트로 돌아가기</button>	
    <button type="button" id="psDelete" >삭제</button>
    <button type="button" id="psUpdate" onclick="popStoreUpdate();">수정완료</button>
	
<script type="text/javascript" src="/resources/adminJs/admin.js"></script>  
<script type="text/javascript" src="/resources/adminJs/adminPopUp.js"></script>  
</body>
</html>