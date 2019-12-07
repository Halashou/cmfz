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
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/ajaxfileupload.js"></script>
    <script src="${pageContext.request.contextPath}/static/kindeditor/kindeditor-all-min.js"></script>
    <script src="${pageContext.request.contextPath}/static/kindeditor/lang/zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/static/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/echarts/china.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script type="text/javascript">

        function funaDept(){
            var val = $("#aDept").val();
            $.get(
                '${pageContext.request.contextPath}/dept/add',
                "name="+val,
                function () {
                    $('#myModal').modal('hide');
                }
            );
        }

    </script>

</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <a class="navbar-brand" href="home.jsp">持名法洲后台管理系统<small>V1.0</small></a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><span class="navbar-text">欢迎:</span></li>
                <li>
                    <a class="navbar-brand" href="#">
                        <p style="color:#2e6da4;font-size: small">小黑</p>
                    </a>
                </li>
               <li><span class="navbar-text">退出登录</span></li>
                <li>
                    <a class="navbar-brand" href="#">
                        <span style="font-size: small" class="glyphicon glyphicon-log-out"></span>
                    </a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="row">
    <div class="col-md-2">
        <div class="panel-group" id="accordion" role="tablist">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                            用户管理
                        </a>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/back/user.jsp');">
                                用户列表
                            </a>
                        </div>
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/back/userecharts.jsp');">
                                注册趋势图
                            </a>
                        </div>
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/back/userechartsmap.jsp');">
                                用户分布图
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">

                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <div class="list-group">
                            <a class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/back/banner.jsp');">
                                轮播图管理
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFour">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseThree">
                           上师管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                    <div class="panel-body">
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/static/emp.jsp');">
                                员工管理
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false" aria-controls="collapseThree">
                            文章管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/back/article.jsp');">
                                文章列表
                            </a>
                        </div>
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/static/emp.jsp');">
                                文章搜索
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingSix">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix" aria-expanded="false" aria-controls="collapseThree">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                    <div class="panel-body">
                        <div class="list-group">
                            <a style="margin-bottom: 5px" class="list-group-item active" href="javascript:$('#content').load('${pageContext.request.contextPath}/back/album.jsp');">
                                章节管理
                            </a>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="col-md-10" id="content">
        <div>
            <div class="jumbotron" style="height: 25px;padding-top: 0px">
                <div class="container">
                    <h3>欢迎使用持名法洲<small>V1.0</small>后台管理系统</h3>
                </div>

            </div>
        </div>
<%--   轮播图--%>
            <div>
                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="width: 1000px;height: 500px">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox" style="width:1000px;height:500px">
                        <div class="item active">
                            <img style="width:1000px;height:500px" src="${pageContext.request.contextPath}/static/image/1.jpg">
                        </div>
                        <div class="item">
                            <img style="width:1000px;height:500px" src="${pageContext.request.contextPath}/static/image/2.jpg">
                        </div>
                        <div class="item">
                            <img style="width:1000px;height:500px" src="${pageContext.request.contextPath}/static/image/3.jpg">
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

            </div>
    </div>
</div>
<%--页脚--%>
<div class="panel-footer navbar-fixed-bottom">
    <div style="text-align: center"><h4>@ancx Made 2019<small> version-V1.0</small></h4></div>
</div>
</body>
</html>
