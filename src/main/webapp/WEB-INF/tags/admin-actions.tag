<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<form action="controller" method="POST">
    <input type="text" style="display: none" name="page" value="${page}">
    <input type="text" style="display: none" name="page_size" value="${page_size}">
    <button type="submit" class="show" name="command" value="show_projects">
        <fmt:message key="label.show_all_projects"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="change" name="command" value="edit_project_get">
        <fmt:message key="label.edit_project"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="show" name="command" value="show_employees_get">
        <fmt:message key="label.show_all_employees"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="change" name="command" value="edit_employee_get">
        <fmt:message key="label.edit_employee"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="show" name="command" value="show_customers_get">
        <fmt:message key="label.show_all_customers"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="change" name="command" value="edit_customer_get">
        <fmt:message key="label.edit_customer"/>
    </button>
</form>