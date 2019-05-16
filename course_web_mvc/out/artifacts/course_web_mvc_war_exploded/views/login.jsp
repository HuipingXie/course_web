<%--
  Created by IntelliJ IDEA.
  User: xiehuiping
  Date: 2019/5/14
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../statics/icon/favicon.ico">

    <title>登录</title>

    <!-- Bootstrap core CSS -->
    <link href="../statics/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../statics/css/floating-labels.css" rel="stylesheet">
    <script src="../statics/js/jquery-3.3.1.js"></script>
    <script src="../statics/js/bootstrap.js"></script>
</head>

<body>
<form class="form-signin" action="/course_web_mvc/login/doLogin" method="post">

    <div class="text-center mb-4">
        <h1 class="h3 mb-3 font-weight-normal">登录</h1>
    </div>

    <div class="alert alert-danger alert-dismissible" hidden>
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>错误!</strong> 失败的操作。
    </div>

    <div class="form-label-group">
        <p>用户类型:</p>
        <select class="custom-select d-block w-100" id="user_type" name="user_type">
            <option value="student">学生</option>
            <option value="teacher">老师</option>
        </select>
    </div>

    <div class="form-label-group">
        <input type="text" id="inputEmail" class="form-control" name="username" placeholder="用户名" required autofocus>
        <label for="inputEmail">用户名</label>
    </div>

    <div class="form-label-group">
        <input type="password" id="inputPassword" class="form-control" name="password" placeholder="密码" required>
        <label for="inputPassword">密码</label>
    </div>

    <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
    <p class="mt-5 mb-3 text-muted text-center">&copy; 2017-2018</p>
</form>
</body>
</html>
