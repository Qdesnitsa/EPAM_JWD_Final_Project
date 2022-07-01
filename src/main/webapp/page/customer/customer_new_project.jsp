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
                        <h2>Create my new project</h2>
                    </caption>
                    <tr>
                        <th>Project name</th>
                        <th>Start date</th>
                        <th>End date</th>
                        <th>Specialization</th>
                        <th>Level & Quantity</th>
                    </tr>
                    <tr>
                        <th>
                <textarea
                        type="text"
                        required
                        name="project_name"
                        placeholder="project_name"
                        rows="5"
                ></textarea>
                        </th>
                        <th width="33%">
                            Select start date<br/>
                            <input type="date" required name="date" class="date"/>
                        </th>

                        <th width="33%">
                            Select end date<br/>
                            <input type="date" required name="date" class="date"/>
                        </th>

                        <th>
                            <fieldset>
                                <legend>Multiple choice</legend>
                                <select
                                        name="developers[]"
                                        id="developers[]"
                                        size="5"
                                        multiple
                                >
                                    <option value="Frontend">HTML/CSS/JS</option>
                                    <option value="C++">C++</option>
                                    <option value="C#">C#</option>
                                    <option value="Java">Java</option>
                                    <option value="Python">Python</option>
                                    <option value="Ruby">Ruby</option>
                                    <option value="PHP">PHP</option>
                                    <option value="DBA">DBA</option>
                                    <option value="QA_Automation">QA Automation</option>
                                    <option value="QA_Manual">QA Manual</option>
                                    <option value="BusinessAnalyst">BA</option>
                                </select>
                            </fieldset>
                        </th>
                        <th>
                            <small>Senior </small
                            ><input
                                type="text"
                                name="senior_quantity"
                                maxlength="2"
                                placeholder="quantity"
                                pattern="[\d]+"
                        />
                            <small>Middle </small
                            ><input
                                type="text"
                                name="middle_quantity"
                                maxlength="2"
                                placeholder="quantity"
                                pattern="[\d]+"
                        />
                            <small>Junior </small
                            ><input
                                type="text"
                                name="junior_quantity"
                                maxlength="2"
                                placeholder="quantity"
                                pattern="[\d]+"
                        />
                        </th>
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
    </form>
</t:not-auth-layout>