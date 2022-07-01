<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
    <p>Progect1</p>
    <p>Progect2</p>
<p><%= request.getAttribute("age")%></p>
<p>${age}</p>
    <ol>
        <c:forEach var="project" items="${projects}">
            <li>${project}</li>
        </c:forEach>
    </ol>
</body>
</html>
