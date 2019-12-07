package com.baizhi.test;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baizhi.entity.DemoData;

public class ConverterReadLisener extends AnalysisEventListener<ConverterData> {

    @Override
    public void invoke(ConverterData converterData, AnalysisContext analysisContext) {
        System.out.println(converterData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //执行完读操作  结束调用
        System.out.println("over");
    }
}
