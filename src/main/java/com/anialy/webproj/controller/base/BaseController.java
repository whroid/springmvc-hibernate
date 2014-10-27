package com.anialy.webproj.controller.base;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.anialy.webproj.common.ResponseEntity;
import com.google.gson.Gson;


public class BaseController<T> {
	protected ResponseEntity<T> responseEntity = new ResponseEntity<T>();
//	private static Gson gson = new Gson();
//	
//	/**
//	 * 以文本形式下发数据
//	 * @param response
//	 * @param responseText
//	 */
//	protected void responseOutWithText(HttpServletResponse response,
//			String responseText) {
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			out.println(responseText);
//			out.close();
//			logger.debug("下发的数据是");
//			logger.debug(responseText);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}
//	
//	
//	/**
//	 * 以JSON格式输出
//	 * @param response
//	 */
//	protected void responseOutWithJson(HttpServletResponse response,
//			Object obj) {
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json; charset=utf-8");
//		PrintWriter out = null;
//		try {
//			out = response.getWriter();
//			String responseJsonStr = gson.toJson(obj);
//			out.append(responseJsonStr);
//			logger.debug("下发的数据是");
//			logger.info(responseJsonStr);
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				out.close();
//			}
//		}
//	}
}
