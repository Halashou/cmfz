<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html;UTF-8"%>
<script>

</script>
    <div class="col-md-10">
        <div>
            <div class="page-header">
                <h1><strong>用户注册趋势</strong></h1>
            </div>
        </div>
        <div>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active" ><a href="#deptList1" data-toggle="tab" id="deptList">趋势图</a></li>
            </ul>
<!--      选项卡面板-->
            <div class="tab-content">
                        <div class="tab-pane active" id="deptList1">
                            <div>
                                <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
                                <div id="main" style="width: 600px;height:400px;"></div>
                                <script type="text/javascript">
                                    // 基于准备好的dom，初始化echarts实例
                                    var myChart = echarts.init(document.getElementById('main'));

                                    // 指定图表的配置项和数据
                                    var option = {
                                        title: {
                                            text: '项目注册趋势'
                                        },
                                        tooltip: {},
                                        legend: {
                                            data:['男','女']
                                        },
                                        xAxis: {
                                            data: ["最近1天","最近7天","最近30天","最近365天"]
                                        },
                                        yAxis: {},

                                    };

                                    // 使用刚指定的配置项和数据显示图表。
                                    myChart.setOption(option);
                                    $.get(
                                        '${pageContext.request.contextPath}/user/findsexAndregist',
                                        function (data) {
                                            myChart.setOption({
                                                series: [
                                                    {
                                                        name: '男',
                                                        type: 'bar',
                                                        data: data.men
                                                    },
                                                    {
                                                        name: '女',
                                                        type: 'bar',
                                                        data: data.women
                                                    }
                                                ]
                                            });
                                        },'json');
                                </script>

                            </div>
                        </div>

            </div>

        </div>

    </div>
