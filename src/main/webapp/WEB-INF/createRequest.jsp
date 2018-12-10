<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 30.08.2018
  Time: 1:49
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
    <title><my:locMes key="request.create.jsp.title" localeString="${pageContext.response.locale}"/></title>
</head>
<body>
<div class="container">
    <my:menu/>
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1><my:locMes key="request.create.jsp.title" localeString="${pageContext.response.locale}"/></h1>
        </div>
    </div>
    <div class="row">
        <form class="col-6" action="${pageContext.request.contextPath}/user/profile/request/create" method="post">
            <div class="form-group">
                <label for="bookingArrivalDate"><my:locMes key="request.create.jsp.label.arrival" localeString="${pageContext.response.locale}"/></label>
                <input name="arrivalDate" type="date" class="form-control" id="bookingArrivalDate" aria-describedby="dateArrivalHelp" placeholder="<my:locMes key="request.create.jsp.label.arrivalDate.description" localeString="${pageContext.response.locale}"/>">
                <small id="dateArrivalHelp" class="form-text text-muted"><my:locMes key="request.create.jsp.label.arrivalDate.description" localeString="${pageContext.response.locale}"/></small>
            </div>
            <div class="form-group">
                <label for="bookingDepartureDate"><my:locMes key="request.create.jsp.label.departure" localeString="${pageContext.response.locale}"/></label>
                <input name="departureDate" type="date" class="form-control" id="bookingDepartureDate" aria-describedby="dateDepartureHelp" placeholder="<my:locMes key="request.create.jsp.label.departure.description" localeString="${pageContext.response.locale}"/>">
                <small id="dateDepartureHelp" class="form-text text-muted"><my:locMes key="request.create.jsp.label.departure.description" localeString="${pageContext.response.locale}"/></small>
            </div>
            <div class="form-group">
                <label for="bookingCapacity"><my:locMes key="request.create.jsp.label.capacity" localeString="${pageContext.response.locale}"/></label>
                <input name="capacity" type="number" class="form-control" id="bookingCapacity" placeholder="<my:locMes key="request.create.jsp.label.capacity.hint" localeString="${pageContext.response.locale}"/>">
            </div>
            <div class="form-group">
                <label for="bookingApartmentClass"><my:locMes key="request.create.jsp.label.apartmentClass" localeString="${pageContext.response.locale}"/></label>
                <c:if test="${not empty classes}">
                <select name="apartmentClassId" class="form-control" id="bookingApartmentClass">
                    <c:forEach var="appclass" items="${classes}" >
                        <option value="${appclass.id}">${appclass.name}</option>
                    </c:forEach>
                </select>
                </c:if>

            </div>
            <button type="submit" class="btn btn-primary"><my:locMes key="request.create.jsp.button" localeString="${pageContext.response.locale}"/></button>
        </form>
    </div>
    <my:exception />
</div>
<script type="text/javascript" src="/libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="/libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
