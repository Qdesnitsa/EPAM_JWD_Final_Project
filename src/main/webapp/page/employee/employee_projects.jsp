<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h2>My projects</h2>
            </caption>
            <tr>
                <th>Project ID</th>
                <th>Project name</th>
                <th>Project status</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Hours FACT</th>
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
        <div align="center">
            <button type="submit" id="submit" class="submit">Show</button>
        </div>
        <div class="clear"></div>
    </jsp:body>
</t:layout>
