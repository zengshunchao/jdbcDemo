/**
 * 登录
 */
function login() {
    //获取表单的值
    var userName = $("#userName").val();
    var passWord = $("#passWord").val();
    var data = {userName: userName, passWord: passWord};
    var url = "/user/login.do"
    //ajax请求
    $.ajax({
        type: 'post',
        data: data,
        url: url,
        dataType: 'json',
        success: function (data) {
            var code = data.resCode;
            if (code == 10000) {
                location.href = "/dataInfo.jsp"
            } else {
                alert("用户名或密码错误，登录失败!")
            }
        }
    })
}

/**
 * 注册
 */
function regist() {

    //获取表单输入的值
    var userName = $(".username").val();
    var password = $(".password").val();
    var className = $(".className").val();
    var phone = $(".phone").val();
    var groupName = $(".groupName").val();

    var data = {userName: userName, passWord: password, className: className, phone: phone, groupName: groupName}
    //请求路径
    var url = "/user/insertUser.do"
    //ajax请求
    $.ajax({
        type: 'post',
        data: data,
        url: url,
        dataType: 'json',
        success: function (data) {
            var code = data.resCode;
            if (code == 10000) {
                alert("注册成功，请登录!");
                location.href = "/login.jsp"
            } else {
                alert("注册失败，请重新注册!");
            }
        }
    })
}