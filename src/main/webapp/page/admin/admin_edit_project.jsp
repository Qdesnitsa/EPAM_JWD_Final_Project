<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/css/styles.css"%>
</style>
<t:layout title="Admin Edit Project">
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
                    <button type="submit" id="new_project" class="show">
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
                        <h2>Edit project</h2>
                        <fieldset>
                            <legend>Please fill in project ID</legend>
                            <table>
                                <tr>
                                    <th width="33%">
                                        Project ID<br/>
                                        <input
                                                type="text"
                                                name="project_id"
                                                class="project_id"
                                                placeholder="project id"
                                                pattern="[\d]+"
                                        />
                                        <br/>
                                    </th>
                                </tr>
                            </table>
                        </fieldset>
                    </caption>
                    <tr>
                        <th>Customer ID</th>
                        <th>Project name</th>
                        <th>Project status</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Requirements comment</th>
                    </tr>
                </table>
                <div align="center">
                    <button type="submit" class="submit">Show</button>
                </div>

                <table class="table" align="center">
                    <caption>
                        <h2>Employees</h2>
                        <fieldset>
                            <legend>Select data to show</legend>
                            <table>
                                <tr>
                                    <th width="25%">
                                        Select start date<br/>
                                        <input type="date" name="start_date" class="date"/><br/>
                                    </th>
                                    <th width="25%">
                                        Select end date<br/>
                                        <input type="date" name="end_date" class="date"/><br/>
                                    </th>

                                    <th width="25%">
                                        Employee position<br/>
                                        <input type="text" name="employee_position" placeholder="position"/>
                                    </th>
                                    <th width="25%">
                                        Employee level<br/>
                                        <select name="level">
                                            <option value="0" selected>all</option>
                                            <option value="1">junior</option>
                                            <option value="2">middle</option>
                                            <option value="3">senior</option>
                                            <option value="5">manager</option>
                                        </select
                                        ><br/>
                                    </th>
                                    <th width="25%">
                                        Quantity<br/>
                                        <textarea
                                                cols="8"
                                                rows="1"
                                                type="text"
                                                name="employee_position"
                                                placeholder="quantity"
                                        ></textarea>
                                    </th>
                                </tr>
                            </table>
                        </fieldset>
                    </caption>
                    <tr>
                        <th>Employee ID</th>
                        <th>Employee position</th>
                        <th>Employee level</th>
                    </tr>
                </table>
                <div align="center">
                    <button type="submit" class="submit">Show</button>
                </div>

                <table class="table" align="center">
                    <caption>
                        <h2>Project edition</h2>
                    </caption>
                    <tr>
                        <th>Project status</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Add employee</th>
                        <th>Remove employee</th>
                    </tr>
                    <tr>
                        <th>
                            <select name="change_project_status">
                                <option value="PREPARE" selected>prepared</option>
                                <option value="ACTIVE">active</option>
                                <option value="CANCEL">cancel</option>
                                <option value="FINISH">finish</option>
                            </select
                            ><br/>
                            <button type="submit" class="submit">
                                Change status
                            </button>
                        </th>
                        <th width="33%">
                            <input type="date" name="start_date" class="date"/><br/>
                            <button type="submit" class="submit">
                                Change start date
                            </button>
                        </th>
                        <th width="33%">
                            <input type="date" name="end_date" class="date"/><br/>
                            <button type="submit" class="submit">
                                Change end date
                            </button>
                        </th>
                        <th>
                            <input type="text" name="employee_id" placeholder="employee_id" pattern="[\d]+"/>
                            <button type="submit" class="submit">
                                Add employee
                            </button>
                        </th>
                        <th>
                            <input type="text" name="employee_id" placeholder="employee_id" pattern="[\d]+"/>
                            <button type="submit" class="submit">
                                Remove employee
                            </button>
                        </th>
                    </tr>
                </table>
                <div align="center">
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="footer">
            &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
        </div>
    </form>
</t:layout>