package com.wdkg.man.executor;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.wdkg.man.enums.DefinitionTypeEnum;
import com.wdkg.man.process.ReplaceProcess;
import com.wdkg.man.strategy.ConvertStrategy;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;

/**
 * @Author 樊凯
 * @Date 2025/1/1
 **/
public class FastTranslateExecutor {

    private final Editor editor;

    private final Project project;

    public FastTranslateExecutor(Editor editor, Project project) {
        this.editor = editor;
        this.project = project;
    }

    public void translateText(String shortcutText, String selectedText) {
        if (StringUtils.isNotBlank(selectedText) && StringUtils.isNotBlank(shortcutText)) {
            ConvertStrategy strategyInstance = DefinitionTypeEnum.getStrategyInstanceByShortName(shortcutText);
            ReplaceProcess.replaceText(Objects.requireNonNull(strategyInstance).execute(selectedText), editor, project);
        }
    }
}
