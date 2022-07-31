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
            <h2 style="text-align: center"><fmt:message key="label.all_projects"/></h2>
            <div style="text-align: center"><fmt:message key="label.choose_status"/><br>
                <form action="controller" method="POST">
                <select name="project_status">
                    <option value="1">new</option>
                    <option value="2">prepared</option>
                    <option value="3">active</option>
                    <option value="4">cancelled</option>
                    <option value="5">finished</option>
                </select
                ><br/>
                <button type="submit" class="submit" name="command" value="show_projects">
                    <fmt:message key="label.choose_status"/>
                </button>
                </form>
            </div>
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
                    <form action="controller" method="POST">
                        <td>
                            <a href="/IT_Team/controller?command=edit_project_get&project_id=${project.id}">${project.name}</a>
                        </td>
                    </form>
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

        <div class="paginator_block">
            <table>
                <tr>
                    <c:forEach begin="1" end="${page_numbers}" var="i">
                        <c:choose>
                            <c:when test="${page_number eq i}">
                                <td>
                                    <button class="paginator_active" type="submit">${i}</button>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="command" value="show_projects">
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