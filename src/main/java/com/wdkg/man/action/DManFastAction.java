package com.wdkg.man.action;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.wdkg.man.executor.FastTranslateExecutor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * @Author 樊凯
 * @Date 2025/1/1
 **/
public class DManFastAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        // 获取当前项目
        Project project = event.getProject();
        if (project == null) {
            return;
        }

        // 获取当前编辑器
        Editor editor = event.getData(com.intellij.openapi.actionSystem.CommonDataKeys.EDITOR);
        if (editor == null) {
            return;
        }
        
        // 获取选中的文本
        String selectedText = editor.getSelectionModel().getSelectedText();

        // 获取实际触发的快捷键
        String shortcutText = KeymapUtil.getFirstKeyboardShortcutText(ActionManager.getInstance().getAction("DManFastAction"));

        if (StringUtils.isNotBlank(selectedText)) {
            String operation;
            if (shortcutText.contains("⇧⌘1") || shortcutText.contains("Shift+Ctrl+1")) {
                operation = "F";
            } else if (shortcutText.contains("⇧⌘2") || shortcutText.contains("Shift+Ctrl+2")) {
                operation = "M";
            } else if (shortcutText.contains("⇧⌘3") || shortcutText.contains("Shift+Ctrl+3")) {
                operation = "C";
            } else {
                return;
            }
            
            // 调用API
            new FastTranslateExecutor(editor, project).translateText(operation, selectedText);
        }
    }
}
