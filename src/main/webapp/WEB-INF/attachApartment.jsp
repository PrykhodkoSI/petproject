<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 01.09.2018
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://mycompany.com" %>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap-4.1.3/css/bootstrap.min.css">
    <title><my:locMes key="requests.attach.jsp.title" localeString="${pageContext.response.locale}"/></title>
</head>
<body>
<div class="container">
    <my:menu/>
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1><my:locMes key="requests.attach.jsp.title" localeString="${pageContext.response.locale}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <c:if test="${not empty request}" >

                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.attach.jsp.arrivalDate" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.arrivalDate}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.attach.jsp.departureDate" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.departureDate}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.attach.jsp.capacity" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.capacity}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.attach.jsp.apartmentClass" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.apartmentClassName}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.attach.jsp.user" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.user.email}</h6>
                    </div>
                </div>
                <c:if test="${not empty apartments}">
                    <form class="row" action="${pageContext.request.contextPath}/${rolePath}/profile/request/attach" method="post">
                        <div class="form-group">
                            <input type="hidden" id="request" name="requestId" value="${request.id}">
                            <label for="bookingApartment"><my:locMes key="requests.attach.jsp.apartment.title" localeString="${pageContext.response.locale}"/></label>

                            <select name="apartmentId" class="form-control" id="bookingApartment">
                                <c:forEach var="apartment" items="${apartments}" >
                                    <option value="${apartment.id}">${apartment.name}</option>
                                </c:forEach>
                            </select>


                        </div>
                        <button type="submit" class="btn btn-primary"><my:locMes key="requests.attach.jsp.button" localeString="${pageContext.response.locale}"/></button>
                    </form>
                </c:if>
                <c:if test="${empty apartments}">
                    <form class="row" action="${pageContext.request.contextPath}/${rolePath}/profile" method="get">
                        <button type="submit" class="btn btn-primary"><my:locMes key="requests.attach.jsp.button.back" localeString="${pageContext.response.locale}"/></button>
                    </form>
                </c:if>
            </c:if>
        </div>
    </div>
    <my:exception />
</div>
<script type="text/javascript" src="/libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="/libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
