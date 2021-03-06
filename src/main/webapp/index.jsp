<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CODING SCHOOL</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%@include file="header.jspf" %>
</div>
<div class="container">
    <table class="table">
        <tr>
            <th>NAZWA ZADANIA</th>
            <th>OSTATNIE ROZWIĄZANIE</th>
            <th>UŻYTKOWNIK</th>
            <th></th>
        </tr>
        <c:forEach items="${solutions}" var="sol">
            <tr>
                <td>${sol.exercise.title}</td>
                <td>
                    <c:if test="${empty sol.updated}">
                        ${sol.created}
                    </c:if>
                    <c:if test="${not empty sol.updated}">
                        ${sol.updated}
                    </c:if>
                </td>
                <td>${sol.user.username}</td>
                <td><a href="/SolutionDetails?solId=${sol.id}">Pokaż</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="container">

    <%@include file="footer.jspf" %>
</div>

</body>
</html>
