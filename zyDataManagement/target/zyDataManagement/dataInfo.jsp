<%@ page import="com.zy.entity.FmsUser" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title></title>
    <style>
        td {
            background-color: gainsboro;
            color: black;
            text-align: center;
            border: 1px solid white;
            height: 50px;
        }

        th {
            background-color: cornflowerblue;
            color: white;
            width: 100px;
            height: 60px;
            border: 1px solid white;
        }

        textarea {
            padding-right: 14px;
            box-sizing: border-box;
            font-size: 1.2em;
            height: 8em;
            border-radius: 4px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
            display: block;
            outline: 0;
            padding: 0 1em;
            text-decoration: none;
            width: 100%;
        }

        table {
            width: 100%;
        }

        #rt {
            float: right;
            display: none;
        }

        ul {
            list-style-type: none;
        }

        li {
            float: right;
        }

        input[type="submit"] {
            background-color: white;
            box-sizing: border-box;
            font-size: 1em;
            height: 2em;
            border-radius: 4px;
            border: 1px solid blue;
            color: black;
        }

        .abc {
            text-align: right;
        }

        .zy {
            text-align: right;
        }
    </style>
    <script src="assets/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="assets/js/dataInfo.js"></script>
    <script type="text/javascript" src="assets/js/user.js"></script>
</head>
<body>
<div id="loginCheck">
    <p id="abc" class="abc">您还未登录&nbsp;<button type="button" onclick="onLogin()">点击登录</button>
    </p>
    <p id="zy" class="zy">欢迎您，<span id="loginUser"></span>
        <input type="hidden" id="role" name="role" value=""/>
    </p>
</div>
<center>
    <h2>
        分享会资料管理平台
    </h2>
</center>
<%--<form>--%>
<p>
    <input type="radio" checked name="findName" value="1">&nbsp;按资料名称&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="radio" name="findName" value="2">&nbsp;按小组名称 &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="text" id="look">&nbsp;
    <button type="button" onclick="getFileList()">搜索</button>
    <button type="button" onclick="uploadFile()">上传资料</button>
</p>
<input type="text" id="rt">
<table>
    <thead>
    <tr>
        <th>小组名称</th>
        <th>资料名称</th>
        <th>IP</th>
        <th>上传人</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <th>浏览数</th>
    </tr>
    </thead>
    <tbody id="tableData"></tbody>
</table>
<%--</from>--%>

<script type="text/javascript">
    //页面加载完成执行
    $(function () {
        var loginName = "${sessionScope.user.getUserName()}";
        var role = "${sessionScope.user.getRole()}";
        //判断是否有用户登录
        if (loginName == null || loginName == "") {
            $("#abc").show();
            $("#zy").hide();
        } else {
            $("#loginUser").html(loginName);
            $("#role").val(role);
            $("#zy").show();
            $("#abc").hide();
        }
    })


</script>
</body>

</html>
