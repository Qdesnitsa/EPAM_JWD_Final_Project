<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin Edit Project">
    <jsp:attribute name="actions">
        <t:admin-actions/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="/IT_Team/edit-project">
        <table class="table" align="center">
            <caption>
                <h2>Edit project</h2>
                <fieldset>
                    <legend>Please fill in project ID</legend>
                    <table>
                        <tr>
                            <th width="33%">
                                Project ID<br/>
                                <input
                                        type="text"
                                        required
                                        name="project_id"
                                        class="project_id"
                                        placeholder="project id"
                                        pattern="[\d]+"
                                />
                                <br/>
                            </th>
                        </tr>
                    </table>
                </fieldset>
            </caption>
            <tr>
                <th>Project ID</th>
                <th>Customer ID</th>
                <th>Project name</th>
                <th>Project status</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Requirements comment</th>
            </tr>

            <c:forEach var="project" items="${project}">

                <tr>
                    <td>${project.id}</td>
                    <td>${project.customerId}</td>
                    <td>${project.name}</td>
                    <td>${project.status}</td>
                    <td>${project.startDate}</td>
                    <td>${project.endDate}</td>
                    <td>${project.requirementComment}</td>
                </tr>

            </c:forEach>
        </table>
        <div align="center">
            <button type="submit" class="submit" name="show_project">Show</button>
        </div>

        <table class="table" align="center">
            <caption>
                <h2>Employees</h2>
                <fieldset>
                    <legend>Select data to show</legend>
                    <table>
                        <tr>
                            <th width="25%">
                                Employee position<br/>
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
                                Employee level<br/>
                                <select name="level">
                                    <option value="junior">junior</option>
                                    <option value="middle">middle</option>
                                    <option value="senior">senior</option>
                                    <option value="manager">manager</option>
                                </select
                                ><br/>
                            </th>
                            <th width="25%">
                                Quantity<br/>
                                <textarea
                                        cols="8"
                                        rows="1"
                                        type="text"
                                        name="quantity"
                                        placeholder="quantity"
                                ></textarea>
                            </th>
                        </tr>
                    </table>
                </fieldset>
            </caption>
            <tr>
                <th>Employee ID</th>
                <th>Employee position</th>
                <th>Employee level</th>
            </tr>
            <c:forEach var="employee" items="${employees}">

                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.position}</td>
                    <td>${employee.level}</td>
                </tr>

            </c:forEach>
        </table>
        <div align="center">
            <button type="submit" class="submit" name="show_employees">Show</button>
        </div>

        <table class="table" align="center">
            <caption>
                <h2>Project edition</h2>
            </caption>
            <tr>
                <th>Project status</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Add employee</th>
                <th>Remove employee</th>
            </tr>
            <tr>
                <th>
                    <select name="project_status">
                        <option value="2" selected>prepared</option>
                        <option value="3">active</option>
                        <option value="4">cancel</option>
                        <option value="5">finish</option>
                    </select
                    ><br/>
                    <button type="submit" class="submit" name="change_status">
                        Change status
                    </button>
                </th>
                <th width="33%">
                    <input type="date" name="start_date" class="date"/><br/>
                    <button type="submit" class="submit" name="change_start">
                        Change start date
                    </button>
                </th>
                <th width="33%">
                    <input type="date" name="end_date" class="date"/><br/>
                    <button type="submit" class="submit" name="change_end">
                        Change end date
                    </button>
                </th>
                <th>
                    <input type="text" name="add_id" placeholder="employee_id" pattern="[\d]+"/>
                    <button type="submit" class="submit" name="add_employee">
                        Add employee
                    </button>
                </th>
                <th>
                    <input type="text" name="remove_id" placeholder="employee_id" pattern="[\d]+"/>
                    <button type="submit" class="submit" name="remove_employee">
                        Remove employee
                    </button>
                </th>
            </tr>
        </table>
            <button type="submit" class="big_button" name="calculate_project">
                Calculate project cost and hours
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