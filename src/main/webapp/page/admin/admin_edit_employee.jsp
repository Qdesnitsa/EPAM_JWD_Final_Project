<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin Edit Employee">
    <jsp:attribute name="actions">
        <t:admin-actions/>
    </jsp:attribute>
    <jsp:body>
        <form action="controller" method="POST">
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.edition_employee"/></h2><br>
                </caption>
                <tr>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.status"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                </tr>
                <tr>
                    <td>${employee.email}</td>
                    <td>${employee.name}</td>
                    <td>${employee.surname}</td>
                    <td>${employee.status}</td>
                    <td>${employee.position}</td>
                    <td>${employee.level}</td>
                </tr>
            </table>
            <table class="table" align="center">
                <caption><br>
                    <h2><fmt:message key="label.employee_parameters_edition"/></h2>
                </caption>
                <tr>
                    <th><fmt:message key="label.status"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                </tr>
                <tr>
                    <th>
                        <select name="change_employee_status">
                            <option value="1">active</option>
                            <option value="2">blocked</option>
                        </select
                        ><br/>
                        <button type="submit" class="submit" name="command" value="change_employee_status_post">
                            <fmt:message key="label.change_status"/>
                        </button>
                    </th>
                    <th width="25%">
                        <select name="change_employee_position">
                            <option value="2">HTML/CSS/JS</option>
                            <option value="3">C++</option>
                            <option value="4">C#</option>
                            <option value="1">Java</option>
                            <option value="5">Python</option>
                            <option value="6">Ruby</option>
                            <option value="7">PHP</option>
                            <option value="8">DBA</option>
                            <option value="9">QA Automation</option>
                            <option value="10">QA Manual</option>
                            <option value="11">BA</option>
                        </select
                        ><br/>
                        <button type="submit" class="big_button_middle" name="command"
                                value="change_employee_position_post">
                            <fmt:message key="label.change_position"/>
                        </button>
                    </th>
                    <th>
                        <select name="change_employee_level">
                            <option value="1">junior</option>
                            <option value="2">middle</option>
                            <option value="3">senior</option>
                            <option value="4">manager</option>
                        </select
                        ><br/>
                        <button type="submit" class="submit" name="command" value="change_employee_level_post">
                            <fmt:message key="label.change_level"/>
                        </button>
                    </th>
                </tr>
            </table>
            <div class="msg"><br>
                <c:choose>
                    <c:when test="${message_success != null}">
                        <h4><fmt:message key="label.message_success"/></h4>
                    </c:when>
                    <c:when test="${message_fail != null}">
                        <h4><fmt:message key="label.message_fail"/></h4>
                    </c:when>
                    <c:when test="${message_email_exists != null}">
                        <h4><fmt:message key="label.message_email_exists"/></h4>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </div>
            <div align="center"></div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>