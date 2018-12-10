<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: stas
  Date: 8/20/18
  Time: 2:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my" uri="http://mycompany.com" %>

<%--<fmt:setLocale value="ru"/>--%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title>Главная страница</title>
</head>
<body>
<div class="container">
    <my:menu/>
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1><my:locMes key="login.jsp.title" localeString="${pageContext.response.locale}"/></h1>
        </div>
    </div>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <div class="row">
                <div class="col-6">
                    <h3><my:locMes key="login.jsp.login" localeString="${pageContext.response.locale}"/></h3>
                </div>
                <div class="col-6">
                    <h3><my:locMes key="login.jsp.registration" localeString="${pageContext.response.locale}"/></h3>
                </div>
            </div>
            <div class="row">
                <form class="col-6" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="exampleInputEmail1"><my:locMes key="login.jsp.login.email" localeString="${pageContext.response.locale}"/></label>
                        <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="<my:locMes key="login.jsp.login.email.hint" localeString="${pageContext.response.locale}"/>">
                        <small id="emailHelp" class="form-text text-muted"><my:locMes key="login.jsp.login.email.description" localeString="${pageContext.response.locale}"/></small>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputPassword1"><my:locMes key="login.jsp.login.password" localeString="${pageContext.response.locale}"/></label>
                        <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="<my:locMes key="login.jsp.login.password.hint" localeString="${pageContext.response.locale}"/>">
                    </div>
                    <button type="submit" class="btn btn-primary"><my:locMes key="login.jsp.login.button" localeString="${pageContext.response.locale}"/></button>
                </form>
                <form class="col-6" action="${pageContext.request.contextPath}/register" method="post">
                    <div class="form-group">
                        <label for="registrationEmail"><my:locMes key="login.jsp.registration.email" localeString="${pageContext.response.locale}"/></label>
                        <input name="email" type="email" class="form-control" id="registrationEmail" aria-describedby="emailHelp" placeholder="<my:locMes key="login.jsp.registration.email.hint" localeString="${pageContext.response.locale}"/>">
                        <small id="registrationEmailHelp" class="form-text text-muted"><my:locMes key="login.jsp.registration.email.description" localeString="${pageContext.response.locale}"/></small>
                    </div>
                    <div class="form-group">
                        <label for="registrationPassword"><my:locMes key="login.jsp.registration.password" localeString="${pageContext.response.locale}"/></label>
                        <input name="password" type="password" class="form-control" id="registrationPassword" placeholder="<my:locMes key="login.jsp.registration.password.hint" localeString="${pageContext.response.locale}"/>">
                    </div>
                    <div class="form-group">
                        <label for="registrationName"><my:locMes key="login.jsp.registration.name" localeString="${pageContext.response.locale}"/></label>
                        <input name="name" type="text" class="form-control" id="registrationName" placeholder="<my:locMes key="login.jsp.registration.name.hint" localeString="${pageContext.response.locale}"/>">
                    </div>
                    <div class="form-group">
                        <label for="registrationSurname"><my:locMes key="login.jsp.registration.surname" localeString="${pageContext.response.locale}"/></label>
                        <input name="surname" type="text" class="form-control" id="registrationSurname" placeholder="<my:locMes key="login.jsp.registration.surname.hint" localeString="${pageContext.response.locale}"/>">
                    </div>
                    <div class="form-group">
                        <label for="registrationAge"><my:locMes key="login.jsp.registration.age" localeString="${pageContext.response.locale}"/></label>
                        <input name="age" type="number" class="form-control" id="registrationAge" placeholder="<my:locMes key="login.jsp.registration.age.hint" localeString="${pageContext.response.locale}"/>">
                    </div>
                    <button type="submit" class="btn btn-primary"><my:locMes key="login.jsp.registration.button" localeString="${pageContext.response.locale}"/></button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div class="row justify-content-md-center">
                <form class="col-3" action="${pageContext.request.contextPath}/rooms" method="get">
                    <button type="submit" class="btn btn-primary">Перейти к выбору номеров</button>
                </form>
                <form class="col-3" action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.button.logout" localeString="${pageContext.response.locale}"/></button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
    <my:exception />
</div>

<script type="text/javascript" src="/libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="/libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
