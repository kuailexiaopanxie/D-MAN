package com.wdkg.man.strategy;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public interface ConvertStrategy {

    /**
     * 名称转换
     * @param msg 待转换的名称
     * @return 结果
     */
    String execute(String msg);
}
