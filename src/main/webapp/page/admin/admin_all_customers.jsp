<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h2>All customers</h2>
            </caption>
            <tr>
                <th>Customer ID</th>
                <th>Email</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Status</th>
            </tr>
            <c:forEach var="customer" items="${customers}">
                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.email}</td>
                    <td>${customer.name}</td>
                    <td>${customer.surname}</td>
                    <td>${customer.status}</td>
                </tr>
            </c:forEach>
        </table>
        <div align="center">
            <button type="submit" class="submit">Show</button>
        </div>
        <div class="clear"></div>
    </jsp:body>
</t:layout>