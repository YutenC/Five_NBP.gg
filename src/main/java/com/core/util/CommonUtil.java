package com.core.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import static com.core.util.Constants.GSON;
import static com.core.util.Constants.JSON_MIME_TYPE;
//import static core.util.Constants.*;

public class CommonUtil {

//	變成完整版的Hibernate不需要DataSource屬性
//	public static Connection getConnection() throws NamingException, SQLException {
//		if (DATASOURCE == null) {
//			DATASOURCE = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/javaFramework");
//		}
//		return DATASOURCE.getConnection();
//	}

	// 將進來的JSON物件轉為Member物件輸入
	public static <E> E json2Pojo(HttpServletRequest request, Class<E> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 將進來的Member物件轉換成JSON物件輸出
	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		response.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
