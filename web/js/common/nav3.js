var isLogin=false;

$(function () {
    var v_navHtml = "<nav class=\"navbar navbar-inverse\">\n" +
        "    <div class=\"container-fluid\">\n" +
        "        <!-- Brand and toggle get grouped for better mobile display -->\n" +
        "        <div class=\"navbar-header\">\n" +
        "           <ul> <li><a class=\"navbar-brand\" href='index-2.html'>飞狐电商前台666</a></li></ul>\n" +
        "        </div>\n" +
        "\n" +
        "        <!-- Collect the nav links, forms, and other content for toggling -->\n" +
        "        <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">" +
        "            <ul class=\"nav navbar-nav navbar-right\" id='nav_ul'>" +
        "                <li id=\"loginInfo\"><a href=\"/view/login.html\">登录</a></li>" +
        "                <li><a href=\"/view/register.html\">注册</a></li>" +
        "                <li><a href=\"/view/cart-1.html\">购物车</a></li>" +
        "            </ul>\n" +
        "        </div><!-- /.navbar-collapse -->\n"+
        "    </div><!-- /.container-fluid -->\n" +
        "</nav>";

    $("#navDiv").html(v_navHtml);

    $.ajaxSetup({
        beforeSend: function(xhr) {
            var token = window.localStorage.getItem("token");
            xhr.setRequestHeader("token", token);
        }
    })
    //查询用户是否已登录
   $.ajax({
       url:"http://localhost:8082/member/getMember",
       type:"post",
       dataType:"json",
       async:false,
       success:function(data){
             if(data.status==200){
                 isLogin =true;
                 $("#loginInfo").html('<a>欢迎'+data.data+'登录!!</a>');
                 $("#nav_ul").append('<li><a href="javascript:;" onclick="loginOut()">退出</a></li>');
             }
       }
   })

})

//加入购物车的方法
function buyCart(productId){
    if (!isLogin) {
        alert("请先登陆后再添加购物车！")
        location.href = 'login.html';
    }
    $.post(
        "http://localhost:8082/cart/buyCart",
        {"productId":productId},
        function (data) {
            if(data.status==200){
                initCartTotalNumber();
            }else{
                alert(data.msg);
            }
        }
    )

}
//退出
/*
function loginOut(){
    $.post(
        "http://localhost:8083/member/loginOut",
        function(data){
            if(data.status==200){
                location.href="/view/index-student.html";
            }
        }
    )
}
*/








