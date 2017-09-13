<div>
<nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
    <ul class="nav sidebar-nav">
        <li class="sidebar-brand">
            <a href="#">
                卖家管理系统
            </a>
        </li>
        <li>
            <a href="/sell/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
                    class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/product/list">列表</a></li>
                <li><a href="/sell/seller/product/index">新增</a></li>
            </ul>
        </li>
        <li class="dropdown open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
                    class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
            <ul class="dropdown-menu" role="menu">
                <li class="dropdown-header">操作</li>
                <li><a href="/sell/seller/category/list">列表</a></li>
                <li><a href="/sell/seller/category/index">新增</a></li>
            </ul>
        </li>

        <li>
            <a href="/sell/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
        </li>
    </ul>
</nav>
</div>

<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                   提示
                </h4>
            </div>
            <div class="modal-body" id="message1">
                您有一条新订单
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="javascript:document.getElementById('notice').pause()" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="window.location='http://127.0.0.1:8080/sell/seller/order/list'">查看详情</button>
            </div>
        </div>

    </div>

</div>

<audio id="notice" loop = "loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg" />
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.2/jquery.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
    var webSocket = null;
    if ("WebSocket" in window) {
        webSocket = new WebSocket("ws://127.0.0.1:8080/sell/webSocket");
    } else {
        alert('该浏览器不支持websocket!');
    }
    webSocket.onopen = function (event) {
        console.log("webSocket连接打开");
    }
    webSocket.onclose = function(event) {
        console.log('连接关闭');
    }

    webSocket.onmessage = function(event) {
        console.log('收到消息:' + event.data);
        //弹窗提醒, 播放音乐
        document.getElementById('message1').innerHTML = event.data;
        $('#myModal').modal('show');
        document.getElementById("notice").play();
    }

    websocket.onerror = function (event) {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function (event) {
        websocket.close();
    }


</script>