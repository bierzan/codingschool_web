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
    <h3>DANE UŻYTKOWNIKA</h3>


    <table class="table">
        <tr>
            <th style="width:25%">ID</th>
            <td>${user.id}</td>
        </tr>
        <tr>
            <th>NAZWA</th>
            <td>${user.username}</td>
        </tr>
        <tr>
            <th>E-MAIL</th>
            <td>${user.email}</td>
        </tr>
        <tr>
            <th>GRUPA</th>
            <td>${user.group.name}</td>
        </tr>
    </table>
</div>

<div class="container">
    <h3>ZADANIA ROZWIĄZANE PRZEZ UŻYTKOWNIKA</h3>
    <c:forEach items="${solutions}" var="sol">
        <table class="table">

            <tr>
                <th style="width:25%">TYTUŁ</th>
                <td colspan="3">${sol.exercise.title}</td>
            </tr>
            <tr>
                <th>TREŚĆ</th>
                <td colspan="3">${sol.exercise.description}</td>
            </tr>
            <tr>
                <th>ROZWIĄZANIE</th>
                <td colspan="3">${sol.description}</td>
            </tr>
            <tr>
                <th>DATA PRZESŁANIA</th>
                <td>${sol.created}</td>
                <c:if test="${not empty sol.updated}">
                    <th>DATA AKTUALIZACJI</th>
                    <td>${sol.updated}</td>
                </c:if>

            </tr>


        </table>
    </c:forEach></div>
<div class="container">
    <%@include file="footer.jspf" %>
</div>

</body>
</html>
