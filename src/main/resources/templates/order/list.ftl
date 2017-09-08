<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td> ${orderDTO.orderId}</td>
                    <td> ${orderDTO.buyerName}</td>
                    <td> ${orderDTO.buyerPhone}</td>
                    <td> ${orderDTO.buyerAddress}</td>
                    <td> ${orderDTO.orderAmount}</td>
                    <td> ${orderDTO.getOrderStatusEnum().message}</td>
                    <td> ${orderDTO.getPayStatusEnum().message}</td>
                    <td> ${orderDTO.createTime}</td>
                    <td> ${orderDTO.updateTime}</td>
                    <td> 详情</td>
                    <#if orderDTO.getOrderStatusEnum().message == "新订单" >
                    <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a></td>
                    </#if>
                </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>