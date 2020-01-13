var isLogin=false;

$(function () {
    var v_navHtml = "<nav class=\"navbar navbar-inverse\">\n" +
        "    <div class=\"container-fluid\">\n" +
        "        <!-- Brand and toggle get grouped for better mobile display -->\n" +
        "        <div class=\"navbar-header\">\n" +
        "           <ul> <li><a class=\"navbar-brand\" href='/'>飞狐电商前台666768</a></li></ul>\n" +
        "        </div>\n" +
        "\n" +
        "        <!-- Collect the nav links, forms, and other content for toggling -->\n" +
        "        <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">" +
        "            <ul class=\"nav navbar-nav navbar-right\" id='nav_ul'>" +
        "                <li id=\"loginInfo\"><a href=\"/login.html\">登录</a></li>" +
        "                <li><a href=\"/view/reg.html\">注册</a></li>" +
        "                <li><a href=\"/view/cart-1.html\">购物车</a></li>" +
        "            </ul>\n" +
        "        </div><!-- /.navbar-collapse -->\n" +
        "    </div><!-- /.container-fluid -->\n" +
        "</nav>";

    $("#navDiv").html(v_navHtml);

    $.ajaxSetup({
        beforeSend: function(xhr) {
            var token = window.localStorage.getItem("token");
            console.log(":token"+token);
            xhr.setRequestHeader("x-auth", token);
        }
    })

    $.ajax({
        type:"get",
        url:"http://localhost:8083/member/getMember",
        async:false,
        success:function (result) {
            if (result.status == 200) {
                isLogin = true;
                $("#loginInfo").html("<a>欢迎"+result.data+"登录！！！</a>")
                $("#nav_ul").append("<li><a href='#' onclick='logout();'>退出</a></li>");
            }
        }

    })

})

var loginDivHtml = '\n' +
    '    <form class="form-horizontal" >\n' +
    '        <div class="form-group">\n' +
    '            <label  class="col-sm-2 control-label">用户名</label>\n' +
    '            <div class="col-sm-8">\n' +
    '                <input type="text" class="form-control"   id="memberName" placeholder="请输入登录名...">\n' +
    '            </div>\n' +
    '        </div>\n' +
    '\n' +
    '        <div class="form-group">\n' +
    '            <label  class="col-sm-2 control-label">密码</label>\n' +
    '            <div class="col-sm-8">\n' +
    '                <input type="password" class="form-control"  id="passWord" placeholder="请输入密码...">\n' +
    '            </div>\n' +
    '        </div>\n' +
    '\n' +
    '    </form>\n' +
    '';

function buy(productId,count){
    if(isLogin){//判断是否已登录
        $.post(
            "http://localhost:8083/cart/add",
            {"productId":productId,"count":count},
            function(data){
                if(data.status==200){
                    window.location.href="/view/cart-1.html"
                }
            }
        )
    }else{
        login();
    }

}
function login(){
    bootbox.dialog({
        message: loginDivHtml,
        title: "登录",
        backdrop:false,
        buttons: {
            Cancel: {
                label: "取消",
                className: "btn-default",
                callback: function () {

                }
            }
            , OK: {
                label: "确认",
                className: "btn-danger",
                callback: function () {
                    var param={};
                    param.memberName=$("#memberName").val();
                    param.passWord=$("#passWord").val();
                    $.post(
                        "http://localhost:8083/member/login",
                        param,
                        function (data) {
                            if(data.status==200){
                                isLogin=true;
                                console.log(data.data);
                                window.localStorage.setItem("token",data.data);
                                // queryList();
                            }else{
                                alert("操作失败！"+data.msg,function(){
                                })
                            }

                        }
                    )
                }
            }
        }
    });
}






