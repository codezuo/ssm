<%--
  Created by IntelliJ IDEA.
  User: 左军
  Date: 2019/6/28
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
   <h1>查询所有账户信息</h1></br>
       <c:forEach items="${list}" var="account">
        ${account.toString()}
        </c:forEach>
</body>
</html>
