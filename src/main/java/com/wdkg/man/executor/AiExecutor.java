package com.wdkg.man.executor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wdkg.man.config.OkHttpClientSingleton;
import com.wdkg.man.model.ChatRoleModel;
import com.wdkg.man.model.RoleContentModel;
import com.wdkg.man.setting.ApiSettingState;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.wdkg.man.constant.ModelConstant.*;



/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
@Slf4j
public class AiExecutor {

    public String getMethodNameByOllama(String name, String prompt){
        OkHttpClient client = OkHttpClientSingleton.getInstance();
        ChatRoleModel chatRoleModel = new ChatRoleModel();
        chatRoleModel.setModel(LLAMA);
        chatRoleModel.setStream(false);
        String prompt2 = ApiSettingState.getInstance().prompt;
        if (StringUtils.isNotBlank(prompt2)){
            prompt = prompt2;
        }
        chatRoleModel.setMessages(buildRoleContentModel(name, prompt));
        String api = ApiSettingState.getInstance().api;
        MediaType mediaType = MediaType.get("application/json");
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(chatRoleModel), mediaType);
        Request request = new Request.Builder()
                .url(api)
                .post(requestBody)
//                .addHeader("Origin", "*")
                .build();
        try (Response response = client.newCall(request).execute()) {
            // 处理响应
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                String string = Objects.requireNonNull(responseBody).string();
                JSONObject jsonObject = JSON.parseObject(string);
                JSONObject messageJson = jsonObject.getJSONObject(MESSAGE);
                return messageJson.getString(CONTENT);
            } else {
                // 请求失败
                System.out.println("请求失败，响应码: " + response.code());
                return "请求失败，响应码: " + response.code();
            }
        } catch (IOException e) {
            // 发生异常
            log.error("请求失败", e);
        }
        return "请求失败";
    }

    public static List<RoleContentModel> buildRoleContentModel(String name, String prompt){
        List<RoleContentModel> modelList = new ArrayList<>(initRole(prompt));
        RoleContentModel model = new RoleContentModel();
        model.setRole(USER_ROLE);
        model.setContent(name);
        modelList.add(model);
        return modelList;
    }

    private static List<RoleContentModel> initRole(String prompt) {
        List<RoleContentModel> initModelList = new ArrayList<>();
        RoleContentModel model = new RoleContentModel();
        model.setRole(SYSTEM);
        model.setContent(prompt);
        initModelList.add(model);
        return initModelList;
    }
}
