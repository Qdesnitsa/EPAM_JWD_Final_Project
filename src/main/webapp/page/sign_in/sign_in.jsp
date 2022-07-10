<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<style>
    <%@include file="/page/sign_in/sign_in.css"%>
</style>
<div class="container">
    <div class="slogan">
        The best solutions<br/>
        for your business
    </div>
    <div class="card">
        <div class="card-header">
            <h2>Sign In</h2>
        </div>
        <div class="card-body">
            <form action="/IT_Team/sign-in" method="post">
                <div class="input-form">
                    <input
                            type="text"
                            required
                            class="form-control"
                            name="email"
                            placeholder="email"
                            pattern="[a-zA-Z]{1}[a-zA-Z\d\u002E\u005F]+@([a-zA-Z]+\u002E){1,2}[a-zA-Z]+"
                    />
                </div>
                <div class="input-form">
                    <input
                            type="password"
                            required
                            name="password"
                            class="form-control"
                            placeholder="password"
                    />
                </div>
                <div class="alert">
                    <c:if test="${not empty error}">
                        <div class="alert">
                                ${error}
                        </div>
                    </c:if>
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
        </div>
    </div>
</div>
