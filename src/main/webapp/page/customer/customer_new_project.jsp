<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Customer Edit">
    <jsp:attribute name="actions">
        <t:customer-actions/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="/IT_Team/projects">
            <table class="table" align="center">
                <caption>
                    <h2>Create my new project</h2>
                </caption>
                <tr>
                    <th>Project name</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Specialization</th>
                    <th>Level & Quantity</th>
                </tr>
                <tr>
                    <th>
                <textarea
                        type="text"
                        required
                        name="project_name"
                        placeholder="project_name"
                        rows="5"
                ></textarea>
                    </th>
                    <th width="33%">
                        Select start date<br/>
                        <input type="date" required name="startDate" class="date_period"/>
                    </th>

                    <th width="33%">
                        Select end date<br/>
                        <input type="date" required name="endDate" class="date_period"/>
                    </th>

                    <th>
                        <fieldset>
                            <legend>Multiple choice</legend>
                            <select
                                    name="developers[]"
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
                            maxlength="2"
                            placeholder="quantity"
                            pattern="[\d]+"
                    />
                        <small>Middle </small
                        ><input
                            type="text"
                            name="middle_quantity"
                            maxlength="2"
                            placeholder="quantity"
                            pattern="[\d]+"
                    />
                        <small>Junior </small
                        ><input
                            type="text"
                            name="junior_quantity"
                            maxlength="2"
                            placeholder="quantity"
                            pattern="[\d]+"
                    />
                    </th>
                </tr>
            </table>
            <div align="center">
                <button type="submit" id="submit" class="submit">Submit</button>
            </div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>