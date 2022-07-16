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
        <form action="controller" method="POST">
        <table class="table" align="center">
            <caption>
                <h2><fmt:message key="label.customer_edition"/></h2>
                <fieldset>
                    <legend><fmt:message key="label.fill_customer_id"/></legend>
                    <table>
                        <tr>
                            <th width="33%">
                                <fmt:message key="label.customer_id"/><br/>
                                <input
                                        type="text"
                                        name="customer_id"
                                        class="project_id"
                                        placeholder="<fmt:message key="label.customer_id"/>"
                                        pattern="[\d]+"
                                />
                                <br/>
                            </th>
                        </tr>
                    </table>
                </fieldset>
            </caption>
            <tr>
                <th><fmt:message key="label.customer_id"/></th>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.name"/></th>
                <th><fmt:message key="label.surname"/></th>
                <th><fmt:message key="label.status"/></th>
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
            <button type="submit" class="submit" name="command" value="show_customer_get"><fmt:message key="label.show"/></button>
        </div>

        <table class="table" align="center">
            <caption>
                <h2><fmt:message key="label.customer_data_edition"/></h2>
            </caption>
            <tr>
                <th><fmt:message key="label.status"/></th>
            </tr>
            <tr>
                <th>
                    <select name="change_customer_status">
                        <option value="1" >active</option>
                        <option value="2">blocked</option>
                    </select
                    ><br/>
                    <button type="submit" class="submit" name="command" value="change_customer_status_post">
                        <fmt:message key="label.change_status"/>
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