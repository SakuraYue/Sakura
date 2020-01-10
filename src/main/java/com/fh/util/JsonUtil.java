package com.fh.util;

import com.alibaba.druid.support.json.JSONUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUtil {

    public static void outJson(HttpServletResponse response, Object obj) throws IOException {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        String json = JSONUtils.toJSONString(obj);
        out.println(json);
    }

}
