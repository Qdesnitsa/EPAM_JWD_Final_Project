<%@attribute name="title" fragment="false" %>
<%@attribute name="actions" fragment="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>

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
                        <fmt:message key="label.hello"/>, ${user_name} ${user_surname}!
                    </th>
                    <th class="date_header" width="33.33%"><fmt:message key="label.today_date"/> ${current_date}</th>
                    <th class="lang">
                        <form action="controller" method="POST">
                            <button class="lang_btn" type="submit" name="language"
                                    value="ru_RU">RU
                            </button>
                            <button class="lang_btn" type="submit" name="language"
                                    value="en_US">ENG
                            </button>
                            <input type="hidden" name="command" value="select_locale">
                        </form>
                    </th>
                    <th class="sign_out_btn" width="33.33%">
                        <form action="controller" method="POST">
                            <button type="submit" id="sign_out" class="signout_btn" name="command" value="sign_out_post">
                                <fmt:message key="label.sign_out"/>
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
            <h1 class="menu"><fmt:message key="label.menu"/></h1>
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