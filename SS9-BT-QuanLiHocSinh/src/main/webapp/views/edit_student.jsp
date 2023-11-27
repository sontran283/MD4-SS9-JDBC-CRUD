<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/27/2023
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<body>
<p>
    <a href="/student">Back to student list</a>
</p>
<h1 class="text-center text-danger">Sửa học sinh</h1>
<form action="<%=request.getContextPath()%>/student" method="POST">
    <div class="form-group">
        <input type="hidden" name="id" value="${student.id}">
        <label for="name">name: </label>
        <input type="text" class="form-control" id="name" name="name" value="${student.name}">
    </div>
    <div class="form-group">
        <label for="email">email: </label>
        <input type="text" class="form-control" id="email" name="email" value="${student.email}">
    </div>
    <div class="form-group">
        <label for="birthday">birthday: </label>
        <input type="date" class="form-control" id="birthday" name="birthday" value="${student.birthday}">
    </div>
    <div class="form-group">
        <label for="address">address: </label>
        <input type="text" class="form-control" id="address" name="address" value="${student.address}">
    </div>
    <button type="submit" class="btn btn-primary" value="edit" name="action">Edit</button>
</form>
</body>
</html>
