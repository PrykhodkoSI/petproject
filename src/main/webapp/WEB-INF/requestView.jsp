<%--
  Created by IntelliJ IDEA.
  User: Stas
  Date: 02.09.2018
  Time: 9:21
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
    <title><my:locMes key="requests.view.jsp.title" localeString="${pageContext.response.locale}"/></title>
</head>
<body>
<div class="container">
    <my:menu/>
    <div class="row justify-content-md-center">
        <div class="col-md-auto">
            <h1><my:locMes key="requests.view.jsp.title" localeString="${pageContext.response.locale}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <c:if test="${not empty request}" >

                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.view.jsp.arrivalDate" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.arrivalDate}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.view.jsp.departureDate" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.departureDate}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.view.jsp.capacity" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.capacity}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.view.jsp.apartmentClass" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.apartmentClassName}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.view.jsp.user" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.user.email}</h6>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <h6><my:locMes key="requests.view.jsp.accepted" localeString="${pageContext.response.locale}"/></h6>
                    </div>
                    <div class="col-6">
                        <h6>${request.isAccepted()  ? 'Yes' : 'No'}</h6>
                    </div>
                </div>
                <div class="row justify-content-md-center">
                    <h3 class="col-12"><my:locMes key="requests.view.jsp.apartment.title" localeString="${pageContext.response.locale}"/></h3>
                </div>
                <div class="row">
                    <table class="table">
                        <thead>
                        <tr>
                            <td hidden>#</td>
                            <td><my:locMes key="requests.view.jsp.apartment.name" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="requests.view.jsp.apartment.capacity" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="requests.view.jsp.apartment.price" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="requests.view.jsp.apartment.class" localeString="${pageContext.response.locale}"/></td>
                            <td><my:locMes key="requests.view.jsp.apartment.status" localeString="${pageContext.response.locale}"/></td>
                        </tr>
                        </thead>
                        <c:if test="${not empty request.apartment}" >
                            <tr>
                                <td hidden><c:out value="${request.apartment.id}"/></td>
                                <td><c:out value="${request.apartment.name}"/></td>
                                <td><c:out value="${request.apartment.capacity}"/></td>
                                <td><c:out value="${request.apartment.money}"/></td>
                                <td><c:out value="${request.apartment.apartmentClassName}"/></td>
                                <td><c:out value="${request.apartment.apartmentStatusName}"/></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                </div>
                <form class="row" action="${pageContext.request.contextPath}/${rolePath}/profile" method="get">
                    <button type="submit" class="btn btn-primary"><my:locMes key="requests.view.jsp.button" localeString="${pageContext.response.locale}"/></button>
                </form>
            </c:if>
        </div>
    </div>
    <c:if test="${not empty error}">
        <div class="row">
            <c:set var="error" value="${error}"/>
            <div class="col-2-offset-2">
                <h3 class="text-danger">Error:</h3>
            </div>
            <div class="col-6">
                <h3 class="text-danger">${error.key}</h3>
            </div>
        </div>
    </c:if>
<my:exception />
</div>
<script type="text/javascript" src="/libs/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/libs/propper-1.11.0/popper.min.js"></script>
<script type="text/javascript" src="/libs/bootstrap-4.1.3/js/bootstrap.min.js"></script>

</body>
</html>