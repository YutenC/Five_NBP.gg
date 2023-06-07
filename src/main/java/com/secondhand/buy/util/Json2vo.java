package com.secondhand.buy.util;


import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Json2vo {
	public static final Gson GSON = new GsonBuilder().create();
	public static final String JSON_MIME_TYPE = "application/json";

	
	
	
	public static <V> V jsonTovo(HttpServletRequest request, Class<V> classOfvo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <V> void writevo2Json(HttpServletResponse response, V vo) {
		response.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(vo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
