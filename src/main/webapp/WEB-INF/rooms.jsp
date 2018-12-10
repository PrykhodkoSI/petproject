<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 29.08.2018
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://mycompany.com" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title><my:locMes key="rooms.jsp.title" localeString="${pageContext.response.locale}"/></title>
</head>
<body>
<div class="container">
    <my:menu/>
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1><my:locMes key="rooms.jsp.title" localeString="${pageContext.response.locale}"/></h1>
        </div>
    </div>
    <c:if test="${not empty rooms}">
        <form class="col-6" action="${pageContext.request.contextPath}/rooms" method="get">
            <div class="row">
                <div class="col-4">
                    <select name="sort" class="form-control" id="sortOptions">
                        <c:forEach var="sortOption" items="${sortOptions}" >
                            <option value="${sortOption}">${sortOption}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-4">
                    <select name="order" class="form-control" id="sortOrders">
                        <c:forEach var="sortOrder" items="${sortOrders}" >
                            <option value="${sortOrder}">${sortOrder}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-4">
                    <button type="submit" class="btn btn-primary"><my:locMes key="rooms.jsp.button.sort" localeString="${pageContext.response.locale}"/></button>
                </div>
            </div>
        </form>
        <table class="table">
            <thead>
            <tr>
                <td hidden>#</td>
                <td><my:locMes key="rooms.jsp.table.header.name" localeString="${pageContext.response.locale}"/></td>
                <td><my:locMes key="rooms.jsp.table.header.capacity" localeString="${pageContext.response.locale}"/></td>
                <td><my:locMes key="rooms.jsp.table.header.price" localeString="${pageContext.response.locale}"/></td>
                <td><my:locMes key="rooms.jsp.table.header.class" localeString="${pageContext.response.locale}"/></td>
                <td><my:locMes key="rooms.jsp.table.header.status" localeString="${pageContext.response.locale}"/></td>
            </tr>
            </thead>
            <c:forEach var="room" items="${rooms}" >
                <tr>
                    <td hidden><c:out value="${room.id}"/></td>
                    <td><c:out value="${room.name}"/></td>
                    <td><c:out value="${room.capacity}"/></td>
                    <td><c:out value="${room.money}"/></td>
                    <td><c:out value="${room.apartmentClassName}"/></td>
                    <td><c:out value="${room.apartmentStatusName}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <my:exception />
</div>
<script type="text/javascript" src="/libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="/libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
