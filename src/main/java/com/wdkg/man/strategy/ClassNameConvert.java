package com.wdkg.man.strategy;

import com.wdkg.man.executor.AiExecutor;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class ClassNameConvert implements ConvertStrategy{
    @Override
    public String execute(String msg) {
        String prompt = "你是一个java语言专家，你只需要将接收到的内容根据java语言的命名规转换为规范java的类名，注意大小写，不需要其他任何修饰词";
        AiExecutor aiExecutor = new AiExecutor();
        String methodName = aiExecutor.getMethodNameByOllama(msg,prompt);
        return methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
    }
}
