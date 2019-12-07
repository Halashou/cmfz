package com.baizhi.test;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.converters.string.StringImageConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {
    private File file;
    private InputStream inputStream;
    /**
     * 如果string类型 必须指定转换器，string默认转换成string
     * 绝对路径 继承StringImageConverter 重写 写入方法
     */
    @ExcelProperty(converter = StringImageConverter.class)
    private String string;
    private byte[] byteArray;
    /**
     * 根据url导出
     *
     * @since 2.1.1
     */
    private URL url;
}
