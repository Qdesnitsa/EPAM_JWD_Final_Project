<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    Change employee status
                </button>
                <button type="submit" class="change_status">
                    Change project status
                </button>
                <button type="submit" class="change_status">
                    Change customer status
                </button>
            </div>
        </div>
        <div class="article">
            <table class="table" align="center">
                <caption>
                    <h2>New projects</h2>
                    <fieldset>
                        <legend>Select month and year period</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Select start month<br />
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
                                    /><br />
                                </th>
                                <th width="33%">
                                    Select end month<br />
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
                                    /><br />
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
                <tr>
                    <th>Project discription</th>
                    <th>Developer specialization</th>
                    <th>Required quantity</th>
                </tr>
            </table>

            <table class="table" align="center">
                <caption>
                    <h2>My projects</h2>
                    <fieldset>
                        <legend>Select month and year period</legend>
                        <table>
                            <tr>
                                <th width="33%">
                                    Select start month<br />
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
                                    /><br />
                                </th>
                                <th width="33%">
                                    Select end month<br />
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
                                    /><br />
                                </th>
                            </tr>
                        </table>
                    </fieldset>
                </caption>
                <tr>
                    <th>Project ID</th>
                    <th>Project title</th>
                    <th>Project discription</th>
                    <th>Project status</th>
                    <th>Amount already paid</th>
                    <th>Amount to pay</th>
                    <th>Actual payment</th>
                </tr>
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