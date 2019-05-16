<%--
  Created by IntelliJ IDEA.
  User: xiehuiping
  Date: 2019/5/14
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../statics/icon/favicon.ico">

    <title>选课</title>

    <!-- Bootstrap core CSS -->

    <link href="../statics/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../statics/css/album.css" rel="stylesheet">
    <link rel="stylesheet" href="../statics/css/dashboard.css">
    <script src="../statics/js/jquery-3.3.1.js"></script>
    <script src="../statics/js/jquery.dataTables.min.js"></script>
    <script src="../statics/js/dataTables.bootstrap4.min.js"></script>
    <!--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">-->
    <link rel="stylesheet" href="../statics/css/dataTables.bootstrap4.min.css">
    <!---->

</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark sticky-top  bg-dark">
    <a class="navbar-brand" href="#">选课系统</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor02">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="../login/login">登录</a>
            </li>
        </ul>
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link disabled" href="">${username}</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <a class="btn btn-secondary my-2 my-sm-0" href="../login/logout">
                退出登录
            </a>
        </form>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar" style="top:11.5%;">
            <div class="sidebar-sticky">
                <ul class="nav flex-column list-group">
                    <li class="nav-item list-group-item">
                        <a class="nav-link" href="index">
                            <span data-feather="home"></span>
                            主页<span class="sr-only"></span>
                        </a>
                    </li>
                    <li class="nav-item list-group-item">
                        <a class="nav-link" href="selectCourses">
                            <span data-feather="book"></span>
                            选课界面
                        </a>
                    </li>
                    <li class="nav-item list-group-item">
                        <a class="nav-link active" href="selectedCourses">
                            <span data-feather="book"></span>
                            已选课程
                        </a>
                    </li>
                    <li class="nav-item list-group-item">
                        <a class="nav-link" href="getScore">
                            <span data-feather="align-justify"></span>
                            查看成绩
                        </a>
                    </li>
                </ul>

            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">

            <h4>选课</h4>
            <div class="row m-2"></div>
            <table class="table table-striped table-bordered table-hover" id="example">
                <thead>
                <tr>
                    <th>课程名</th>
                    <th>教师</th>
                    <th>教室</th>
                    <th>时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach  items="${timetableInfoList}" var="ti" varStatus="loop">
                    <tr class="odd gradeX">
                        <td>
                            <a href="courseInfo?timetable_id=${ti.timetableID}">${ti.course.courseName}</a>
                        </td>
                        <td>${ti.teacher.name}</td>
                        <td>${ti.classroom}</td>
                        <td class="center">${ti.courseTime}</td>
                        <td class="center">
                            ${ti.courseOrder}
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

            <div class="m-5"></div>

        </main>
    </div>
</div>





<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->


<script src="../statics/assets/js/vendor/popper.min.js"></script>
<script src="../statics/js/bootstrap.min.js"></script>
<script src="../statics/assets/js/vendor/holder.min.js"></script>
<script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
<script>
    feather.replace()
</script>

<script>
    $(document).ready(function() {
        $('#example').DataTable();
    } );
</script>
<!--动态表格特有的js end-->



</body>
</html>