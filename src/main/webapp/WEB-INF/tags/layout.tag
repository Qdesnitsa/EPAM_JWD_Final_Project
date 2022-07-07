<%@attribute name="title" fragment="false" %>
<%@attribute name="actions" fragment="true" %>
<html>
<title>${title}</title>
<body>

<style>
    <%@include file="/page/css/styles.css"%>
</style>
<div class="container">
    <div class="header">
        <h2>
            <table width="100%">
                <tbody>
                <tr>
                    <th class="greeting" width="33.33%" ;>
                        Hello, ${user_name} ${user_surname}!
                    </th>
                    <th class="date_header" width="33.33%">Today is Date_now</th>
                    <th class="sign_out_btn" width="33.33%">
                        <form method="post" action="/IT_Team/sign-out">
                            <button type="submit" id="sign_out" class="signout_btn">
                                Sign out
                            </button>
                        </form>
                    </th>
                </tr>
                </tbody>
            </table>
        </h2>
    </div>

    <div class="content">
        <div class="navigation">
            <h1 class="menu">Menu</h1>
            <jsp:invoke fragment="actions"/>
        </div>
    </div>
    <div class="article">
        <jsp:doBody/>
    </div>
</div>
<div class="footer">
    &copy; IT-Teams Studio (Elena Sidina). All rights reserved. 2022
</div>

</body>
</html>