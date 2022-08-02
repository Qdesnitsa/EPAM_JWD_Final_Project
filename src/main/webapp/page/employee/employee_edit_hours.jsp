<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Employee Edit">
    <jsp:attribute name="actions">
        <t:employee-actions/>
    </jsp:attribute>
    <jsp:body>
        <form action="controller" method="POST">
            <table class="table" align="center">
                <caption>
                    <h2><fmt:message key="label.post_hours"/></h2>
                    <fieldset>
                        <legend><fmt:message key="label.fill_in_all_fields"/></legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    <fmt:message key="label.select_date"/><br/>
                                    <input
                                            type="date"
                                            required
                                            name="date"
                                            class="date"
                                    />
                                </th>
                                <th width="33%">
                                    <fmt:message key="label.post_hours"/><br/>
                                    <input
                                            type="text"
                                            required
                                            name="hours"
                                            placeholder="<fmt:message key="label.hours024"/>"
                                            pattern="^([0-9]|([1][0-9]|[2][0-4]))"
                                    />
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
            </table>
            <div class="msg"><br>
                <c:choose>
                    <c:when test="${message_success != null}">
                        <h4><fmt:message key="label.message_success"/></h4>
                    </c:when>
                    <c:when test="${message_fail != null}">
                        <h4><fmt:message key="label.message_fail"/></h4>
                    </c:when>
                    <c:otherwise>

                    </c:otherwise>
                </c:choose>
            </div>
            <div align="center">
                <button type="submit" id="submit" class="submit" name="command" value="post_hours_post">
                    <fmt:message key="label.submit"/></button>
            </div>
            <div class="clear"></div>
        </form>
    </jsp:body>
</t:layout>
