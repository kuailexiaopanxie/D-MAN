package com.wdkg.man.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
public class ApiConfigurable implements Configurable {

    private ApiSettingsComponent settingsComponent;

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "D-MAN";
    }

    @Override
    public @Nullable
    JComponent createComponent() {
        settingsComponent = new ApiSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        ApiSettingState settings = ApiSettingState.getInstance();
        return !settingsComponent.getApi().equals(settings.api) || !settingsComponent.getPrompt().equals(settings.prompt);
    }

    @Override
    public void apply() {
        ApiSettingState settings = ApiSettingState.getInstance();
        settings.api = settingsComponent.getApi();
        settings.prompt = settingsComponent.getPrompt();
    }

    @Override
    public void reset() {
        ApiSettingState settings = ApiSettingState.getInstance();
        settingsComponent.setApi(settings.api);
        settingsComponent.setPrompt(settings.prompt);
    }
}
