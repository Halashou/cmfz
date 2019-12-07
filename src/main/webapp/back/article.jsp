<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html;UTF-8"%>
<script>
    $(function () {
        $('#deptTable').jqGrid({
            autowidth:true,
            height:290,
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/article/findAll",
            datatype:"json",
            mtype:"post",
            colNames:["id","title","img","content","create_date","publish_date","status","guru_id","操作"],
            colModel:[
                {name:"id",hidden:true},
                {name:"title",align:"center",editable:true,editrules:{required:true}},
                {name:"img",align:"center",formatter:function(data){
                        return "<img style='width: 50px;height: 30px' src='"+data+"'>";
                    },editable:true,edittype:"file",editrules:{required:true}},
                {name:"content",width:50,align:"center",editable:true,editrules:{required:true}},
                {name:"create_date",align:"center",editable:true,editrules:{required:true}},
                {name:"publish_date",align:"center",editable:true,editrules:{required:true}},
                {name:"status",align:"center",editable:true,editrules:{required:true}},
                {name:"guru_id",align:"center",editable:true,edittype:"select",
                    // editoptions:{
                    //     //value:"1:展示中;2:冻结中"
                    //     dataUrl:""
                    // }

                },
                {name:"option",align:"center",
                    formatter:function (cellvalue, options, rowObject) {
                        var result="";
                        result +="<a href='javascript:void(0);' onclick=\"showModel('"+rowObject.id+"')\" class='btn btn-lg' title='查看详情'> <span class='glyphicon glyphicon-th-list'></span></a>";
                   return result;
                    }
                    }
                // {name:"dept.id",editable:true,edittype:"select",editoptions:{
                //         // value:"21:男;22:女"
                //         dataUrl:""
                //     },
                //     formatter:function(value,options,row){
                //         if(row.dept)return row.dept.name;
                //     }
                //},
            ],
            pager:"#pager",
            rowNum:"3",
            rowList:[6,9],
            viewrecords:true,
            caption:"专辑列表",
            //subGrid : true,
            // subGridRowExpanded : function(subgrid_id, row_id) {
            //     addTable(subgrid_id,row_id);
            // },
            // 删除表格方法
            // subGridRowColapsed : function(subgrid_id, row_id) {
            //
            // },
            //cellEdit:true, //单元格是否可编辑
            multiselect:true,
            editurl:"${pageContext.request.contextPath}/banner/oper",
        }).navGrid("#pager",//参数1: 分页工具栏id
            {add:false,edit:false,del:true,deltext:"删除"}, //参数2:开启工具栏编辑按钮 默认都是true
            {closeAfterEdit:true,reloadAfterSubmit:true,
                beforeShowForm:function (frm) {//编辑之前的函数，一般不做处理
                    //    frm.find("#url").attr("disabled","true"); 设置不可用  默认向后台传入“ ”串，
                }
            },//编辑面板的配置
            {closeAfterAdd:true,reloadAfterSubmit:true,//add添加之后的操作  返回response响应
                afterSubmit:function (response,postData) {
                    var bannerID = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        data:{bannerId:bannerID},
                        datatype:"json",
                        type:"post",
                        fileElementId:"url",//上传的前台接收别名
                        success:function (data) {

                        }
                    });
                    return postData;
                }
            },//添加面板的配置
            {}//删除的配置
        );
    });
    function showModel(id) {
        openM();
        $("#model_foot").attr("onclick","updateArticle()");
        let data = $('#deptTable').jqGrid("getRowData",id);//返回这一行的数据
        $("#inputId").val(data.id);
        $("#inputTitle").val(data.title);
        $("#inputStatus").val(data.status);
        $("#findGuru").val(data.guru_id);
        KindEditor.html("#editor_id",data.content);
        $("#modal_foot").html( "<button type=\"button\" class=\"btn btn-primary\" onclick=\"updateArticle()\">修改</button>"+
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>")
        }
    function openM() {
        $('#findGuru').empty();
        $("#Myform")[0].reset();//清空表单数据
        KindEditor.html("#editor_id", "");//清空富文本
        $.get(
            '${pageContext.request.contextPath}/guru/findAll',
            function (gurus) {
                $.each(gurus,function (i,guru){
                    var option=$('<option value='+guru.id+'>'+guru.nick_name+'</option>');
                    $('#findGuru').append(option);
                });
            },
            'json'
        );
        $('#myModal').modal("show");
        //富文本
        window.editor = KindEditor.create('#editor_id',{width:"100%",
            afterBlur:function () {//同步
                this.sync();
            },
            // 1. 指定图片上传路径
            uploadJson:"${pageContext.request.contextPath}/article/uploadKindeditorImg",
            allowFileManager:true,
            fileManagerJson:"${pageContext.request.contextPath}/article/showAllImgs",
            }
        );
        //最下面的按钮
        $("#modal_foot").html( "<button type=\"button\" class=\"btn btn-primary\" onclick=\"insertArticle()\">添加</button>"
           +"<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">关闭</button>")
         }
  function insertArticle(){
        $.ajaxFileUpload({
            url:"${pageContext.request.contextPath}/article/add",
            datatype:'json',
            type:"post",
            fileElementId:"inputFile",
            //通过这种方式获得数据 和文件上传输送到一个方法处理  注意这是form 和jqgrid的方式不同
            data:{id:$('#inputId').val(),title:$('#inputTitle').val(),
                status:$('#inputStatus').val(),guru_id:$('#findGuru').val(),content:$('#editor_id').val()},
            success:function () {
                //同步数据

                $('#deptTable').trigger("reloadGrid");
                closeModel();
                //关闭模态框
            }
        });



  }
  function updateArticle(){

      alert("update");
  }
  function closeModel(){
      $('#myModal').modal("hide");
  }
</script>
    <div class="col-md-10">
        <div>
            <div class="page-header">
                <h1><strong>文章管理</strong></h1>
            </div>
        </div>
<%--       标签页--%>
        <div>

            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章列表</a></li>
                <li role="presentation"><a href="#myModal" onclick="openM()">添加文章</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
<%--     文章列表--%>
                        <div class="tab-pane active" id="deptList1">
                            <div>
                                <table id="deptTable"></table>
                                <div id="pager"></div>
                            </div>
                        </div>

                </div>
                <div role="tabpanel" class="tab-pane" id="profile">
<%-- 添加文章 放在motal里--%>

                </div>
            </div>

        </div>
    </div>
<%--模态框--%>
<!-- Button trigger modal -->

<!-- Modal tabindex="-1"去掉 才可编辑图片上传的文本框的操作-->
<div id="myModal" class="modal fade" role="dialog" aria-labelledby="gridSystemModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gridSystemModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
     <%--书写表单--%>
                <div>
                    <form enctype="multipart/form-data" method="post" id="Myform">
                    <%-- id--%>
                        <input type="hidden" class="form-control" name="id" id="inputId">
                    <%-- title--%>
                        <div class="form-group">
                            <label>title</label>
                            <input type="text" class="form-control" name="title" id="inputTitle" placeholder="title">
                        </div>
                      <%-- img--%>
                        <div class="form-group">
                            <label for="inputFile">上传封面</label>
                            <input type="file" id="inputFile" name="imgFile">
                        </div>
                <%--图片展示状态--%>
                        <div class="form-group">
                            <label>封面状态</label>
                            <select id="inputStatus" class="form-control">
                                <option value="1">展示</option>
                                <option value="2">不展示</option>
                            </select>
                        </div>
                 <%--上师--%>
                        <div class="form-group">
                            <label>选择上师</label>
                            <select name="guru_id" class="form-control" id="findGuru">
                            </select>
                        </div>
                     <%--  富文本编辑框--%>
                        <div class="form-group">
                            <label for="editor_id">内容</label>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">

                        </textarea>
                        </div>
                        <div class="modal-footer" id="modal_foot">
                            <button type="button" class="btn btn-primary" onclick="insertArticle()">提交</button>
                            <button type="button" class="btn btn-danger" onclick="closeModel()">关闭</button>
                        </div>

                    </form>
                </div>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


