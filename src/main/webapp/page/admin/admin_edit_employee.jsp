<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin Edit Employee">
    <jsp:attribute name="actions">
        <t:admin-actions/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="/IT_Team/edit-employee">
            <table class="table" align="center">
                <caption>
                    <h2>Edit employee</h2>
                    <fieldset>
                        <legend>Please fill in employee ID</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Employee ID<br/>
                                    <input
                                            type="text"
                                            name="employee_id"
                                            class="project_id"
                                            placeholder="employee id"
                                            pattern="[\d]+"
                                    />
                                    <br/>
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
                <button type="submit" class="submit" name="show_employee">Show</button>
            </div>
            <table class="table" align="center">
                <caption>
                    <h2>Employee data edition</h2>
                </caption>
                <tr>
                    <th>Status</th>
                    <th>Position</th>
                    <th>Level</th>
                </tr>
                <tr>
                    <th>
                        <select name="change_employee_status">
                            <option value="1">active</option>
                            <option value="2">blocked</option>
                        </select
                        ><br/>
                        <button type="submit" class="submit" name="change_status">
                            Change status
                        </button>
                    </th>
                    <th width="25%">
                        Employee position<br/>
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
                        <button type="submit" class="big_button_middle" name="change_position">
                            Change specialization
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
                        <button type="submit" class="submit" name="change_level">
                            Change level
                        </button>
                    </th>
                </tr>
            </table>
            <table class="table" align="center">
                <caption>
                    <h2>Add new employee</h2>
                </caption>
                <tr>
                    <th>Email</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Status</th>
                    <th>Role</th>
                    <th>Position</th>
                    <th>Level</th>
                </tr>
                <tr>
                    <th>
                        <input
                                type="text"
                                name="email"
                                placeholder="email"
                                pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                        /><br/>
                    </th>
                    <th>
                        <input
                                type="text"
                                name="password"
                                placeholder="password"
                        /><br/>
                    </th>
                    <th>
                        <input
                                type="text"
                                name="name"
                                placeholder="name"
                                pattern="[a-zA-Zа-яА-Я]+"
                        /><br/>
                    </th>
                    <th>
                        <input
                                type="text"
                                name="surname"
                                placeholder="surname"
                                pattern="[a-zA-Zа-яА-Я]+"
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
                <button type="submit" class="submit" name="add_employee">Add</button>
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