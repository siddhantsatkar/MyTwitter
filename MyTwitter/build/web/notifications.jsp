<%-- 
    Document   : notifications
    Created on : Aug 6, 2016, 11:51:34 PM
    Author     : Giridhar Anuchand Kathiresan <giridharanuchand@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifications Page</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
    </head>
    <body>
	<%@include file="header.jsp" %>
        <section class="container">
        <h1>Notifications</h1>
  <%--      <c:forEach items ="${notifications}" var ="eachNotification" >
            <c:if test="${eachNotification.notifyType == 'tweet'}">
                <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                <p><img src="uploadfiles/${eachNotification.notifyValue.picfilepath}" width="50" height="50"/> ${eachNotification.notifyValue.fullName} [@${eachNotification.notifyValue.nickName}]: ${eachNotify.notifyValue.date} </p>
                <p>${eachNotification.notifyValue.text} </p>
                </div>
            </c:if>
            <c:if test="${eachNotification.notifyType == 'follow'}">
                <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                    <p>${eachNotification.notifyValue.fullName} followed you</p>
                    <p><img src="uploadfiles/${eachNotification.notifyValue.picfilepath}" width="50" height="50"/></p>
                </div>   
            </c:if>
</c:forEach>  --%>
  <c:forEach items ="${notifyType}" var ="eachNotifyType" >
      
            <c:if test="${notifications[eachNotifyType] == 'tweet'}" >
                <section class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                <p><img src="uploadfiles/${notifications[eachNotifyType].picfilepath}" width="50" height="50"/> ${notifications[eachNotifyType].fullName} [@${notifications[eachNotifyType].nickName}]: ${notifications[eachNotifyType].date} </p>
                <p>${notifications[eachNotifyType].text} </p>
                </section>
            </c:if>
            <c:if test="${notifications[eachNotifyType] == 'follow'}" >
                <section class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                    <p>${notifications[eachNotifyType].fullName} followed you</p>
                    <p><img src="uploadfiles/${notifications[eachNotifyType].picfilepath}" width="50" height="50"/></p>
                </section>   
            </c:if>
</c:forEach> 
	</section> 	
		<%@include file="footer.jsp" %> 
    </body>
</html>
