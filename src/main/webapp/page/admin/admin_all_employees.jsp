<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin Edit Customer">
    <jsp:attribute name="actions">
        <t:admin-actions/>
    </jsp:attribute>
    <jsp:body>
        <table class="table" align="center">
            <caption>
                <h2><fmt:message key="label.all_employees"/></h2>
            </caption>
            <tr>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.name"/></th>
                <th><fmt:message key="label.surname"/></th>
                <th><fmt:message key="label.status"/></th>
                <th><fmt:message key="label.employee_position"/></th>
                <th><fmt:message key="label.employee_level"/></th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.email}</td>
                    <td>${employee.name}</td>
                    <form action="controller" method="POST">
                        <input type="hidden">
                        <td>
                            <a href="/IT_Team/controller?command=edit_employee_get&employee_id=${employee.id}">${employee.surname}</a>
                        </td>
                    </form>
                    <td>${employee.status}</td>
                    <td>${employee.position}</td>
                    <td>${employee.level}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="paginator_block">
            <table>
                <tr>
                    <c:forEach begin="1" end="${page_quantity}" var="i">
                        <c:choose>
                            <c:when test="${page_number eq i}">
                                <td>
                                    <button class="paginator_active" type="submit">${i}</button>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="command" value="show_employees_get">
                                        <input type="hidden" name="page_number" value="${i}">
                                        <button class="paginator" type="submit">${i}</button>
                                    </form>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
        </div>
        <div class="clear"></div>
    </jsp:body>
</t:layout>