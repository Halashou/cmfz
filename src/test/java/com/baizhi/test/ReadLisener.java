package com.baizhi.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baizhi.entity.DemoData;

public class ReadLisener extends AnalysisEventListener<DemoData> {
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        //读一行之后调用  返回demoData对象  可以插入数据库
        System.out.println(demoData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //执行完读操作  结束调用
        System.out.println("over");
    }
}
