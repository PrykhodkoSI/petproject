<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 24.08.2018
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title>Ошибка</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1>Ошибка</h1>
        </div>
    </div>
    <div class="row  justify-content-md-center">
        <c:if test="${not empty sessionScope.exception}">
            <div class="row">
                <c:set var="exception" value="${sessionScope.exception}"/>
                <div class="col-2-offset-2">
                    <h3 class="text-danger">Error:</h3>
                </div>
                <div class="col-6">
                    <h3 class="text-danger">${exception.key}</h3>
                </div>
            </div>
        </c:if>
    </div>
    <div class="row  justify-content-md-center">
        <form class="col-md-auto" action="${pageContext.request.contextPath}/" method="post">
            <button type="submit" class="btn btn-primary">To Main Page</button>
        </form>
    </div>
</div>

<script type="text/javascript" src="/libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="/libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>