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
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>商品id</th>
                            <th>名称</th>
                            <th width="100">图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.content as productInfo>
                        <tr>
                            <td>${productInfo.productId}</td>
                            <td>${productInfo.productName}</td>
                            <td><img height="90" width="100" src="${productInfo.productIcon}" alt=""></td>
                            <td>${productInfo.productPrice}</td>
                            <td>${productInfo.productStock}</td>
                            <td>${productInfo.productDescription}</td>
                            <td>${productInfo.getCategoryName()}</td>
                            <td>${productInfo.createTime}</td>
                            <td>${productInfo.updateTime}</td>
                            <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                            <#if productInfo.getProductStatusEnum().message == "在架" >
                                <td><a href="/sell/seller/product/off_sale?productId=${productInfo.productId}" style="color: chocolate">下架</a></td>
                            <#else >
                                <td><a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a></td>
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