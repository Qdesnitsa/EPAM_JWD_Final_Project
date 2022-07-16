<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Employee Projects">
    <jsp:attribute name="actions">
        <t:employee-actions/>
    </jsp:attribute>
    <jsp:body>
        <table class="table" align="center">
            <caption>
                <h2><fmt:message key="label.my_projects"/></h2>
            </caption>
            <tr>
                <th><fmt:message key="label.project_id"/></th>
                <th><fmt:message key="label.project_name"/></th>
                <th><fmt:message key="label.project_status"/></th>
                <th><fmt:message key="label.project_start_date"/></th>
                <th><fmt:message key="label.project_end_date"/></th>
                <th><fmt:message key="label.project_hours_fact"/></th>
            </tr>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>${project.status}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <td>${project.hoursFact}</td>
                </tr>
            </c:forEach>
        </table>
        <div class="clear"></div>
    </jsp:body>
</t:layout>
