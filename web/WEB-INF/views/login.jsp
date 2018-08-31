<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="BS" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>OAuth2.0网页授权</title>
    <meta name="viewport" content="width=device-width,user-scalable=0">
    <style type="text/css">
        *{margin:0; padding:0}
        table{border:1px dashed #B9B9DD;font-size:12pt}
        td{border:1px dashed #B9B9DD;word-break:break-all; word-wrap:break-word;}
    </style>
    <script type="text/javascript" src="/weChat/js/jquery-2.1.4.js"></script>
    <script type="text/javascript">
        function createPay(){
            var url = "http://shwx.huhuschool.com/weChat/pay/createRed";
            var openId = "23545";
            var money = 1;
            var sendname = "${nickname }";
            $.ajax({
                url : url,
                type:"POST",
                dataType : 'json', // 服务器返回的格式,可以是json或xml等
                data:{
                    uid:openId,         //客户
                    money:money,
                    sendname:sendname
                },
                success : function(result) { // 服务器响应成功时的处理函数
                    alert(result);
                    var date = result;
                    var ticket = date.ticket;
                    $('#ticket').val(ticket);
                },
                error : function(data, status, e) { // 服务器响应失败时的处理函数
                    alert("初始化支付接口失败，请联系系统运营商", 'error');
                }
            });
        }


        function getPay() {
            var url = "http://shwx.huhuschool.com/weChat/pay/getRed";
            var ticket = $('#ticket').val(ticket);
            $.ajax({
                url : url,
                type:"POST",
                dataType : 'json', // 服务器返回的格式,可以是json或xml等
                data:{
                    ticket:ticket         //客户
                },
                success : function(result) { // 服务器响应成功时的处理函数
                    alert(result);
                },
                error : function(data, status, e) { // 服务器响应失败时的处理函数
                    alert("初始化支付接口失败，请联系系统运营商", 'error');
                }
            });
        }




        function toPay(){
            var url = "http://syp.huhuschool.com/weChat/pay/jsApi?payMoney=0.01&openId=${openid }&state=${state }";
            var openId = "${openid }";
            var state = "${state }";
            $.ajax({
                url : "/weChat/pay/red",
                type:"POST",
                dataType : 'json', // 服务器返回的格式,可以是json或xml等
                data:{
                    openId:openId

                },
                success : function(result) { // 服务器响应成功时的处理函数

                    var success = result.success;
                    var success_1 = result.success_1;

                    if(success == 1 && success_1 == 0){
                        //调用抽奖方法
                        alert("恭喜一等奖！");
                        return ;
                    }

                    if(success == 1 && success_1 == 1){
                        alert("你抽过奖了！");
                        return ;
                    }

                    location.href = url;

                },
                error : function(data, status, e) { // 服务器响应失败时的处理函数
                    alert("初始化支付接口失败，请联系系统运营商", 'error');
                }
            });
        }
    </script>


</head>
<body>
<input type="hidden" id="ticket" class="ticket" value="">
<input type="hidden" id="weixinOperId" value="${openid }">
<table width="100%" cellspacing="0" cellpadding="0">
    <tr><td width="20%">属性</td><td width="80%">值</td></tr>
    <tr><td>OpenID</td><td>${openid }</td></tr>
    <tr><td>昵称</td><td>${nickname }</td></tr>
    <tr><td>商品订单号</td><td>${state }</td></tr>
    <tr><td>金额</td><td><input id="payMoney" name="payMoney" type="text" value="0.01">0.01</td></tr>
    <tr><td>按钮</td><td>
        <div  id="addressArea" style="min-height:526px;">
            <section class="SelectCityWrap" style="width:98%;">
                <section class="content">
                    <div class="nav">
                        <a href="http://shwx.huhuschool.com/weChat/pay/jsApi?payMoney=0.01&openId=${openid }&state=${state }" class="weui_btn weui_btn_primary">立即支付</a>
                        <%--<a class=""  nav="nav_1" onclick="initPay(11,1)">马上支付</a>--%>
                    </div>
                    <div class="nav">
                        <a href="http://shwx.huhuschool.com/weChat/red/createRed?money=1&sendname=${nickname }" class="weui_btn weui_btn_primary">创建红包</a>
                        <%--<a class=""  nav="nav_1" onclick="initPay(11,1)">马上支付</a>--%>
                    </div>
                    <button id="btnOption1" class="btn btn-info "
                            onclick="getPay()">
                        <p class="fa fa-folder-open"></p> 发送红包
                    </button>
                </section>
            </section>
        </div>
    </td>
    </tr>
    <tr><td>按钮</td><td>
        <div  id="addressArenb" style="min-height:526px;">
            <section class="SelectCityWrap" style="width:98%;">
                <section class="content">

                    <button id="btnOption" class="btn btn-info "
                            onclick="createPay()">
                        <p class="fa fa-folder-open"></p> 创建红包
                    </button>





                    <div class="nav">
                        <a href="http://shwx.huhuschool.com/weChat/pay/getPay?payMoney=0.01&openId=${openid }&state=${state }" class="weui_btn weui_btn_primary">发送红包</a>
                        <%--<a class=""  nav="nav_1" onclick="initPay(11,1)">马上支付</a>--%>
                    </div>
                </section>
            </section>
        </div>
    </td>
    </tr>
</table>

</body>
</html>