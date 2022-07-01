<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/sign_up/sign_up.css"%>
</style>
<t:not-auth-layout title="Sign Up">
    <div class="container">
        <div class="slogan">
            The best solutions<br/>
            for your business
        </div>
        <div class="card">
            <div class="card-header">
                <h2>Registration form</h2>
            </div>
            <div class="card-body">
                <form action="/IT_Team/sign-up" method="POST">
                    <div class="input-form">
                        <input type="text"
                               required class="name"
                               placeholder="name"
                                name="name"/>
                    </div>
                    <div class="input-form">
                        <input
                                type="text"
                                required
                                class="surname"
                                placeholder="surname"
                                name="surname"
                        />
                    </div>
                    <div class="input-form">
                        <input
                                type="text"
                                required
                                class="email"
                                placeholder="email"
                                pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                                name="email"
                        />
                    </div>
                    <div class="input-form">
                        <input
                                type="password"
                                required
                                class="password"
                                placeholder="password"
                                name="password"
                        />
                    </div>
                    <div class="input-form">
                        <input
                                type="password"
                                required
                                class="check_password"
                                placeholder="repeat password"
                        />
                    </div>
                    <div class="error"></div>
                    <div class="remember"><input type="checkbox"/>Remember Me</div>
                    <div class="form-group">
                        <button type="submit" id="submit" class="login_btn">
                            Sign up
                        </button>
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <div class="have-account">
                    Already have an account?<a href="/IT_Team/sign-in"> Sign In</a>
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
</t:not-auth-layout>