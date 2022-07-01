<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/IT_Team/add_project">
<input
        type="text"
        name="shortname"
        placeholder="shortname"
        class="y" />
<textarea
        name="description"
        placeholder="description"
        class="z">

</textarea>
    <button type="submit">
        Submit
    </button>
</form>

</body>
</html>
