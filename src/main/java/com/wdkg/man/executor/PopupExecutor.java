package com.wdkg.man.executor;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.wdkg.man.enums.DefinitionTypeEnum;
import com.wdkg.man.process.ReplaceProcess;
import com.wdkg.man.strategy.ConvertStrategy;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class PopupExecutor extends BaseListPopupStep<String> {

    private final Editor editor;

    private final Project project;

    public PopupExecutor(@NotNull String title, @NotNull List<String> values, Editor editor, Project project) {
        super(title, values);
        this.editor = editor;
        this.project = project;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Nullable
    @Override
    public PopupStep onChosen(String selectedValue, boolean finalChoice) {
        // 处理选中的值
        if (StringUtils.isNotBlank(selectedValue)) {
            String selectedText = editor.getSelectionModel().getSelectedText();
            if (StringUtils.isNotBlank(selectedText)){
                ConvertStrategy strategyInstance = DefinitionTypeEnum.getStrategyInstance(selectedValue);
                ReplaceProcess.replaceText(Objects.requireNonNull(strategyInstance).execute(selectedText), editor, project);
            }
        }
        // 如果是最终选择，则关闭弹出窗口
        return finalChoice ? PopupStep.FINAL_CHOICE : super.onChosen(selectedValue, finalChoice);
    }

    @Override
    public boolean hasSubstep(@Nullable String selectedValue) {
        // 在这里可以定义是否有子步骤
        return false;
    }

    @Nullable
    @Override
    public String getTextFor(String value) {
        // 返回列表项的显示文本
        return value;
    }

    @Nullable
    @Override
    public Icon getIconFor(String value) {
        // 返回列表项的图标，如果不需要图标，则返回 null
        return null;
    }

}
