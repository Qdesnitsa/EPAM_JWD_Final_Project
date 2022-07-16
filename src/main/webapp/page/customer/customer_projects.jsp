<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Customer Projects">
    <jsp:attribute name="actions">
        <t:customer-actions/>
    </jsp:attribute>
    <jsp:body>
        <form action="controller" method="POST">
        <table class="table" align="center">
            <caption>
                <h2><fmt:message key="label.my_projects"/></h2>
            </caption>
            <tr>
                <th><fmt:message key="label.project_id"/></th>
                <th><fmt:message key="label.project_name"/></th>
                <th><fmt:message key="label.project_start_date"/></th>
                <th><fmt:message key="label.project_end_date"/></th>
                <th><fmt:message key="label.project_status"/></th>
                <th><fmt:message key="label.project_cost_plan"/></th>
                <th><fmt:message key="label.project_amount_paid"/></th>
                <th><fmt:message key="label.project_amount_topay"/></th>
            </tr>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <td>${project.status}</td>
                    <td>${project.costPlan}</td>
                    <td>${project.amount}</td>
                    <td>${project.costPlan-project.amount}</td>
                </tr>
            </c:forEach>
        </table>
        <table class="table" align="center">
            <caption>
                <h2><fmt:message key="label.proceed_payment"/></h2>
                <fieldset>
                    <legend><fmt:message key="label.enter_data_payment"/></legend>
                </fieldset>
            </caption>
            <tr>
                <th><fmt:message key="label.project_id"/></th>
                <th><fmt:message key="label.project_amount_topay"/></th>
            </tr>
            <tr>
                <th><input
                        type="text"
                        name="project_id"
                        placeholder="project_id"
                        pattern="[\d]+"
                        value="0"
                /></th>
                <th><input
                        type="text"
                        name="payment"
                        placeholder="<fmt:message key="label.two_numbers_after_point"/>"
                        pattern="[\d]+[.]?[0-9]{0,2}"
                /></th>
            </tr>

        </table>
            <div class="msg"><br>
                <c:if test="${message != null}">
                    <h4><c:out value="${message}" default="guest"/></h4>
                </c:if>
            </div>
        <div align="center">
            <button type="submit" class="submit" name="command" value="new_payment_post">
                <fmt:message key="label.confirm_payment"/></button>
        </div>
        <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>