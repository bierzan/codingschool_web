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
            <th>TYTUŁ</th>
            <td>${sol.exercise.title}</td>
        </tr>
        <tr>
            <th>TREŚĆ</th>
            <td>${sol.exercise.description}</td>
        </tr>
        <tr>
            <th>ROZWIĄZANIE</th>
            <td>${sol.description}</td>
        </tr>
        <tr>
            <th>AUTOR</th>
            <td>${sol.user.username}</td>
            <td>grupa: ${sol.user.group.name}</td>
        </tr>
        <tr>
            <th>DATA PRZESŁANIA</th>
            <td>${sol.created}</td>
        </tr>
        <tr>
            <th>DATA AKTUALIZACJI</th>
            <td>${sol.updated}</td>
        </tr>
    </table>
</div>
<div class="container">

    <%@include file="footer.jspf" %>
</div>

</body>
</html>
