<%-- 
    Document   : forgotpassword
    Created on : Jul 25, 2016, 2:34:30 AM
    Author     : Giridhar Anuchand Kathiresan <giridharanuchand@gmail.com>
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot password Page</title>
        <link rel="stylesheet" href="styles/logincss.css" type="text/css"/>
    </head>
    <body>
	<%@include file="header.jsp" %>
        <h1>Login In</h1>
		<section class="nav">
        <c:if test="${message != null}">
            <p><i>${message}</i></p>
        </c:if>
        <form action="membershipServlet" method="post">
            <input type="hidden" name="action" value="forgot">
			<label class="pad_top">Email: </label>
            <input type="email" name="email" class="email" placeholder="Email" ><br>
      
            <input type="submit" value="Sendemail" class="button" >
            
        </form>
		</section>
		<%@include file="footer.jsp" %> 
    </body>
</html>

