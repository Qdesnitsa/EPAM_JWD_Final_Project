<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Employee Projects">
    <jsp:attribute name="actions">
        <t:employee-actions/>
    </jsp:attribute>
    <jsp:body>
        <table class="table" align="center">
            <caption>
                <h2>My projects</h2>
                <fieldset>
                    <legend>Select project status</legend>
                    <table>
                        <tr>
                            <th width="33%">
                                Project status<br/>
                                <select name="status" class="status">
                                    <option value="0" selected>all</option>
                                    <option value="3">active</option>
                                    <option value="4">cancelled</option>
                                    <option value="5">finished</option>
                                </select>
                                <br/>
                            </th>
                        </tr>
                    </table>
                </fieldset>
            </caption>
            <tr>
                <th>Project ID</th>
                <th>Project name</th>
                <th>Project status</th>
                <th>Start date</th>
                <th>End date</th>
                <th>Hours FACT</th>
            </tr>
        </table>
        <div align="center">
            <button type="submit" id="submit" class="submit">Show</button>
        </div>
        <div class="clear"></div>
    </jsp:body>
</t:layout>
