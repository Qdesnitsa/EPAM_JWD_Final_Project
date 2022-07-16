<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin All Projects">
    <jsp:attribute name="actions">
        <t:admin-actions/>
    </jsp:attribute>
    <jsp:body>
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.all_projects"/></h2>
                </caption>
                <tr>
                    <th><fmt:message key="label.customer_id"/></th>
                    <th><fmt:message key="label.project_id"/></th>
                    <th><fmt:message key="label.project_name"/></th>
                    <th><fmt:message key="label.project_status"/></th>
                    <th><fmt:message key="label.project_start_date"/></th>
                    <th><fmt:message key="label.project_end_date"/></th>
                    <th><fmt:message key="label.project_hours_plan"/></th>
                    <th><fmt:message key="label.project_hours_fact"/></th>
                    <th><fmt:message key="label.project_cost_plan"/></th>
                    <th><fmt:message key="label.project_amount_paid"/></th>
                </tr>
                <c:forEach var="project" items="${projects}">
                    <tr>
                        <td>${project.customerId}</td>
                        <td>${project.id}</td>
                        <td>${project.name}</td>
                        <td>${project.status}</td>
                        <td>${project.startDate}</td>
                        <td>${project.endDate}</td>
                        <td>${project.hoursPlan}</td>
                        <td>${project.hoursFact}</td>
                        <td>${project.costPlan}</td>
                        <td>${project.amount}</td>
                    </tr>
                </c:forEach>
            </table>
            <div class="clear"></div>
    </jsp:body>
</t:layout>