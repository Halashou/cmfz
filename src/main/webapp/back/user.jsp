<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html;UTF-8"%>
<script>
    $(function () {
        $('#deptTable').jqGrid({
            autowidth:true,
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/user/findAll",
            datatype:"json",
            mtype:"post",
            colNames:["id","phone","password","salt","status","photo","name","nick_name","sex","sign","location","regest_date","last_login"],
            colModel:[
                {name:"id",hidden:true},
                {name:"phone",align:"center",editable:true,editrules:{required:true}},
                {name:"password",align:"center",editable:true,editrules:{required:true}},
                {name:"salt",align:"center",editable:true,editrules:{required:true}},
                {name:"status",align:"center",editable:true,editrules:{required:true}},
                {name:"photo",align:"center",editable:true,editrules:{required:true},
                    formatter:function(data){
                        return "<img style='width: 50px;height: 30px' src='"+data+"'>";
                    },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"}
                },
                {name:"name",align:"center",editable:true,editrules:{required:true}},
                {name:"nick_name",align:"center",editable:true,editrules:{required:true}},
                {name:"sex",align:"center",editable:true,editrules:{required:true}},
                {name:"sign",align:"center",editable:true,editrules:{required:true}},
                {name:"location",align:"center",editable:true,editrules:{required:true}},
                {name:"regest_date",align:"center"},
                {name:"last_login",align:"center",editable:true,editrules:{required:true}}
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
                <h1><strong>用户管理</strong></h1>
            </div>
        </div>
        <div>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active" ><a href="#deptList1" data-toggle="tab" id="deptList">用户列表</a></li>
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
