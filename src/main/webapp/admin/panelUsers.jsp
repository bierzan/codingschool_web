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
            <th>ID</th>
            <th>NAZWA</th>
            <th>E-MAIL</th>
            <th>GRUPA</th>
            <th></th>
        </tr>
        <tr>
            <form action="/PanelUsers" method="post">
                <td></td>
                <td>
                    <input type="text" name="username" placeholder="Nazwa nowego użytkownika" class="form-control">
                    <input type="password" name="password" placeholder="Hasło" class="form-control">
                </td>
                <td>
                    <input type="email" name="email" placeholder="e-mail" class="form-control">
                </td>
                <td>
                    <select class="form-control" name="group">
                        <option value="0">...</option>
                        <c:forEach items="${groups}" var="group">
                            <option value="${group.id}">${group.name}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="submit" name="add" value="Dodaj" class="btn"></td>
                <%--todo: przerobić formularz + zrobić stronę pośrednią lub pole koło "dodaj" które pyta o hasło--%>
            </form>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <c:if test="${edit == user.id}">
                    <td>${user.id}</td>
                    <form action="/PanelUsers" method="post">
                        <td>
                            <input type="text" name="newUsername" value="${user.username}">
                            <input type="password" name="newPassword">
                        </td>
                        <td>
                            <input type="email" name="newEmail" value="${user.email}">
                        </td>
                        <td>
                            <select class="form-control" name="newGroupId">
                                <option value="0">...</option>
                                <c:forEach items="${groups}" var="group">
                                    <option value="${group.id}">${group.name}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <td>
                            <button type="submit" name="editUserId" value="${user.id}" class="btn">Zapisz</button>
                        </td>
                    </form>

                </c:if>
                <c:if test="${edit != user.id}">
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.group.name}</td>
                    <td><a href="/PanelUsers?editUserId=${user.id}">Edytuj</a></td>
                </c:if>

            </tr>
        </c:forEach>

    </table>
</div>
<div class="container">

    <%@include file="/footer.jspf" %>
</div>

</body>
</html>
