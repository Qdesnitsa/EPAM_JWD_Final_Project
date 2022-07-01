<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/pages/home/styles.css"%>
</style>
<t:not-auth-layout title="Admin Home">
    <div class="container">
        <div class="header">
            <h2>
                <table width="100%">
                    <tbody>
                    <tr>
                        <th class="greeting" width="33.33%" ;>
                            Hello, username usersurname ${email}!
                        </th>
                        <th class="date" width="33.33%">Today is Date_now</th>
                        <th class="sign_out_btn" width="33.33%">
                            <button type="submit" id="sign_out" class="signout_btn">
                                Sign out
                            </button>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </h2>
        </div>

        <div class="content">
            <div class="navigation">
                <h1 class="menu">Menu</h1>
                <button type="submit" id="new_project" class="new_orders">
                    Show new orders
                </button>
                <button type="submit" class="show_projects">
                    Show all projects
                </button>
                <button type="submit" class="new_employee">
                    Add new employee
                </button>
                <button type="submit" class="change_status">
                    Change employee data
                </button>
                <button type="submit" class="change_status">
                    Change project data
                </button>
                <button type="submit" class="change_status">
                    Change customer status
                </button>
            </div>
        </div>
        <div class="article">
            <table class="table" align="center">
                <caption>
                    <h2>All projects</h2>
                    <fieldset>
                        <legend>Select month and year period</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Select start month<br/>
                                    <select name="month" class="month">
                                        <option value="01" selected>January</option>
                                        <option value="02">February</option>
                                        <option value="03">March</option>
                                        <option value="04">April</option>
                                        <option value="05">May</option>
                                        <option value="06">June</option>
                                        <option value="07">July</option>
                                        <option value="08">August</option>
                                        <option value="09">September</option>
                                        <option value="10">October</option>
                                        <option value="11">November</option>
                                        <option value="12">December</option>
                                    </select>
                                    <input
                                            type="text"
                                            name="year"
                                            class="year"
                                            maxlength="4"
                                            placeholder="Year"
                                            pattern="((19)|(20))[\d]{2}"
                                    /><br/>
                                </th>
                                <th width="33%">
                                    Select end month<br/>
                                    <select name="month" class="month">
                                        <option value="01" selected>January</option>
                                        <option value="02">February</option>
                                        <option value="03">March</option>
                                        <option value="04">April</option>
                                        <option value="05">May</option>
                                        <option value="06">June</option>
                                        <option value="07">July</option>
                                        <option value="08">August</option>
                                        <option value="09">September</option>
                                        <option value="10">October</option>
                                        <option value="11">November</option>
                                        <option value="12">December</option>
                                    </select>
                                    <input
                                            type="text"
                                            name="year"
                                            class="year"
                                            maxlength="4"
                                            placeholder="Year"
                                            pattern="((19)|(20))[\d]{2}"
                                    /><br/>
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
                <tr>
                    <th>Project ID</th>
                    <th>Project title</th>
                    <th>Project description</th>
                    <th>Project status</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Hours PLAN</th>
                    <th>Hours FACT</th>
                    <th>Amount to pay</th>
                    <th>Amount already paid</th>
                </tr>
                <c:forEach var="i" begin="1" end="3" step="1">

                    <tr>
                        <td>${i + step}</td>
                        <td>${projects[i].shortName}</td>
                        <td>${projects[i].description}</td>


                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:forEach>
            </table>
            <div align="center">
                <button type="submit" id="submit" class="submit">Submit</button>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="footer">
        &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
    </div>
</t:not-auth-layout>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>Hello, ${usern} ${users}</h1>--%>
<%--<br/>--%>
<%--<table>--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <td>Short name</td>--%>
<%--        <td>Description</td>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--        <c:forEach var="project" items="${projects}">--%>
<%--            <tr>--%>
<%--                <td>${project.shortName}</td>--%>
<%--                <td>${project.description}</td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--    </tbody>--%>

<%--</table>--%>

<%--</body>--%>
<%--</html>--%>

<%--{--%>
<%--    "projects": [--%>
<%--        <c:forEach var="project" items="${projects}">--%>
<%--            {--%>
<%--                "shortName": "${project.shortName}",--%>
<%--                "description": "${project.description}"--%>
<%--            }--%>
<%--        </c:forEach>--%>
<%--    ]--%>
<%-- }--%>