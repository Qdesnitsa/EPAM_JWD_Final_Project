<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<form action="controller" method="POST">
    <button type="submit" id="show_projects" class="show" name="command" value="show_projects">
        <fmt:message key="label.show_my_projects"/>
    </button>
</form>

