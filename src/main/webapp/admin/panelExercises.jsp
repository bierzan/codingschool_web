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
<c:if test="${empty edit}">
    <div class="container">
        <form action="/PanelExercises" method="post">
            <input type="text" name="title" placeholder="Tytuł zadnia" class="form-control">
            <textarea rows="4" placeholder="Opis zadania" name="description" class="form-control"
                      style="resize:vertical"></textarea>
            <input type="submit" name="add" value="Dodaj" class="btn" style="float: right">
        </form>

    </div>
</c:if>


<div class="container">
    <table class="table table-condensed">
        <tr>
            <th>ID</th>
            <th>TYTUŁ</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${exercises}" var="ex">
            <c:if test="${edit != ex.id}">
                <tr style="background-color: lightgray">
                    <td>${ex.id}</td>
                    <td>${ex.title}</td>
                    <td></td>

                    <td><a href="/PanelExercises?editExId=${ex.id}">Edytuj</a></td>
                </tr>
                <tr>
                    <td colspan="3">${ex.description}</td>
                </tr>
            </c:if>

            <c:if test="${edit==ex.id}">
                <form action="/PanelExercises" method="post">
                    <tr style="background-color: lightgray">

                        <td>${ex.id}</td>

                        <td><input type="text" name="newTitle" value="${ex.title}" class="form-control"></td>
                        <td></td>

                        <td>
                            <button type="submit" name="editExId" value="${ex.id}" class="btn btn-primary">Zapisz
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <textarea rows="4" name="newDescription" class="form-control"
                                      style="resize:vertical">${ex.description}</textarea>
                        </td>
                    </tr>
                </form>

            </c:if>

        </c:forEach>

    </table>
</div>
<div class="container">

    <%@include file="/footer.jspf" %>
</div>

</body>
</html>
