<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 31.08.2018
  Time: 22:03
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
    <title><my:locMes key="user.profile.jsp.title" localeString="${pageContext.response.locale}"/></title>
</head>
<body>
<div class="container">
    <my:menu/>
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1><my:locMes key="user.profile.jsp.title" localeString="${pageContext.response.locale}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-6">
            <c:if test="${not empty sessionScope.user}">
                <c:set var="user" value="${sessionScope.user}"/>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="user.profile.jsp.label.name" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${user.name}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="user.profile.jsp.label.surname" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${user.surname}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="user.profile.jsp.label.age" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${user.age}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="user.profile.jsp.label.email" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${user.email}</h6>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="row justify-content-md-center">
                <h3 class="col-9"><my:locMes key="user.profile.jsp.requests.title" localeString="${pageContext.response.locale}"/></h3>
                <form class="col-3" action="${pageContext.request.contextPath}/user/profile/request/create" method="get">
                    <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.requests.add" localeString="${pageContext.response.locale}"/></button>
                </form>
            </div>
            <c:if test="${not empty requests}">
                <div class="row justify-content-md-center">
                    <table class="table">
                        <thead>
                        <tr>
                            <td hidden>#</td>
                            <td><my:locMes key="user.profile.jsp.requests.arrivalDate" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="user.profile.jsp.requests.departureDate" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="user.profile.jsp.requests.apartment" localeString="${pageContext.response.locale}"/></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </thead>
                        <c:forEach var="request" items="${requests}" >
                            <tr>
                                <td hidden><c:out value="${request.id}"/></td>
                                <td><c:out value="${request.arrivalDate}"/></td>
                                <td><c:out value="${request.departureDate}"/></td>
                                <td><c:out value="${request.apartment.name}"/></td>
                                <td>
                                    <form class="col-1" action="${pageContext.request.contextPath}/user/profile/request/attach" method="get">
                                        <input type="hidden" id="bookingRequestIdAttach" name="id" value="${request.id}" />
                                        <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.requests.attach" localeString="${pageContext.response.locale}"/></button>
                                    </form>
                                </td>
                                <td>
                                    <c:if test="${not empty request.apartment.id}">
                                        <form class="col-1" action="${pageContext.request.contextPath}/user/profile/request/accept" method="post">
                                            <input type="hidden" id="bookingRequestIdAccept" name="id" value="${request.id}" />
                                            <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.requests.accept" localeString="${pageContext.response.locale}"/></button>
                                        </form>
                                    </c:if>
                                </td>
                                <td>
                                    <form class="col-1" action="${pageContext.request.contextPath}/user/profile/request/remove" method="get">
                                        <input type="hidden" id="bookingRequestIdRemove" name="id" value="${request.id}" />
                                        <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.requests.dismis" localeString="${pageContext.response.locale}"/></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <div class="row justify-content-md-center">
                <h3 class="col-12"><my:locMes key="user.profile.jsp.bills.title" localeString="${pageContext.response.locale}"/></h3>
            </div>
            <c:if test="${not empty bills}">
                <div class="row justify-content-md-center">
                    <table class="table">
                        <thead>
                        <tr>
                            <td hidden>#</td>
                            <td><my:locMes key="user.profile.jsp.bills.billedDate" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="user.profile.jsp.bills.price" localeString="${pageContext.response.locale}"/></td>
                            <td></td>
                            <td></td>
                        </tr>
                        </thead>
                        <c:forEach var="bill" items="${bills}" >
                            <tr>
                                <td hidden><c:out value="${bill.id}"/></td>
                                <td><c:out value="${bill.billedDate}"/></td>
                                <td><c:out value="${bill.money}"/></td>
                                <td>
                                    <form class="col-1" action="${pageContext.request.contextPath}/user/profile/request/view" method="get">
                                        <input type="hidden" id="billRequestId" name="id" value="${bill.bookingRequest.id}" />
                                        <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.bills.viewRequest" localeString="${pageContext.response.locale}"/></button>
                                    </form>
                                </td>
                                <td>
                                    <form class="col-1" action="${pageContext.request.contextPath}/user/profile/bill/pay" method="post">
                                        <input type="hidden" id="billIdPay" name="id" value="${bill.id}" />
                                        <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.bills.pay" localeString="${pageContext.response.locale}"/></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
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
