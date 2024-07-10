package com.MobileDev.controller.auth;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil {

    public static Map<String, Object> generateResponse(int code, String message, Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("code", code);
        response.put("message", message);
        response.put("datas", data);
        response.put("warnings", new String[]{});
        return response;
    }
}