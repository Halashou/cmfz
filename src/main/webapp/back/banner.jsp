<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html;UTF-8"%>
<script>
    $(function () {
        $('#deptTable').jqGrid({
            autowidth:true,
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/banner/findAll",
            datatype:"json",
            mtype:"post",
            colNames:["id","title","url","href","create_date","desc","status"],
            colModel:[
                {name:"id",hidden:true},
                {name:"title",align:"center",editable:true,editrules:{required:true}},
                {name:"url",align:"center",formatter:function(data){
                    return "<img style='width: 50px;height: 30px' src='"+data+"'>";
                },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"}},
                {name:"href",align:"center",editable:true,editrules:{required:true}},
                {name:"create_date",align:"center"},
                {name:"desc",align:"center",editable:true,editrules:{required:true}},
                {name:"status",align:"center",editable:true,edittype:"select",
                    editoptions:{
                    value:"1:展示中;2:冻结中"
                    },
                    formatter:function (date) {
                            if(date=="1"){
                                return "展示中";
                            }else{
                                return "冻结中";
                            }
                         }
                    },
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
            caption:"轮播图列表",
            //cellEdit:true, //单元格是否可编辑
            multiselect:true,
            editurl:"${pageContext.request.contextPath}/banner/oper",
        }).navGrid("#pager",//参数1: 分页工具栏id
            {add:true,edit:true,del:true,addtext:"添加",deltext:"删除",edittext:"编辑"}, //参数2:开启工具栏编辑按钮 默认都是true
            {closeAfterEdit:true,reloadAfterSubmit:true,
                beforeShowForm:function (frm) {//编辑之前的函数，一般不做处理
                    frm.find("#url").attr("disabled","true");// 设置不可用  默认向后台传入“ ”串，
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
</script>
    <div class="col-md-10">
        <div>
            <div class="page-header">
                <h1><strong>轮播图管理</strong></h1>
            </div>
        </div>
        <div>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#deptList1" data-toggle="tab" id="deptList">轮播图列表</a></li>
                <li><a href="${pageContext.request.contextPath}/banner/downeasyExcel">导出轮播图信息</a></li>
                <li><a href="javascript:void(0);" data-toggle="tab" >导出轮播图模板</a></li>
                <li><a href="javascript:void(0);" data-toggle="tab" >导入轮播图信息</a></li>
            </ul>
<!--      选项卡面板-->
            <div class="tab-content">
                        <div class="tab-pane active" id="deptList1">
                            <div>
                                <table id="deptTable"></table>
                                <div id="pager"></div>
                            </div>
                        </div>

            </div>

        </div>

    </div>
