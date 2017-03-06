
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
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
            <input type="hidden" name="action" value="logIn">
			<label class="pad_top">Email: </label>
            <input type="email" name="email" class="email" placeholder="Email" ><br>
            <label class="pad_top">Password: </label>
            <input type="password" class="password" placeholder="password" name="password"><br>
            <!--<input type="submit" value="Login" class="button" ><br>-->
            <input type="submit" value="Login" class="button" >
            <label>
            <input type="checkbox" name="rememberMe" checked>Remember me &nbsp;
            <a href="forgotpassword.jsp">Forgot password?</a></label><br>
            <p>New ? &nbsp; <a href="signup.jsp">Sign up now >></a></p>
        </form>
		</section>
		<%@include file="footer.jsp" %> 
    </body>
</html>
