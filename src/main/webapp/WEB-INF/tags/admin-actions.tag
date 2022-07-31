<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<form action="controller" method="POST">
    <button type="submit" class="show" name="command" value="show_projects">
        <fmt:message key="label.show_all_projects"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="show" name="command" value="show_employees_get">
        <fmt:message key="label.show_all_employees"/>
    </button>
</form>

<form action="controller" method="POST">
    <button type="submit" class="show" name="command" value="show_customers_get">
        <fmt:message key="label.show_all_customers"/>
    </button>
</form>