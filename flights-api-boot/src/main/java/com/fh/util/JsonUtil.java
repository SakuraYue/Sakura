package com.fh.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtil {

	public static void outJson(HttpServletResponse response, Object object){
		response.setContentType("application/json;charset=utf-8");
		response.setCharacterEncoding("utf-8");
        try {
			PrintWriter writer = response.getWriter();
			writer.print(JSON.toJSONString(object));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
