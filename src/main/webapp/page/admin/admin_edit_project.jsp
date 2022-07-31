<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin Edit Project">
    <jsp:attribute name="actions">
        <t:admin-actions/>
    </jsp:attribute>
    <jsp:body>
        <form action="controller" method="POST">
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.project_edition"/></h2><br>
                </caption>
                <tr>
                    <th width="10%"><fmt:message key="label.project_id"/></th>
                    <th width="10%"><fmt:message key="label.customer_id"/></th>
                    <th width="15%"><fmt:message key="label.project_name"/></th>
                    <th width="10%"><fmt:message key="label.project_status"/></th>
                    <th width="10%"><fmt:message key="label.project_start_date"/></th>
                    <th width="10%"><fmt:message key="label.project_end_date"/></th>
                    <th width="35%"><fmt:message key="label.project_comment"/></th>
                </tr>
                <tr>
                    <td>${project.id}</td>
                    <td>${project.customerId}</td>
                    <td>${project.name}</td>
                    <td>${project.status}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <td>${project.requirementComment}</td>
                </tr>
            </table>
            <table class="table" align="center">
                <caption><br>
                    <h2><fmt:message key="label.add_employees_on_project"/></h2>
                    <fieldset>
                        <legend><fmt:message key="label.select_data_to_show"/></legend>
                        <table>
                            <tr>
                                <th width="25%">
                                    <fmt:message key="label.employee_position"/><br/>
                                    <select name="employee_position">
                                        <option value="HTML/CSS/JS">HTML/CSS/JS</option>
                                        <option value="C++">C++</option>
                                        <option value="C#">C#</option>
                                        <option value="Java">Java</option>
                                        <option value="Python">Python</option>
                                        <option value="Ruby">Ruby</option>
                                        <option value="PHP">PHP</option>
                                        <option value="DBA">DBA</option>
                                        <option value="QA_Automation">QA Automation</option>
                                        <option value="QA_Manual">QA Manual</option>
                                        <option value="BusinessAnalyst">BA</option>
                                    </select
                                    ><br/>
                                </th>
                                <th width="25%">
                                    <fmt:message key="label.employee_level"/><br/>
                                    <select name="level">
                                        <option value="junior" selected>junior</option>
                                        <option value="middle">middle</option>
                                        <option value="senior">senior</option>
                                        <option value="manager">manager</option>
                                    </select
                                    ><br/>
                                </th>
                                <th width="25%">
                                    <fmt:message key="label.employee_quantity"/><br/>
                                    <input
                                            type="text"
                                            name="quantity"
                                            value="0"
                                            placeholder="quantity"
                                            pattern="[\d]+"
                                    />
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
                <tr>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                    <th><fmt:message key="label.button_action"/></th>
                </tr>
                <c:forEach var="employee" items="${free_employees}">
                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.position}</td>
                        <td>${employee.level}</td>
                        <td>
                            <form action="controller" method="POST">
                                <input type="hidden" name="add_id" value="${employee.id}"/>
                                <button type="submit" class="submit" name="command" value="add_employee_post">
                                    <fmt:message key="label.add_employee"/>
                                </button>
                                <br>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div align="center">
                <button type="submit" class="submit" name="command" value="show_free_employees_get"><fmt:message
                        key="label.show"/></button>
            </div>
            <table class="table" align="center">
                <caption><br>
                    <h2><fmt:message key="label.project_show_remove_employee"/></h2>
                </caption>
                <tr>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th><fmt:message key="label.employee_level"/></th>
                    <th><fmt:message key="label.button_action"/></th>
                </tr>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <td>${employee.name}</td>
                        <td>${employee.surname}</td>
                        <td>${employee.position}</td>
                        <td>${employee.level}</td>
                        <td>
                            <form action="controller" method="POST">
                                <input type="hidden" name="remove_id" value="${employee.id}"/>
                                <button type="submit" class="submit" name="command" value="remove_employees_post">
                                    <fmt:message key="label.remove_employee"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div align="center">
                <button type="submit" class="submit" name="command" value="show_employees_on_project_get">
                    <fmt:message key="label.show"/></button>
            </div>
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.project_parameters_edition"/></h2>
                </caption>
                <tr>
                    <th><fmt:message key="label.project_status"/></th>
                    <th><fmt:message key="label.project_start_date"/></th>
                    <th><fmt:message key="label.project_end_date"/></th>
                </tr>
                <tr>
                    <th>
                        <select name="project_status">
                            <option value="3" selected>active</option>
                            <option value="4">cancelled</option>
                            <option value="5">finished</option>
                        </select
                        ><br/>
                        <button type="submit" class="submit" name="command" value="change_project_status">
                            <fmt:message key="label.change_status"/>
                        </button>
                    </th>
                    <th width="33%">
                        <input type="date" name="start_date" class="date"/><br/>
                        <button type="submit" class="submit" name="command" value="change_project_start">
                            <fmt:message key="label.change_project_start"/>
                        </button>
                    </th>
                    <th width="33%">
                        <input type="date" name="end_date" class="date"/><br/>
                        <button type="submit" class="submit" name="command" value="change_project_end">
                            <fmt:message key="label.change_project_end"/>
                        </button>
                    </th>
                </tr>
            </table>
            <button type="submit" class="big_button" name="command" value="remove_calculation_project_post">
                <fmt:message key="label.remove_calculate_project"/>
            </button>
            <button type="submit" class="big_button" name="command" value="add_calculation_project_post">
                <fmt:message key="label.add_calculate_project"/>
            </button>
            <div class="msg"><br>
                <c:if test="${message != null}">
                    <h4><c:out value="${message}" default="guest"/></h4>
                </c:if>
            </div>
            <div align="center">
            </div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>