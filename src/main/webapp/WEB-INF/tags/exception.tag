<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" uri="http://mycompany.com" %>
<c:if test="${not empty exception}">
    <div class="row">
        <div class="col-2-offset-2">
            <h3 class="text-danger"><my:locMes key="exception.error" localeString="${pageContext.response.locale}"/></h3>
        </div>
        <div class="col-6">
            <h3 class="text-danger">${exception.localizedMessage}</h3>
        </div>
    </div>
</c:if>