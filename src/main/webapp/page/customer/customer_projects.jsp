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
                    <h2><fmt:message key="label.my_projects"/></h2><br>
                </caption>
                <tr>
                    <th><fmt:message key="label.project_name"/></th>
                    <th><fmt:message key="label.project_start_date"/></th>
                    <th><fmt:message key="label.project_end_date"/></th>
                    <th><fmt:message key="label.project_status"/></th>
                    <th><fmt:message key="label.project_cost_plan"/></th>
                    <th><fmt:message key="label.project_amount_paid"/></th>
                    <th><fmt:message key="label.debt"/></th>
                    <th><fmt:message key="label.project_amount_topay"/></th>
                    <th><fmt:message key="label.action"/></th>
                </tr>
                <c:forEach var="project" items="${projects}">
                    <tr>
                        <form action="controller" method="POST">
                            <td>${project.name}</td>
                            <td>${project.startDate}</td>
                            <td>${project.endDate}</td>
                            <td>${project.status}</td>
                            <td>${project.costPlan}</td>
                            <td>${project.amount}</td>
                            <td>${project.costPlan-project.amount}</td>
                            <td>
                                <input style="width: 120px"
                                       type="text"
                                       name="payment"
                                       pattern="[\d]+[.]?[0-9]{0,2}"
                                />
                            </td>
                            <td>
                                <div align="center">
                                    <input type="hidden" name="project_id" value="${project.id}">
                                    <button type="submit" class="submit_small" name="command" value="new_payment_post">
                                        <fmt:message key="label.confirm"/></button>
                                </div>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
            <div class="msg"><br>
                <c:choose>
                    <c:when test="${message_success != null}">
                        <h4><fmt:message key="label.message_success"/></h4>
                    </c:when>
                    <c:when test="${message_fail != null}">
                        <h4><fmt:message key="label.message_fail"/></h4>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>