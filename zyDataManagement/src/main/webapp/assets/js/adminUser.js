function login() {
    var username = $(".username").val();
    var password = $(".password").val();
    var data = {adminName: username, adminPassWord: password};
    var url = "/admin/login.do";
    $.ajax({
        type: 'post',
        data: data,
        url: url,
        dataType: 'json',
        success: function (data) {
            var code = data.resCode;
            if (code == 10000) {
                sessionStorage.setItem("userName", username);
                location.href = "/index.jsp"
            } else {
                alert("用户名或密码错误，登录失败!")
            }
        }
    })
}