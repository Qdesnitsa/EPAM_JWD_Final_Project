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
                    <h2>All employees</h2>
                    <fieldset>
                        <legend>Select employee position and level</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Position<br/>
                                    <input
                                            type="text"
                                            name="position"
                                            placeholder="specialization"
                                    />
                                </th>
                                <th width="33%">
                                    Level<br/>
                                    <input
                                            type="text"
                                            name="level"
                                            placeholder="level"
                                    />
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
                <tr>
                    <th>Employee ID</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Status</th>
                    <th>Position</th>
                    <th>Level</th>
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
            <div align="center">
                <button type="submit" class="submit">Show</button>
            </div>
            <div class="clear"></div>
    </jsp:body>
</t:layout>