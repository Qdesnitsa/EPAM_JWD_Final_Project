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
                    <h2>All projects</h2>
                    <fieldset>
                        <legend>Select project status</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Project status<br/>
                                    <select name="status" class="status">
                                        <option value="0" selected>all</option>
                                        <option value="1">new</option>
                                        <option value="2">prepared</option>
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
                    <th>Customer ID</th>
                    <th>Project ID</th>
                    <th>Project name</th>
                    <th>Project status</th>
                    <th>Start date</th>
                    <th>End date</th>
                    <th>Hours PLAN</th>
                    <th>Hours FACT</th>
                    <th>Total amount</th>
                    <th>Amount already paid</th>
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