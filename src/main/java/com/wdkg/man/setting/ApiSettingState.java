package com.wdkg.man.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.application.ApplicationManager;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
@State(
        name = "com.wdkg.man.setting.ApiSettingState",
        storages = @Storage("ApiSettingState.xml")
)
public class ApiSettingState implements PersistentStateComponent<ApiSettingState> {

    public String api = "";

    public String prompt = "";

    public static ApiSettingState getInstance(){
        return ApplicationManager.getApplication().getService(ApiSettingState.class);
    }

    @Nullable
    @Override
    public ApiSettingState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull ApiSettingState apiSettingState) {
        this.api = apiSettingState.api;
        this.prompt = apiSettingState.prompt;
    }
}
