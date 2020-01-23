$(function(){
    //进行AJAX请求全局设置
    $.ajaxSetup({
        beforeSend:function(request){
            //从LocalStorage中取出Token
            var token = localStorage.getItem("token");
            //将token放入请求的头信息中
            request.setRequestHeader("abc",token);
        }
    });
});