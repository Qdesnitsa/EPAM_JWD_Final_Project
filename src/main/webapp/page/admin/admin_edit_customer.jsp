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
        <form method="post" action="/IT_Team/edit-customer">
        <table class="table" align="center">
            <caption>
                <h2>Edit customer</h2>
                <fieldset>
                    <legend>Please fill in employee ID</legend>
                    <table>
                        <tr>
                            <th width="33%">
                                Customer ID<br/>
                                <input
                                        type="text"
                                        name="customer_id"
                                        class="project_id"
                                        placeholder="customer id"
                                        pattern="[\d]+"
                                />
                                <br/>
                            </th>
                        </tr>
                    </table>
                </fieldset>
            </caption>
            <tr>
                <th>Customer ID</th>
                <th>Email</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Status</th>
            </tr>
            <tr>
                <td>${customer.id}</td>
                <td>${customer.email}</td>
                <td>${customer.name}</td>
                <td>${customer.surname}</td>
                <td>${customer.status}</td>
            </tr>
        </table>
        <div align="center">
            <button type="submit" class="submit" name="show_customer">Show</button>
        </div>

        <table class="table" align="center">
            <caption>
                <h2>Customer data edition</h2>
            </caption>
            <tr>
                <th>Status</th>
            </tr>
            <tr>
                <th>
                    <select name="change_customer_status">
                        <option value="1" >active</option>
                        <option value="2">blocked</option>
                    </select
                    ><br/>
                    <button type="submit" class="submit" name="change_status">
                        Change status
                    </button>
                </th>
            </tr>
        </table>
            <div class="msg"><br>
                <c:if test="${message != null}">
                    <h4><c:out value="${message}" default="guest"/></h4>
                </c:if>
            </div>
        <div align="center"></div>
        <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>