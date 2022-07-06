<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Customer Projects">
    <jsp:attribute name="actions">
        <t:customer-actions/>
    </jsp:attribute>
    <jsp:body>
        <table class="table" align="center">
            <caption>
                <h2>My projects</h2>
                <fieldset>
                    <legend>Select project status</legend>
                    <table>
                        <tr>
                            <select name="status" class="status">
                                <option value="0" selected>all</option>
                                <option value="2">prepared</option>
                                <option value="3">active</option>
                                <option value="4">cancelled</option>
                                <option value="5">finished</option>
                            </select>
                        </tr>
                    </table>
                </fieldset>
            </caption>
            <tr>
                <th>Project ID</th>
                <th>Project name</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Project status</th>
                <th>Total amount</th>
                <th>Amount already paid</th>
                <th>Amount to pay</th>
            </tr>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td>${project.id}</td>
                    <td>${project.name}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <td>${project.status}</td>
                    <td>NOT IMPLEMENTED</td>
                    <td>NOT IMPLEMENTED</td>
                    <td>NOT IMPLEMENTED</td>
                </tr>
            </c:forEach>
        </table>
        <div align="center">
            <button type="submit" class="submit">Show</button>
        </div>
        <table class="table" align="center">
            <caption>
                <h2>Proceed payment</h2>
                <fieldset>
                    <legend>Enter project ID and amount to pay</legend>
                </fieldset>
            </caption>
            <tr>
                <th>Project ID</th>
                <th>Amount to pay</th>
            </tr>
            <tr>
                <th><input
                        type="text"
                        name="project_id"
                        placeholder="project_id"
                        pattern="[\d]+"
                /></th>
                <th><input
                        type="text"
                        name="payment"
                        placeholder="two numbers after point"
                        pattern="[\d]+[.]?[0-9]{0,2}"
                /></th>
            </tr>

        </table>
        <div align="center">
            <button type="submit" class="submit">Confirm payment</button>
        </div>
        <div class="clear"></div>
    </jsp:body>
</t:layout>