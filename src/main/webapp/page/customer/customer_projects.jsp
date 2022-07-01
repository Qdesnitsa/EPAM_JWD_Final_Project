<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/employee/styles.css"%>
</style>
<t:not-auth-layout title="Customer Projects">
    <form>
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
                    <button type="submit" id="new_project" class="new">
                        Create new project
                    </button>

                    <button type="submit" id="show_projects" class="show">
                        Show my projects
                    </button>
                </div>
            </div>
            <div class="article">
                <table class="table" align="center">
                    <caption>
                        <h2>My projects</h2>
                        <fieldset>
                            <legend>Select project status</legend>
                            <table>
                                <tr>
                                    <select name="status" class="status">
                                        <option value="0" selected>all</option>
                                        <option value="2">prepared</option>
                                        <option value="3">active</option>
                                        <option value="4">cancelled</option>
                                        <option value="5">finished</option>
                                    </select>
                                </tr>
                            </table>
                        </fieldset>
                    </caption>
                    <tr>
                        <th>Project ID</th>
                        <th>Project name</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Project status</th>
                        <th>Total amount</th>
                        <th>Amount already paid</th>
                        <th>Amount to pay</th>
                    </tr>
                </table>
                <div align="center">
                    <button type="submit" class="submit">Show</button>
                </div>
                <table class="table" align="center">
                    <caption>
                        <h2>Proceed payment</h2>
                        <fieldset>
                            <legend>Enter project ID and amount to pay</legend>
                        </fieldset>
                    </caption>
                    <tr>
                        <th>Project ID</th>
                        <th>Amount to pay</th>
                    </tr>
                    <tr>
                        <th><input
                                type="text"
                                name="project_id"
                                placeholder="project_id"
                                pattern="[\d]+"
                        /></th>
                        <th><input
                                type="text"
                                name="payment"
                                placeholder="two numbers after point"
                                pattern="[\d]+[.]?[0-9]{0,2}"
                        /></th>
                    </tr>

                </table>
                <div align="center">
                    <button type="submit" class="submit">Confirm payment</button>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">
            &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
        </div>
    </form>
</t:not-auth-layout>