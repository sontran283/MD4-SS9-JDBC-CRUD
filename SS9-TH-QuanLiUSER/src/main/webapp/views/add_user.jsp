<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 11/25/2023
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    /* Add your styles here */
    body {
        font-family: Arial, sans-serif;
    }

    .container {
        max-width: 600px;
        margin: 0 auto;
    }

    .form-group {
        margin-bottom: 15px;
    }

    label {
        display: block;
        margin-bottom: 5px;
    }

    input {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
    }

    button {
        padding: 10px 15px;
        background-color: #007BFF;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    button:hover {
        background-color: #0056b3;
    }
</style>
<body>
<p>
    <a href="/danh_sach">Back to user list</a>
</p>
<h1 class="text-center text-danger">Thêm mới User</h1>
<form action="<%=request.getContextPath()%>/danh_sach" method="POST">
    <div>
        <label for="name">name: </label>
        <input type="text" class="form-control" id="name" name="name">
    </div>
    <div class="form-group">
        <label for="email">email: </label>
        <input type="text" class="form-control" id="email" name="email">
    </div>
    <div class="form-group">
        <label for="country">country: </label>
        <input type="text" class="form-control" id="country" name="country">
    </div>
    <button type="submit" class="btn btn-primary">Add</button>
</form>
</body>
</html>
