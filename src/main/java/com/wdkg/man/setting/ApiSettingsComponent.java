package com.wdkg.man.setting;

import lombok.Getter;

import javax.swing.*;


/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class ApiSettingsComponent {

    @Getter
    private final JPanel panel;

    private JTextField apiTestField;

    private JTextField promptTestField;

    public ApiSettingsComponent() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        apiTestField = new JTextField(40);
        promptTestField = new JTextField(40);

        JPanel apiPanel = new JPanel();
        apiPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));  // 移除边距
        apiPanel.add(new JLabel("请输入Ollama的API:"));
        apiPanel.add(apiTestField);

        JPanel promptPanel = new JPanel();
        promptPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));  // 移除边距
        promptPanel.add(new JLabel("请配置prompt:"));
        promptPanel.add(promptTestField);

        panel.add(apiPanel);
        panel.add(promptPanel);
    }

    public String getApi() {
        return apiTestField.getText();
    }

    public void setApi(String api) {
        apiTestField.setText(api);
    }

    public String getPrompt() {
        return promptTestField.getText();
    }

    public void setPrompt(String prompt) {
        promptTestField.setText(prompt);
    }

}
