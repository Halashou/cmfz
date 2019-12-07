<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html;UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/jqgrid/ui.jqgrid-bootstrap.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/jquery.jqGrid.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/jqgrid/grid.locale-cn.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.js"></script>
    <style type="text/css">
        body {
            background-image: url(${pageContext.request.contextPath}/static/image/login.jpg);
            background-size: cover;
        }
        .a{
            background:rgba(240,214,200,0.5);
        }
    </style>
    <script type="text/javascript">
        function funChangeImage(){
            console.log($('#imga'));
            $('#imga').attr('src','${pageContext.request.contextPath}/admin/code?'+Math.random());
        }
    </script>
</head>
<body>
<div class="modal-dialog" style="margin-top: 15%; width: 350px;">
    <div class="modal-content" style="background:rgba(240,214,200,0.5);">
        <div class="modal-header">

            <h4 class="modal-title text-center" id="myModalLabel">持明法洲</h4>
        </div>
        <form id="loginForm" method="post" action="${pageContext.request.contextPath}/admin/login">
            <div class="modal-body" id = "model-body">
                <div class="form-group">
                    <input type="text" class="a form-control"placeholder="用户名" autocomplete="off" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="a form-control" placeholder="密码" autocomplete="off" name="password">
                </div>
                <span id="msg">
                    验证码：<img id="imga" src="${pageContext.request.contextPath}/admin/code">&nbsp;&nbsp;<a href="javascript:void(0);" onclick="funChangeImage()">看不清，换一张</a>
                </span>
                <div class="form-group">
                    <input type="text" class="a form-control" placeholder="验证码" autocomplete="off" name="code">
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-group">
                    <button style="background:rgba(240,214,270,0.5);" type="submit" class="a btn btn-primary form-control" id="log">登录</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>