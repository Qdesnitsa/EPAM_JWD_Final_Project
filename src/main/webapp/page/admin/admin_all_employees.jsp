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
                    <th><fmt:message key="label.employee_id"/></th>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.status"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                </tr>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${employee.id}</td>
                        <td>${employee.email}</td>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.status}</td>
                        <td>${employee.position}</td>
                        <td>${employee.level}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="clear"></div>
    </jsp:body>
</t:layout>