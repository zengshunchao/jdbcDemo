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
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8"/>
    <meta charset="UTF-8">
    <title></title>
    <style>

        table {
            width: 80%;
            height: 80%;
        }

        textarea {
            padding-right: 14px;
            box-sizing: border-box;
            font-size: 1.2em;
            height: 6em;
            border-radius: 4px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
            display: block;
            outline: 0;
            padding: 0 1em;
            text-decoration: none;
        }

        select {

            padding-right: 14px;
            box-sizing: border-box;
            font-size: 1em;
            height: 2em;
            border-radius: 4px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
            display: block;
            outline: 0;
            padding: 0 1em;
            text-decoration: none;
        }

        input[type="text"] {
            box-sizing: border-box;
            font-size: 1em;
            height: 2em;
            border-radius: 4px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
            -webkit-appearance: none;
            -moz-appearance: none;
            display: block;
            outline: 0;
            padding: 0 1em;
            text-decoration: none;
        }

        input[type="submit"] {
            box-sizing: border-box;
            font-size: 1em;
            height: 2em;
            border-radius: 4px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
        }

        input[type="reset"] {
            box-sizing: border-box;
            font-size: 1em;
            height: 2em;
            border-radius: 4px;
            border: 1px solid #c8cccf;
            color: #6a6f77;
        }

        input[type="text"]:focus {
            border: 1px solid #ff7496;
        }

        ::-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #6a6f77;
        }

        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #6a6f77;
        }

        input::-webkit-input-placeholder {
            color: #6a6f77;
        }
    </style>
</head>
<body>
<form id="formUpload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td></td>
            <td align="right">
                <input type="text" style="display:none">
            </td>
    </table>
    <h2>上传界面：</h2>
    <table>
        </tr>
        <tr>
            <td align="center">资料名称</td>
            <td><input type="text" id="dataName" name="dataName" style="width:100%"></td>
        </tr>
        <tr>
            <td align="center">小组名称</td>
            <td><input type="text" id="groupName" name="groupName" style="width:100%"></td>
            </select>
            </td>
        </tr>
        <tr>
            <td align="center">上传人</td>
            <td><input type="text" id="userName" value="${sessionScope.user.getUserName()}"
                       style="width:100%">
                <%--<input type="hidden" id="userId" name="userId" value="${sessionScope.user.getId()}" style="width:100%">--%>
            </td>
        </tr>
        <tr>
            <td align="center">资料简介</td>
            <td><textarea id="desc" name="dataDesc" class="input-t" style="width:100%"></textarea></td>
        </tr>
        <tr>
            <td align="center">上传附件</td>
            <td><input type="file" name="file" style="width:40%"></td>
        </tr>
        <tr>
            <td></td>
            <td align="right">
                <input type="button" onclick="upload()" value="立即创建">
                <input type="button" value="取消" onclick="returnInfo()">
            </td>
        </tr>
    </table>
    </from>
    <script type="text/javascript" src="assets/js/jquery-1.8.2.min.js"></script>
    <script type="text/javascript">
        //上传
        function upload() {
            //获取表单数据
            var form = new FormData(document.getElementById("formUpload"));
            $.ajax({
                url: "/file/upload.do",
                type: "post",
                data: form,
                processData: false,
                contentType: false,
                success: function (data) {
                    alert("上传成功！");
                    location.href = "/dataInfo.jsp";
                },
                error: function (e) {
                    alert("上传失败！");
                    console.log(e);
                }
            });
        }

        //取消
        function returnInfo() {
            location.href = "/dataInfo.jsp"
        }
    </script>
</body>
</html>
