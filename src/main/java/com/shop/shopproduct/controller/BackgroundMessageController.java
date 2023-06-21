package com.shopproduct.controller;

import com.google.gson.Gson;
import com.shopproduct.common.backgroundtask.BackgroundFactory;
import com.shopproduct.common.backgroundtask.BackgroundHandler;
import com.shopproduct.entity.RequestMsg;

public class BackgroundMessageController {


    public String getBackgroundMessage(String taskName) {
        BackgroundHandler backgroundHandler = BackgroundFactory.getBackgroundHandler("productBackground");
        String str = backgroundHandler.getTaskResult(taskName);
        System.out.println("str: " + str);
        Gson gson = new Gson();
        RequestMsg requestMsg = new RequestMsg("longTime", "longTimeProcess", "");

        return gson.toJson(requestMsg);
    }
}
