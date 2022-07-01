<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/employee/styles.css"%>
</style>
<t:not-auth-layout title="Admin Edit Customer">
    <div class="container">
        <div class="header">
            <h2>
                <table width="100%">
                    <tbody>
                    <tr>
                        <th class="greeting" width="33.33%" ;>
                            Hello, username usersurname !
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
                <button type="submit" class="show">
                    Show all projects
                </button>
                <button type="submit" class="change">
                    Edit project data
                </button>
                <button type="submit" class="show">
                    Show all employees
                </button>
                <button type="submit" class="change">
                    Edit employee data
                </button>
                <button type="submit" class="show">
                    Show all customers
                </button>
                <button type="submit" class="change">
                    Edit customer data
                </button>
            </div>
        </div>
        <div class="article">
            <table class="table" align="center">
                <caption>
                    <h2>All employees</h2>
                    <fieldset>
                        <legend>Select employee position and level</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Position<br/>
                                    <input
                                            type="text"
                                            name="position"
                                            placeholder="specialization"
                                    />
                                </th>
                                <th width="33%">
                                    Level<br/>
                                    <input
                                            type="text"
                                            name="level"
                                            placeholder="level"
                                    />
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
            </table>
            <div align="center">
                <button type="submit" class="submit">Show</button>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="footer">
        &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
    </div>
</t:not-auth-layout>