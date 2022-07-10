<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Employee Edit">
    <jsp:attribute name="actions">
        <t:employee-actions/>
    </jsp:attribute>
    <jsp:body>
        <form method="post" action="/IT_Team/post-hours">
            <table class="table" align="center">
                <caption>
                    <h2>Post working hours</h2>
                    <fieldset>
                        <legend>Select date and project ID</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Select date<br/>
                                    <input
                                            type="date"
                                            name="date"
                                            class="date"
                                    />
                                </th>
                                <th width="33%">
                                    Project ID<br/>
                                    <input
                                            type="text"
                                            name="project_id"
                                            placeholder="project id"
                                            pattern="[\d]+"
                                    />
                                </th>
                                <th width="33%">
                                    Hours consumed<br/>
                                    <input
                                            type="text"
                                            name="hours"
                                            placeholder="hours 0-24"
                                            pattern="^([0-9]|([1][0-9]|[2][0-4]))"
                                    />
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
            </table>
            <div class="msg"><br>
                <c:if test="${message != null}">
                    <h4><c:out value="${message}" default="guest"/></h4>
                </c:if>
            </div>
            <div align="center">
                <button type="submit" id="submit" class="submit">Submit</button>
            </div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>
