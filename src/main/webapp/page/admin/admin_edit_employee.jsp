<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/employee/styles.css"%>
</style>
<t:not-auth-layout title="Admin Edit Employee">
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
                    <button type="submit" id="show_projects" class="change">
                        Edit customer data
                    </button>
                </div>
            </div>
            <div class="article">
                <table class="table" align="center">
                    <caption>
                        <h2>Edit employee</h2>
                        <fieldset>
                            <legend>Please fill in employee ID</legend>
                            <table>
                                <tr>
                                    <th width="33%">
                                        Employee ID<br/>
                                        <input
                                                type="text"
                                                name="project_id"
                                                class="project_id"
                                                placeholder="employee id"
                                                pattern="[\d]+"
                                        />
                                        <br/>
                                    </th>
                                </tr>
                            </table>
                        </fieldset>
                    </caption>
                    <tr>
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
                <table class="table" align="center">
                    <caption>
                        <h2>Employee data edition</h2>
                    </caption>
                    <tr>
                        <th>Status</th>
                        <th>Position</th>
                        <th>Level</th>
                    </tr>
                    <tr>
                        <th>
                            <select name="change_employee_status">
                                <option value="1" selected>active</option>
                                <option value="2">blocked</option>
                            </select
                            ><br/>
                            <button type="submit" class="submit">
                                Change status
                            </button>
                        </th>
                        <th>
                            <input
                                    type="text"
                                    name="position"
                                    placeholder="specialization"
                            /><br/>
                            <button type="submit" class="submit">
                                Change specialization
                            </button>
                        </th>
                        <th>
                            <input type="text" name="level" placeholder="level"/><br/>
                            <button type="submit" class="submit">
                                Change level
                            </button>
                        </th>
                    </tr>
                </table>
                <table class="table" align="center">
                    <caption>
                        <h2>Add new employee</h2>
                    </caption>
                    <tr>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Status</th>
                        <th>Role</th>
                        <th>Position</th>
                        <th>Level</th>
                    </tr>
                    <tr>
                        <th>
                            <input
                                    type="text"
                                    name="email"
                                    placeholder="email"
                                    pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                            /><br/>
                        </th>
                        <th>
                            <input
                                    type="text"
                                    name="password"
                                    placeholder="password"
                            /><br/>
                        </th>
                        <th>
                            <input
                                    type="text"
                                    name="name"
                                    placeholder="name"
                                    pattern="[a-zA-Zа-яА-Я]"
                            /><br/>
                        </th>
                        <th>
                            <input
                                    type="text"
                                    name="surname"
                                    placeholder="surname"
                                    pattern="[a-zA-Zа-яА-Я]"
                            /><br/>
                        </th>
                        <th>
                            <select name="status">
                                <option value="1" selected>active</option>
                                <option value="2">blocked</option>
                            </select
                            ><br/>
                        </th>
                        <th>
                            <select name="role">
                                <option value="1" selected>admin</option>
                                <option value="2">employee</option>
                            </select
                            ><br/>
                        </th>
                        <th>
                            <input
                                    type="text"
                                    name="position"
                                    placeholder="specialization"
                            /><br/>
                        </th>
                        <th>
                            <select name="level">
                                <option value="1" selected>junior</option>
                                <option value="2">middle</option>
                                <option value="3">senior</option>
                                <option value="5">manager</option>
                            </select
                            ><br/>
                        </th>
                    </tr>
                </table>
                <div align="center">
                    <button type="submit" class="submit">Add</button>
                </div>
                <div align="center"></div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">
            &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
        </div>
    </form>
</t:not-auth-layout>