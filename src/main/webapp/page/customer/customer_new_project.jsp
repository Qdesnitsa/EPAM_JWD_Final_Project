<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Customer Edit">
    <jsp:attribute name="actions">
        <t:customer-actions/>
    </jsp:attribute>
    <jsp:body>
        <form action="controller" method="POST">
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.create_new_project"/></h2>
                </caption>
                <tr>
                    <th><fmt:message key="label.project_name"/></th>
                    <th><fmt:message key="label.project_start_date"/></th>
                    <th><fmt:message key="label.project_end_date"/></th>
                    <th><fmt:message key="label.employee_position"/></th>
                    <th>
                        <fmt:message key="label.employee_level"/>
                         &
                        <fmt:message key="label.employee_quantity"/>
                    </th>
                </tr>
                <tr>
                    <th>
                <textarea
                        type="text"
                        required
                        name="project_name"
                        placeholder="<fmt:message key="label.project_name"/>"
                        rows="5"
                ></textarea>
                    </th>
                    <th width="33%">
                        <fmt:message key="label.project_start_date"/><br/>
                        <input type="date" required name="startDate" class="date_period"/>
                    </th>

                    <th width="33%">
                        <fmt:message key="label.project_end_date"/><br/>
                        <input type="date" required name="endDate" class="date_period"/>
                    </th>

                    <th>
                        <fieldset>
                            <legend><fmt:message key="label.multiple_choice"/></legend>
                            <select
                                    name="developers"
                                    id="developers[]"
                                    size="5"
                                    multiple
                            >
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
                            </select>
                        </fieldset>
                    </th>
                    <th>
                        <small>Senior </small
                        ><input
                            type="text"
                            name="senior_quantity"
                            maxlength="3"
                            placeholder="<fmt:message key="label.employee_quantity"/>"
                            value="0"
                            pattern="[\d]+"
                    /><br>
                        <small>Middle </small
                        ><input
                            type="text"
                            name="middle_quantity"
                            maxlength="3"
                            placeholder="<fmt:message key="label.employee_quantity"/>"
                            value="0"
                            pattern="[\d]+"
                    /><br>
                        <small>Junior </small
                        ><input
                            type="text"
                            name="junior_quantity"
                            maxlength="3"
                            placeholder="<fmt:message key="label.employee_quantity"/>"
                            value="0"
                            pattern="[\d]+"
                    />
                    </th>
                </tr>
            </table>
            <div align="center">
                <button type="submit" id="submit" class="submit" name="command" value="new_project_post">
                    <fmt:message key="label.submit"/></button>
            </div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>