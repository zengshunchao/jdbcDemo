$(function () {
    getFileList();
})

//点击上传文件
function uploadFile() {
    var loginname = $("#loginUser").html();
    //判断是否登录

    if (loginname == null || loginname == "") {
        location.href = "/login.jsp";
    } else {
        location.href = "/upload.jsp"
    }

}

//点击登录
function onLogin() {
    location.href = "/login.jsp";
}

//获取列表数据
function getFileList() {
    var dataName = "";
    var groupName = "";
    //获取选中状态的值
    var radio = $('input[name="findName"]:checked').val()
    var text = $("#look").val();

    if (radio == 1) {
        dataName = text;
    } else {
        groupName = text;
    }
    var data = {dataName: dataName, groupName: groupName}
    var url = "/file/getFileList.do";
    $.ajax({
        type: 'post',
        data: data,
        url: url,
        dataType: 'json',
        success: function (data) {
            var str = "";
            for (var i = 0; i < data.resList.length; i++) {
                var file = data.resList[i];
                str += "<tr>"
                str += "<td>" + file.groupName + "</td>";
                str += '<td><a href="/file/getById.do?id=' + file.id + '">' + file.dataName + '</a></td>';
                str += "<td>" + file.ipPath + "</td>";
                str += "<td>" + file.userName + "</td>";
                str += "<td>" + file.createDate + "</td>";
                str += "<td>" + file.updateDate + "</td>";
                str += "<td>" + file.lookNumber + "</td>";
                str += "</tr>";
            }
            $("#tableData").html(str);

        }
    })
}