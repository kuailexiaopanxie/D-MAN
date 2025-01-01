package com.wdkg.man.enums;

import com.wdkg.man.strategy.ClassNameConvert;
import com.wdkg.man.strategy.ConvertStrategy;
import com.wdkg.man.strategy.FieldNameConvert;
import com.wdkg.man.strategy.MethodNameConvert;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
@Getter
@Slf4j
public enum DefinitionTypeEnum {

    /**
     * 类名
     */
    CLASS("class", "C", ClassNameConvert.class),
    /**
     * 属性名
     */
    FIELD("field", "F", FieldNameConvert.class),
    /**
     * 方法名
     */
    METHOD("method", "M", MethodNameConvert.class);

    private final String typeName;

    private final String shortName;

    private final Class<? extends ConvertStrategy> strategyClass;

    DefinitionTypeEnum(String typeName, String shortName, Class<? extends ConvertStrategy> strategyClass) {
        this.typeName = typeName;
        this.shortName = shortName;
        this.strategyClass = strategyClass;
    }

    /**
     * 获取全部的转换方法
     *
     */
    public static List<String> getTypeNameList() {
        List<String> typeNameList = new ArrayList<>();
        for (DefinitionTypeEnum value : DefinitionTypeEnum.values()) {
            typeNameList.add(value.getTypeName());
        }
        return typeNameList;
    }

    public static ConvertStrategy getStrategyInstance(String typeName) {
        for (DefinitionTypeEnum value : DefinitionTypeEnum.values()) {
            if (value.getTypeName().equals(typeName)) {
                try {
                    Class<? extends ConvertStrategy> strategyClass = value.getStrategyClass();
                    return strategyClass.getDeclaredConstructor().newInstance();
                } catch (Exception exception) {
                    log.error("获取转换器失败", exception);
                }
            }
        }
        return null;
    }

    public static ConvertStrategy getStrategyInstanceByShortName(String shortName) {
        for (DefinitionTypeEnum value : DefinitionTypeEnum.values()) {
            if (value.getShortName().equals(shortName)) {
                try {
                    Class<? extends ConvertStrategy> strategyClass = value.getStrategyClass();
                    return strategyClass.getDeclaredConstructor().newInstance();
                } catch (Exception exception) {
                    log.error("获取转换器失败", exception);
                }
            }
        }
        return null;
    }
}
