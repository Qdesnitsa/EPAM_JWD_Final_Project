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
                    <h2><fmt:message key="label.edition_employee"/></h2>
                    <fieldset>
                        <legend><fmt:message key="label.fill_employee_id"/></legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    <fmt:message key="label.employee_id"/><br/>
                                    <input
                                            type="text"
                                            name="employee_id"
                                            class="project_id"
                                            placeholder="<fmt:message key="label.employee_id"/>"
                                            pattern="[\d]+"
                                    />
                                    <br/>
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
                <tr>
                    <th><fmt:message key="label.employee_id"/></th>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.status"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                </tr>
                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.email}</td>
                    <td>${employee.name}</td>
                    <td>${employee.surname}</td>
                    <td>${employee.status}</td>
                    <td>${employee.position}</td>
                    <td>${employee.level}</td>
                </tr>
            </table>
            <div align="center">
                <button type="submit" class="submit" name="command" value="show_employee_get"><fmt:message key="label.show"/></button>
            </div>
            <table class="table" align="center">
                <caption>
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
                        <select name="employee_position">
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
                        <button type="submit" class="big_button_middle" name="command" value="change_employee_position_post">
                            <fmt:message key="label.change_position"/>
                        </button>
                    </th>
                    <th>
                        <select name="employee_level">
                            <option value="1">junior</option>
                            <option value="2">middle</option>
                            <option value="3">senior</option>
                            <option value="5">manager</option>
                        </select
                        ><br/>
                        <button type="submit" class="submit" name="command" value="change_employee_level_post">
                            <fmt:message key="label.change_level"/>
                        </button>
                    </th>
                </tr>
            </table>
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.addition_new_employee"/></h2>
                </caption>
                <tr>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.password"/></th>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.status"/></th>
                    <th><fmt:message key="label.employee_role"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                </tr>
                <tr>
                    <th>
                        <input
                                type="text"
                                name="email"
                                placeholder="<fmt:message key="label.email"/>"
                                pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                        /><br/>
                    </th>
                    <th>
                        <input
                                type="text"
                                name="password"
                                placeholder="<fmt:message key="label.password"/>"
                        /><br/>
                    </th>
                    <th>
                        <input
                                type="text"
                                name="name"
                                placeholder="<fmt:message key="label.name"/>"
                                pattern="[a-zA-Z??-????-??]+"
                        /><br/>
                    </th>
                    <th>
                        <input
                                type="text"
                                name="surname"
                                placeholder="<fmt:message key="label.surname"/>"
                                pattern="[a-zA-Z??-????-??]+"
                        /><br/>
                    </th>
                    <th>
                        <select name="employee_status">
                            <option value="1" selected>active</option>
                            <option value="2">blocked</option>
                        </select
                        ><br/>
                    </th>
                    <th>
                        <select name="employee_role">
                            <option value="1" selected>admin</option>
                            <option value="2">employee</option>
                        </select
                        ><br/>
                    </th>
                    <th width="25%">

                        <select name="employee_position">
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
                    </th>
                    <th>
                        <select name="employee_level">
                            <option value="1">junior</option>
                            <option value="2">middle</option>
                            <option value="3">senior</option>
                            <option value="5">manager</option>
                        </select
                        ><br/>
                    </th>
                </tr>
            </table>
            <div align="center">
                <button type="submit" class="submit" name="command" value="add_new_employee_post"><fmt:message key="label.add"/></button>
            </div>
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