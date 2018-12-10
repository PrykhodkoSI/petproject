<%@ taglib prefix="my" uri="http://mycompany.com" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-end">
    <form class="col-1" action="${pageContext.request.contextPath}/rooms" method="get">
        <button type="submit" class="btn btn-primary"><my:locMes key="menu.jsp.button.rooms" localeString="${pageContext.response.locale}"/></button>
    </form>
    <c:choose >
        <c:when test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.user.isClient()}">
                <form  class="col-1" action="${pageContext.request.contextPath}/user/profile" method="get">
                    <button type="submit" class="btn btn-primary"><my:locMes key="rooms.jsp.button.profile" localeString="${pageContext.response.locale}"/></button>
                </form>
            </c:if>
            <c:if test="${sessionScope.user.isManager()}">
                <form  class="col-2" action="${pageContext.request.contextPath}/managers/profile" method="get">
                    <button type="submit" class="btn btn-primary"><my:locMes key="rooms.jsp.button.profileManager" localeString="${pageContext.response.locale}"/></button>
                </form>
            </c:if>
            <form class="col-1" action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-primary"><my:locMes key="user.profile.jsp.button.logout" localeString="${pageContext.response.locale}"/></button>
            </form>
        </c:when>
        <c:otherwise>
            <form class="col-1" action="${pageContext.request.contextPath}/login" method="get">
                <button type="submit" class="btn btn-primary"><my:locMes key="menu.jsp.button.login" localeString="${pageContext.response.locale}"/></button>
            </form>
        </c:otherwise>
    </c:choose>
    <my:locale/>
</div>