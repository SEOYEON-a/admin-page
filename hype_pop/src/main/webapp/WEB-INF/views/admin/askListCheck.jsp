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
	
	<form action="#" id="askTypeBox">
		<select id="askType">
			<option value="refund">환불</option>
			<option value="delivery">배송</option>
			<option value="complain">신고</option>
		</select>
	</form>
	
	<div id="askList">
		<span id="askNo">문의 번호</span>
		<span id="askCat">문의 유형</span>
		<span id="askTitle">문의 제목</span>
		<span id="askDate">문의 작성 날짜</span>
		<span id="askYn">답변 여부</span>
	</div>
	
	<!-- page -->
	<div class="page-wrap">
        <ul class="page-nation">
           <c:if test="${pageMaker.prev }">
              <li class="previous">
                 <a href="${pageMaker.startPage-1 }"> &lt; </a>
              </li>
           </c:if>
           <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }" step="1">
              <li>
                 <a href="${num }" class="${pageMaker.cri.pageNum == num ? 'active' : '' }"> ${num } </a>
              </li>
           </c:forEach>
           <c:if test="${pageMaker.next }">
              <li><a href="${pageMaker.endPage+1 }"> &gt; </a></li>
           </c:if>
        </ul>
      </div>
</body>
</html>