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


    </script>


</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0">
    <tr><td width="20%">属性</td><td width="80%">值</td></tr>
    <tr><td>ticket</td><td>${ticket }</td></tr>
    <tr><td>按钮</td><td>
        <div  id="addressArea" style="min-height:526px;">
            <section class="SelectCityWrap" style="width:98%;">
                <section class="content">
                    <div class="nav">
                        <a href="http://shwx.huhuschool.com/weChat/red/getRed?ticket=${ticket }" class="weui_btn weui_btn_primary">发送红包</a>
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