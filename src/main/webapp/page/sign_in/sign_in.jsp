<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/sign_in/sign_in.css"%>
</style>
<t:not-auth-layout title="Sign In">
    <div class="container">
        <div class="slogan">
            The best solutions<br />
            for your business
        </div>
        <div class="card">
            <div class="card-header">
                <h2>Sign In</h2>
            </div>
            <div class="card-body">
                <form>
                    <div class="input-form">
                        <input
                                type="text"
                                required
                                class="form-control"
                                placeholder="email"
                                pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                        />
                    </div>
                    <div class="input-form">
                        <input
                                type="password"
                                required
                                class="form-control"
                                placeholder="password"
                        />
                    </div>
                    <div class="remember"><input type="checkbox" />Remember Me</div>
                    <div class="form-group">
                        <button type="submit" id="submit" class="login_btn">
                            Sign In
                        </button>
                    </div>
                </form>
            </div>
            <div class="card-footer">
                <div class="have-account">
                    Don't have an account?<a href="/IT_Team/sign-up"> Sign Up</a>
                </div>
                <div class="forgot-password">
                    <a href="#">Forgot your password?</a>
                </div>
            </div>
        </div>
    </div>
</t:not-auth-layout>
