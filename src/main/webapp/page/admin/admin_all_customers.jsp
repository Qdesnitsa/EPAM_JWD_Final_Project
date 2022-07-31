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
                <h2><fmt:message key="label.all_customers"/></h2><br>
            </caption>
            <tr>
                <th><fmt:message key="label.customer_id"/></th>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.name"/></th>
                <th><fmt:message key="label.surname"/></th>
                <th><fmt:message key="label.status"/></th>
            </tr>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.email}</td>
                    <td>${customer.name}</td>
                    <form action="controller" method="POST">
                        <input type="hidden">
                        <td>
                            <a href="/IT_Team/controller?command=edit_customer_get&customer_id=${customer.id}">${customer.surname}</a>
                        </td>
                    </form>
                    <td>${customer.status}</td>
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
                                        <input type="hidden" name="command" value="show_customers_get">
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