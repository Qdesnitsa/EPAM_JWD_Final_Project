<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/employee/styles.css"%>
</style>
<t:not-auth-layout title="Customer Edit">
    <form>
        <div class="container">
            <div class="header">
                <h2>
                    <table width="100%">
                        <tbody>
                        <tr>
                            <th class="greeting" width="33.33%">
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
                    <button type="submit" id="post_hours" class="post_hours">
                        Post hours spent
                    </button>

                    <button type="submit" id="show_projects" class="show">
                        Show my projects
                    </button>
                </div>
            </div>
            <div class="article">
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
                <div align="center">
                    <button type="submit" id="submit" class="submit">Submit</button>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">
            &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
        </div>
    </form>
</t:not-auth-layout>
