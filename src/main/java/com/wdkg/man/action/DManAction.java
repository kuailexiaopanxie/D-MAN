package com.wdkg.man.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.wdkg.man.enums.DefinitionTypeEnum;
import com.wdkg.man.executor.PopupExecutor;

import java.util.List;
import java.util.Objects;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class DManAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = (Editor) event.getDataContext().getData("editor");
        Project project = event.getProject();

        // 创建要展示的列表数据
        List<String> typeNameList = DefinitionTypeEnum.getTypeNameList();
        // 创建列表弹出窗口
        ListPopup listPopup = JBPopupFactory.getInstance()
                .createListPopup(new PopupExecutor("Name Type", typeNameList, editor, project));
        // 在屏幕中间显示列表弹出窗口
        listPopup.showCenteredInCurrentWindow(Objects.requireNonNull(event.getProject()));
    }
}
