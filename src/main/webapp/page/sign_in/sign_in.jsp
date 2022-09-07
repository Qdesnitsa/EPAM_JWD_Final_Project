<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/sign_in/sign_in.css"%>
</style>
<div class="container">
    <div class="slogan">
        <fmt:message key="label.the_best_solutions"/><br/>
        <fmt:message key="label.for_your_business"/>
    </div>
    <div class="card">
        <div class="card-header">
            <h2><fmt:message key="label.sign_in"/>
                <form action="controller" method="POST">
                        <span class="language">
                            <button class="lang_btn" type="submit" name="language"
                                    value="ru_RU">Русский
                            </button>
                            <button class="lang_btn" type="submit" name="language"
                                    value="en_US">English (US)
                            </button>
                            <input type="hidden" name="command" value="select_locale">
                        </span>
                </form>
            </h2>
        </div>
        <div class="card-body">
            <form action="controller" method="POST">
                <div class="input-form">
                    <input
                            type="text"
                            required
                            class="form-control"
                            name="email"
                            placeholder="<fmt:message key="label.email"/>"
                            pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                    />
                </div>
                <div class="input-form">
                    <input
                            type="password"
                            required
                            name="password"
                            class="form-control"
                            placeholder="<fmt:message key="label.password"/>"
                    />
                </div>
                <div class="alert">
                    <c:if test="${message_invalid_input != null}">
                        <div>
                            <fmt:message key="label.message_invalid_input"/>
                        </div>
                    </c:if>
                    <input type="hidden" name="command" value="sign_in_post">
                    <button type="submit" id="submit" class="login_btn">
                        <fmt:message key="label.sign_in"/>
                    </button>
                </div>
            </form>
        </div>
        <div class="card-footer">
            <div class="have-account">
                <fmt:message key="label.not_have_account"/>
                <a href="/IT_Team/controller?command=sign_up_get"> <fmt:message key="label.sign_up"/></a>
            </div>
        </div>
    </div>
</div>