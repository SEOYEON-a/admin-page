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
	
	<form method="POST" action="/admin/psRegister" onsubmit="return popStoreRegister(this);">
	    <div id="popUpimg" style="cursor: pointer;">팝업스토어 이미지</div>
	    <input type="file" id="fileInput" name="uploadFile" style="display: none;" multiple>
	    <div id="uploadedImages"></div>
	    
	    <div id="latitude">위도 <input type="text" name="psLatitude"></div>
	    <div id="longitude">경도 <input type="text" name="psLongitude"></div>
	    
	    <div id="psName">팝업스토어 이름 <input type="text" name="storeName"></div>
	    <div id="psCat">팝업스토어 카테고리 <input type="text" name="storeCat"></div> 
	    <div id="psStartDate">시작일 <input type="text" name="startDate"></sdiv>
	    <div id="psEndDate">종료일 <input type="text" name="endDate"></div>
	    <div id="psAddress">주소 <input type="text" name="address"></div>
	    <div id="snsAd">SNS 주소 <input type="text" name="snsAddress"></div>
	    <div id="comInfo">주최사 정보 <input type="text" name="company"></div>
	    <div id="transInfo">교통편 <input type="text" name="transfer"></div>
	    <div id="parkingInfo">주차장 정보 <input type="text" name="parking"></div>
	    <div id="psExp">설명글 <input type="text" name="storeExp"></div>
	    
	    <div id="psRegister">
	        <button type="submit" id="psRegisterBtn">등록하기</button>
	    </div>
	</form>
	
	
<script type="text/javascript" src="/resources/adminJs/admin.js"></script>  
<script type="text/javascript" src="/resources/adminJs/adminPopUp.js"></script>  
  
</body>
</html>
