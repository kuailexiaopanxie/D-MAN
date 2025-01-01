package com.wdkg.man.process;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class ReplaceProcess {

    /**
     * 替换文本
     * @param execute execute
     * @param editor editor
     * @param project project
     */
    public static void replaceText(String execute, Editor editor, Project project) {
        Runnable runnable = () -> {
            ApplicationManager.getApplication().runWriteAction(() -> {
                Document document = editor.getDocument();
                document.replaceString(editor.getSelectionModel().getSelectionStart(), editor.getSelectionModel().getSelectionEnd(), execute);
            });
        };
        WriteCommandAction.runWriteCommandAction(project, runnable);
    }
}
