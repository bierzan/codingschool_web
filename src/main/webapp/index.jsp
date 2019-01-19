<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CODING SCHOOL</title>
</head>
<body>
<div>
    <table>
        <c:forEach items="${solutions}" var="sol">
            <tr>
                <td>${sol.created}</td>
                <td>${sol.description}</td>
                <td>${sol.user.username}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
