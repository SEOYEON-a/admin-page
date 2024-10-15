<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="layout/adminHeader.jsp"/>
	<form method="POST">
		<div id="popUpimg" style="cursor: pointer;">팝업스토어 이미지</div>
		<input type="file" id="fileInput" style="display: none;" multiple>
		<div id="uploadedImages"></div>
				
		<div id="latitude">위도 <input type="text" name="psLatitude" value="${vo.latitude}"></div>
		<div id="longitude">경도 <input type="text" name="psLongitude" value="${vo.longitude}"></div>
		
		<div id="psName">팝업스토어 이름 <input type="text" name="storeName" value="${vo.psName}"></div>
		<div id="psCat">팝업스토어 카테고리 <input type="text" name="storeCat" value="${vo.psCat}"></div> 
		<div id="psStartDate">시작일 <input type="text" name="startDate" value="${vo.psStartDate}"></div>
		<div id="psEndDate">종료일 <input type="text" name="endDate" value="${vo.psEndDate}"></div>
		<div id="psAddress">주소 <input type="text" name="address" value="${vo.psAddress}"></div>
		<div id="snsAd">SNS 주소 <input type="text" name="snsAddress" value="${vo.snsAd}"></div>
		<div id="comInfo">주최사 정보 <input type="text" name="company" value="${vo.comInfo}"></div>
		<div id="transInfo">교통편 <input type="text" name="transfer" value="${vo.transInfo}"></div>
		<div id="parkingInfo">주차장 정보 <input type="text" name="parking" value="${vo.parkinginfo}"></div>
		<div id="psExp">설명글 <input type="text" name="storeExp" value="${vo.psExp}">
	</form>
	
	<div id="psCancel">취소 및 리스트로 돌아가기</div>	
	<div id="psUpdate">수정완료</div>	
	
	
</body>
</html>