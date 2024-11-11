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

input[type="text"], input[type="date"], input[type="file"] {
    width: 100%; /* 전체 너비 */
    padding: 10px; /* 내부 여백 */
    border: 1px solid #ced4da; /* 테두리 */
    border-radius: 5px; /* 모서리 둥글게 */
    box-sizing: border-box; /* 패딩과 테두리 포함 */
    margin-bottom: 10px; /* 필드 간 여백 축소 */
}

/* 이미지 업로드 버튼 스타일 */
#exhimg {
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

#exhimg:hover {
    background-color: #c82333; /* 호버 시 배경색 변경 */
}

/* 필드 섹션 스타일 */
#exhName, #address, #startDate, #endDate, 
#watchTime, #watchAge, #price, #exhExp {
    padding: 2px;
}

/* 업로드된 이미지 섹션 */
#uploadedImages {
    margin-bottom: 15px;
    display: flex;
    flex-wrap: wrap;
}

/* 버튼 스타일 */
#exhCancel, #exhDelete, #exhUpdate {
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
#exhCancel {
    background-color: transparent;
    color: #dc3545;
    border-color: #dc3545;
}

#exhCancel:hover {
    background-color: #dc3545;
    color: white;
    border-color: #c82333; /* 호버 시 테두리 색상 변경 */
}

#exhDelete {
    background-color: transparent;
    color: #dc3545;
    border-color: #dc3545;
}

#exhDelete:hover {
    background-color: #dc3545;
    color: white;
    border-color: #c82333; /* 호버 시 테두리 색상 변경 */
}

#exhUpdate {
    background-color: transparent;
    color: #dc3545;
    border-color: #dc3545;
}

#exhUpdate:hover {
    background-color: #dc3545;
    color: white;
    border-color: #c82333; /* 호버 시 테두리 색상 변경 */
}
</style>
</head>
<body>
	<jsp:include page="layout/adminHeader.jsp"/>
	
	<form id="exhUpdateDeleteForm" method="POST" action="/admin/exhUpdateDelete">
		<input type="hidden" name="psNo" value="${exh.exhNo}">
		<div id="exhimg" style="cursor: pointer;">전시회 이미지</div>
	    <input type="file" id="imageFile" name="imageFile" style="display: none;">
	    <div id="uploadedImages"></div>
		
		<div id="exhName">전시회 이름 <input type="text" name="psName" value="${exh.exhName}"></div>
		<div id="address">주소 <input type="text" name="psAddress" value="${exh.exhLocation}"></div>
		<div id="startDate">시작일 <input type="date" name="psStartDate" value="${exh.exhStartDate}"></div>
		<div id="endDate">종료일 <input type="date" name="psEndDate" value="${exh.exhEndDate}"></div>
		<div id="watchTime">러닝타임 <input type="text" name="snsAd" value="${exh.exhWatchTime}"></div>
		<div id="watchAge">연령가 <input type="text" name="comInfo" value="${exh.exhWatchAge}"></div>
		<div id="price">가격 <input type="text" name="transInfo" value="${exh.exhPrice}"></div>
		<div id="exhExp">설명글 <input type="text" name="psExp" value="${exh.exhInfo}"></div>
	</form>

	<button type="button" id="exhCancel" >취소 및 리스트로 돌아가기</button>	
    <button type="button" id="exhDelete" >삭제</button>
    <button type="button" id="exhUpdate" onclick="exhUpdate();">수정완료</button>
	
<script type="text/javascript" src="/resources/adminJs/admin.js"></script>  
</body>
</html>