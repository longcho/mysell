<!DOCTYPE html>
<html lang="en">

<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">
    <#--边栏sidebar-->
    <#include "../common/nav.ftl">
<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fruid" style="padding-left: 15px ; padding-right: 15px">

            <div class="row clearfix">
                <div class="col-md-12 column">
                    <nav class="navbar navbar-default navbar-static-top" role="navigation">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1"><span
                                    class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                                    class="icon-bar"></span><span class="icon-bar"></span></button>
                            <a class="navbar-brand" href="#">订单号:</a>
                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                            <form class="navbar-form navbar-left" , role="search" ,method="get" ,
                                  action="/sell/seller/order/detail">
                                <div class="form-group">
                                    <input type="text" class="form-control" name="orderId"/>
                                </div>
                                <button type="submit" class="btn btn-default">查询</button>
                            </form>

                        </div>

                    </nav>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-hover table-bordered table-condensed">
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
                        </tr>
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
                            <td><a href="/sell/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>
                            <#if orderDTO.getOrderStatusEnum().message == "新订单" >
                                <td><a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a></td>
                            <#else >
                                <td></td>
                            </#if>
                        </tr>
                        </#list>
                        </tbody>
                    </table>

                    <div class="col-md-12 column ">
                        <ul class="pagination pull-right">
                            <li><a href="/sell/seller/order/list?page=1&size=${size}">首页</a></li>
                        <#if currentPage gt 1 >
                        <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">
                        <#else>
                        <li class="disabled"><a href="#">
                        </#if>
                            上一页</a> </li>

                        <#if currentPage-3 gt 1>
                            <li class="disabled"><a href="#">..</a></li>
                        </#if>
                        <#list currentPage-3..currentPage+3 as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <#if index gte 1 && index  lte page>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}</a>
                                    </li>
                                </#if>

                            </#if>
                        </#list>
                        <#if currentPage-3 lt page>
                            <li class="disabled"><a href="#">..</a></li>
                        </#if>


                        <#if currentPage  lt page >
                        <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">
                        <#else>
                        <li class="disabled"><a href="#">
                        </#if>下一页</a> </li>
                            <li><a href="/sell/seller/order/list?page=${page}&size=${size}">末页</a></li>
                        </ul>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>


</body>
</html>