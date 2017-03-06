

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignUp Page</title>
        <link rel="stylesheet" href="styles/signupcss.css" type="text/css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
    <%--<c:choose>--%>
        <%--<c:when test="${theUser != null}">--%>
        
            <c:if test="${theUser != null}">
            <section class="container">
                <header>
                    <h1><span>Profile Update</span> Please edit your details</h1>
            </header> 
            </section>    
            <section class="form">
            <!-- Date of birth validation error msg -->
            <c:if test="${message != null}">
                <p><i>${message}</i></p>
            </c:if>
        <form action="membershipServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="update">
            <p class="contact"><label for="name">Full Name: </label></p>
            <input type="text" name="fullName" value="${theUser.fullName}"><br>
            
            <p class="contact"><label for="email">Email: </label></p>
            <input type="email" name="email" value="${theUser.email}"readonly><br>
            
            <fieldset>
                 <label>Birthday</label>
                 <label class="day">
            <select class="select-style" name="dob_Day" value="${theUser.dobDay}">
           <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
            </select>
                 </label>
                 <label class="month">
            <select class="select-style" name="dob_Month" value="${theUser.dobMonth}">
            <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
                 </label>
                 <label class="year">
            <select name="dob_Year" value="${theUser.dobYear}" class="select-style">
            <option value="1980">1980</option>
                <option value="1981">1981</option>
                <option value="1982">1982</option>
                <option value="1983">1983</option>
                <option value="1984">1984</option>
                <option value="1985">1985</option>
                <option value="1986">1986</option>
                <option value="1987">1987</option>
                <option value="1988">1988</option>
                <option value="1989">1989</option>
                <option value="1990">1990</option>
                <option value="1991">1991</option>
                <option value="1992">1992</option>
                <option value="1993">1993</option>
                <option value="1994">1994</option>
                <option value="1995">1995</option>
                <option value="1996">1996</option>
                <option value="1997">1997</option>
                <option value="1998">1998</option>
                <option value="1999">1999</option>
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
                <option value="2005">2005</option>
                <option value="2006">2006</option>
                <option value="2007">2007</option>
                <option value="2008">2008</option>
                <option value="2009">2009</option>
                <option value="2010">2010</option>
                <option value="2011">2011</option>
                <option value="2012">2012</option>
                <option value="2013">2013</option>
                <option value="2014">2014</option>
                <option value="2015">2015</option>
                <option value="2016">2016</option>
            </select>
                 </label>
            </fieldset>
            <p class="contact"><label for="Nickname">Nick Name: </label></p>
            <input type="text" tabindex="2" name="nickName" value="${theUser.nickName}" readonly><br>
            <p class="contact"><label for="password">Password: </label></p>
            <input type="password" name="password"  value="${theUser.password}"><br>
            <p class="contact"><label for="profilePicture">Profile Picture: </label></p>
            <input type="file" name="file"  value="${theUser.picfilepath}"><br>
            <!--<input type="submit" value="Upload" />-->
<!--            <form method="post" action="Profilepicservlet"
        enctype="multipart/form-data">
                     <img width="240" height="240"/>
        <input type="file" name="file" size="60" /><br />
        <br /> <input type="submit" value="Upload" />
        
                  
             <img src="uploadfiles/${theUser.picfilepath}" width="240" height="240"/>
            </form>-->
    
            <input class="button" name="submit" id="submit" tabindex="5" type="submit" value="update">
        </form>
            </section>
        </c:if>
        <c:if test="${theUser == null}">
            <section class="container">
                <header>
                    <h1><span>Sign Up</span>To join Twitter, Please enter your name and email address below.</h1>
            </header> 
            </section>
            <section class="form">
                <!-- date of birth validation error msg -->
                <c:if test="${message != null}">
                    <p><i>${message}</i></p>
                </c:if>
        <form action="membershipServlet" method="post">
            <input type="hidden" name="action" value="signUp">
            <p class="contact"><label for="name">Full Name: </label></p>
            <input type="text" placeholder="First and last name" required="" tabindex="1" name="fullName" required/>
            <p class="contact"><label for="email">Email: </label></p>
            <input type="email" name="email" required/><br>
            <fieldset>
                 <label>Birthday</label>
                 <label class="day">
            <select class="select-style" name="dob_Day">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
                <option value="13">13</option>
                <option value="14">14</option>
                <option value="15">15</option>
                <option value="16">16</option>
                <option value="17">17</option>
                <option value="18">18</option>
                <option value="19">19</option>
                <option value="20">20</option>
                <option value="21">21</option>
                <option value="22">22</option>
                <option value="23">23</option>
                <option value="24">24</option>
                <option value="25">25</option>
                <option value="26">26</option>
                <option value="27">27</option>
                <option value="28">28</option>
                <option value="29">29</option>
                <option value="30">30</option>
                <option value="31">31</option>
            </select>
                 </label>
                 <label class="month">
            <select class="select-style" name="dob_Month">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>
            </select>
                 </label>
                 <label class="year">
            <select name="dob_Year" class="select-style">
                <option value="1980">1980</option>
                <option value="1981">1981</option>
                <option value="1982">1982</option>
                <option value="1983">1983</option>
                <option value="1984">1984</option>
                <option value="1985">1985</option>
                <option value="1986">1986</option>
                <option value="1987">1987</option>
                <option value="1988">1988</option>
                <option value="1989">1989</option>
                <option value="1990">1990</option>
                <option value="1991">1991</option>
                <option value="1992">1992</option>
                <option value="1993">1993</option>
                <option value="1994">1994</option>
                <option value="1995">1995</option>
                <option value="1996">1996</option>
                <option value="1997">1997</option>
                <option value="1998">1998</option>
                <option value="1999">1999</option>
                <option value="2000">2000</option>
                <option value="2001">2001</option>
                <option value="2002">2002</option>
                <option value="2003">2003</option>
                <option value="2004">2004</option>
                <option value="2005">2005</option>
                <option value="2006">2006</option>
                <option value="2007">2007</option>
                <option value="2008">2008</option>
                <option value="2009">2009</option>
                <option value="2010">2010</option>
                <option value="2011">2011</option>
                <option value="2012">2012</option>
                <option value="2013">2013</option>
                <option value="2014">2014</option>
                <option value="2015">2015</option>
                <option value="2016">2016</option>
            </select>
                 </label>
            </fieldset>
            <p class="contact"><label for="Nickname">Nick Name: </label></p>
            <input type="text" tabindex="2" name="nickName" required/><br>
            <p class="contact"><label for="password">Password: </label></p>
            <input type="password" name="password"  required/><br>
            <input class="button" name="submit" id="submit" tabindex="5" type="submit" value="Sign Up">
            <input type="reset" value="Reset" class="button" tabindex="5">
        </form>
                </section>
        </c:if>
      <%--</c:choose>--%>
      <%@include file="footer.jsp" %> 
    </body>
</html>
