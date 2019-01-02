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
    <meta charset="{CHARSET}">
    <title></title>
    <style>
        .main {
            width: 80%;
            height: 200px;
        }

        table caption {
            text-align: left;
        }

    </style>
    <script src="assets/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<input type="hidden" id="id" name="id" value="${file.id}"/>
<input type="hidden" id="userId" name="userId" value="${file.userId}"/>
<center>
    <table border="1" class="main">
        <caption>资料简介</caption>
        <tr>
            <td>
                <textarea name="desc" id="data" cols="200" rows="12">${file.dataDesc}</textarea></td>
        </tr>
    </table>
    <button type="button" id="deleteData" onclick="delData()">删除</button>
    <button type="button" id="updateData" onclick="updateDate()">修改资料</button>
    <button type="button" id="downLoad" onclick="downLoad()">附件下载</button>
    <button type="button" onclick="returnBack()">返回</button>

</center>
<script type="text/javascript">
    //返回
    function returnBack() {
        location.href = "/dataInfo.jsp";
    }

    //判断是否拥有删除修改权限
    $(function () {
        var userId = $("#userId").val();
        var role = "${sessionScope.user.getRole()}";
        var loginUserId = "${sessionScope.user.getId()}";
        if (loginUserId == null || loginUserId.length == 0) {
            $("#deleteData").hide();
            $("#updateData").hide();
        } else {
            //当前登录用户是否和文件上传用户一致
            if (userId == loginUserId) {
                $("#deleteData").show();
                $("#updateData").show();
                //是否管理员
            } else if (role == 0) {
                $("#deleteData").show();
                $("#updateData").show();
            } else {
                $("#deleteData").hide();
                $("#updateData").hide();
            }
        }

    })

    //点击下载
    function downLoad() {
        var fileId = $("#id").val();
        location.href = "/file/download.do?fileId=" + fileId;
    }

    //删除文件
    function delData() {
        var fileId = $("#id").val();
        var data = {id: fileId}
        var url = "/file/deleteById.do"
        $.ajax({
            type: 'post',
            data: data,
            url: url,
            dataType: 'json',
            success: function (data) {
                var code = data.resCode;
                if (code == 10000) {
                    alert("删除成功!")
                    location.href = "/dataInfo.jsp"
                } else {
                    alert("删除失败!")
                }
            }
        })
    }

    function updateDate() {
        var fileId = $("#id").val();
        var text = $("#data").val();
        alert(text);
        var data = {dataDesc: text, id: fileId}
        var url = "/file/updateDesc.do"
        $.ajax({
            type: 'post',
            data: data,
            url: url,
            dataType: 'json',
            success: function (data) {
                var code = data.resCode;
                if (code == 10000) {
                    alert("修改成功!")
                    location.href = "/dataInfo.jsp"
                } else {
                    alert("修改失败!")
                }
            }
        })
    }
</script>
</body>
</html>
