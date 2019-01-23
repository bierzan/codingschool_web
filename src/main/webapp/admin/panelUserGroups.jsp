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

    <%@include file="/header.jspf" %>
    <%@include file="/admin/adminHeader.jspf" %>
</div>


<div class="container">
    <table class="table table-condensed">
        <tr>
            <th>NUMER ID</th>
            <th>NAZWA</th>
            <th></th>
        </tr>
        <tr>
            <form action="/PanelUserGroups" method="post">
                <td>

                </td>
                <td>
                    <input type="text" name="groupName" placeholder="Nazwa nowej grupy">
                </td>
                <td><input type="submit" name="add" value="Dodaj" class="btn"></td>
            </form>

        </tr>

        <c:forEach items="${groups}" var="group">
            <tr>
                <td>${group.id}</td>
                <td>${group.name}</td>
                <td><a href="/UsersByGroup?groupId=${group.id}">Pokaż użytkowników</a></td>
                    <%--todo dorobić edycje grup--%>
            </tr>
        </c:forEach>

    </table>
</div>
<div class="container">

    <%@include file="/footer.jspf" %>
</div>

</body>
</html>
