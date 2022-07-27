<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<style>
    <%@include file="/page/sign_up/sign_up.css"%>
</style>
<div class="container">
    <div class="slogan">
        <fmt:message key="label.the_best_solutions"/><br/>
        <fmt:message key="label.for_your_business"/>
    </div>
    <div class="card">
        <div class="card-header">
            <h2><fmt:message key="label.registration_form"/>
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
                    <input type="text"
                           required class="name"
                           placeholder="<fmt:message key="label.name"/>"
                           name="name"/>
                </div>
                <div class="input-form">
                    <input
                            type="text"
                            required
                            class="surname"
                            placeholder="<fmt:message key="label.surname"/>"
                            name="surname"
                    />
                </div>
                <div class="input-form">
                    <input
                            type="text"
                            required
                            class="email"
                            placeholder="<fmt:message key="label.email"/>"
                            pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                            name="email"
                    />
                </div>
                <div class="input-form">
                    <input
                            type="password"
                            required
                            class="password"
                            placeholder="<fmt:message key="label.password"/>"
                            name="password"
                    />
                </div>
                <div class="input-form">
                    <input
                            type="password"
                            required
                            class="check_password"
                            placeholder="<fmt:message key="label.repeat_password"/>"
                    />
                </div>
                <div class="error"></div>
                <div class="alert">
                    <c:if test="${not empty error}">
                        <div>
                                ${error}
                        </div>
                    </c:if>
                    <input type="hidden" name="command" value="sign_up_post">
                    <button type="submit" id="submit" class="login_btn">
                        <fmt:message key="label.sign_up"/>
                    </button>
                </div>
            </form>
        </div>
        <div class="card-footer">
            <div class="have-account">
                <fmt:message key="label.have_account"/>
                <a href="/IT_Team/controller?command=sign_in_get"> <fmt:message key="label.sign_in"/></a>
            </div>
        </div>
    </div>
</div>
<script
        src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
        crossorigin="anonymous"
></script>
<script>
    $(document).ready(function ($) {
        $(".check_password").on("keyup", function () {
            var input1 = $(".password").val();
            var input2 = $(".check_password").val();

            if (input1 != "" && input2 === "") {
                $(".check_password").css("backgroundColor", "white");
                $(".password").css("backgroundColor", "white");
            } else if (input1 != input2) {
                $(".error").html("Passwords do not match. Please try again.");
                $("#submit").attr("disabled", "disabled");
                $(".check_password").css("backgroundColor", "lightpink");
                $(".password").css("backgroundColor", "lightpink");
            } else {
                $("#submit").removeAttr("disabled");
                $(".error").html("");
                $(".check_password").css("backgroundColor", "white");
                $(".password").css("backgroundColor", "white");
            }
        });
    });

    var button = document.querySelector("button");
    var input = document.querySelector("input");

    button.addEventListener(
        "click",
        function () {
            if (input.value === "") {
                $(".error").html("All fields are mandatory.");
            }
        },
        false
    );
</script>
