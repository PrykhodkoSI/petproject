<form class="col-2" action="${pageContext.request.contextPath}/locale" method="post">
    <select name="locale" class="form-control" id="localeId" onchange="this.form.submit()" >
        <option selected value="${pageContext.response.locale}">${pageContext.response.locale}</option>
        <option value="en">en</option>
        <option value="ru">ru</option>
    </select>
</form>