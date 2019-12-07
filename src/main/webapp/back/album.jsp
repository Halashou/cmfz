<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html;UTF-8"%>
<script>
    $(function () {
        $('#deptTable').jqGrid({
            autowidth:true,
            height:290,
            styleUI:"Bootstrap",
            url:"${pageContext.request.contextPath}/album/findAll",
            datatype:"json",
            mtype:"post",
            colNames:["id","title","score","author","broadcast","count","desc","status","create_date","cover"],
            colModel:[
                {name:"id",hidden:true},
                {name:"title",align:"center",editable:true,editrules:{required:true}},
                {name:"score",align:"center",editable:true,editrules:{required:true}},
                {name:"author",align:"center",editable:true,editrules:{required:true}},
                {name:"broadcast",align:"center",editable:true,editrules:{required:true}},
                {name:"count",align:"center",editable:true,editrules:{required:true}},
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
                {name:"create_date",align:"center"},
                {name:"cover",align:"center",formatter:function(data){
                        return "<img style='width: 50px;height: 30px' src='"+data+"'>";
                    },editable:true,edittype:"file",editrules:{required:true},editoptions:{enctype:"multipart/form-data"}},
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
            subGrid : true,
            subGridRowExpanded : function(subgrid_id, row_id) {
                addTable(subgrid_id,row_id);
            },
            // 删除表格方法
            subGridRowColapsed : function(subgrid_id, row_id) {

            },
            //cellEdit:true, //单元格是否可编辑
            multiselect:true,
            editurl:"${pageContext.request.contextPath}/banner/oper",
        }).navGrid("#pager",//参数1: 分页工具栏id
            {add:true,edit:true,del:true,addtext:"添加",deltext:"删除",edittext:"编辑"}, //参数2:开启工具栏编辑按钮 默认都是true
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
    function addTable(subgrid_id,row_id){
        // 声明子表格|工具栏id
        var subgridTable = subgrid_id + "table";
        var subgridPage = subgrid_id + "page";
        // 根据下方空间id 创建表格及工具栏
        $("#"+subgrid_id).html("<table id='"+subgridTable+"'></table><div style='height: 50px' id='"+subgridPage+"'></div>")
        // 子表格JqGrid声明
        $("#"+subgridTable).jqGrid({
            url : "${pageContext.request.contextPath}/album/findAllChapters?id="+row_id,
            datatype : "json",
            colNames : [ 'id', 'title','size','time','create_time','name','操作'],
            colModel : [
                {name : "id"},
                {name : "title",editable:true},
                {name : "size"},
                {name : "time"},
                {name : "create_time",edittype:"date",editable:true},
                {name: "url", align: "center",editable:true,edittype:"file",editoptions:{enctype:"multpart/form-data"},
                    formatter(data){
                        if (data) {
                            console.log(data);
                            var split = data.split("_");
                            var element = split[1];
                            return element;
                        }
                    }
                },
                {name: "url", align: "center",
                    formatter(data) {
                        var result = "";
                        result += "<a href='javascript:void(0)' onclick=\"playAudio('" + data + "')\" class='btn btn-lg' title='播放'><span class='glyphicon glyphicon-play-circle'></span></a>";
                        result += "<a href='javascript:void(0)' onclick=\"downloadAudio('" + data + "')\" class='btn btn-lg' title='下载'><span class='glyphicon glyphicon-download'></span></a>";
                        return result;
                    }
                },
            ],
            rowNum : 20,
            pager : "#"+subgridPage,
            sortname : 'num',
            height : '100%',
            styleUI:"Bootstrap",
            editurl: "${pageContext.request.contextPath}/album/chapterOper?albumId="+row_id,
            autowidth:true
        });
        $("#" + subgridTable).jqGrid('navGrid',
            "#" + subgridPage,
            {edit : true, add : true, del : true, addtext:"添加",deltext:"删除",edittext:"编辑"},
            {},//edit
            {closeAfterAdd:true,reloadAfterSubmit:true,//add添加之后的操作  返回response响应
                afterSubmit:function (response,postData){
                    var chapterID = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/chapterUpload",
                        data:{chapterId:chapterID},
                        datatype:"json",
                        type:"post",
                        fileElementId:"url",//上传的前台接收别名
                        success:function (data){

                        }
                    });
                    return postData;
                }
            },//add
            {},//del
        );
    }
    function playAudio(data) {
        $('#myModal').modal('show');
        $('#adio').attr('src',data);
    }
    function downloadAudio(data){
       location.href="${pageContext.request.contextPath}/album/download?url="+data;
    }
</script>
    <div class="col-md-10">
        <div>
            <div class="page-header">
                <h1><strong>专辑管理</strong></h1>
            </div>
        </div>
        <div>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active" ><a href="#deptList1" data-toggle="tab" id="deptList">专辑列表</a></li>
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
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <audio controls id="adio" src=""></audio>
</div>
