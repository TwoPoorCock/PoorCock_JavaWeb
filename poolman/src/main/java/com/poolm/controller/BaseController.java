package com.poolm.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.poolm.entity.AppData;
import com.poolm.util.HttpRequestDeviceUtils;
import com.poolm.util.JsonHelper;

/**
 * �����������Ϊ��������������ṩ��������
 * 
 * @author ����
 * 
 */
public class BaseController {

	public static Logger log = Logger.getLogger(BaseController.class);

	/**
	 * ��̨������ͼ
	 */
	protected final static String ERROR_VIEW_ADMIN = "/error/404";

	/**
	 * ǰ̨������ͼ
	 */
	protected final static String ERROR_VIEW = "";

	protected final static int SUCCESS = 1;// �ɹ�

	protected final static int FAIL = 0;// ʧ��

	protected final static int EMPTY = 2;// ��

	protected final static int ERROR = 3;// ʧ��

	protected final static int FORMAT = 4;// ��ʽ

	protected final static int SIZE = 5;// ��С

	protected final static int EXIST = 6;// �Ѿ�����

	protected final static int PROPROTION = 7;// ͼƬ����

	protected final static String DATA = "data";// ����

	protected final static String STATUS = "status";// ״̬

	protected final static String MSG = "msg";

	protected final static String OUTSUPPLY = "outSupply";// �������

	public void renderJson(Object obj) {
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset=utf-8");
			out = response.getWriter();
			if (obj.getClass().getPackage().toString().indexOf("java.lang") != -1) {
				out.print(obj);
			} else {
				out.write(JSONObject.fromObject(obj).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				new Exception(e);
			}
		}
	}

	public void outJSON(Object obj) {
		PrintWriter out = null;
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			if (obj.getClass().getPackage().toString().indexOf("java.lang") != -1) {
				out.print(obj);
			} else {
				String str = JsonHelper.convertToJSON(obj, true);
				out.write(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (Exception e) {
				new Exception(e);
			}
		}
	}

	protected HttpServletRequest request = null;

	protected HttpServletResponse response = null;

	protected HttpSession session = null;

	protected String basePath = null;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ "/";
		model.addAttribute("domain", this.basePath);
	}

	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}

	protected HttpServletResponse getResponse() {
		HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder
				.getRequestAttributes()).getResponse();
		return resp;
	}

	protected HttpSession getSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request.getSession();
	}

	/** ����@ExceptionHandler�쳣���� */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);
		String requestType = request.getHeader("X-Requested-With");
		if (HttpRequestDeviceUtils.isMobileDevice(request) == false) {
			if (StringUtils.equals(requestType, "XMLHttpRequest")) {
				// ��ajax���󣬷���json
				returnJson(ex);
				return null;
			} else {
				return "error/500.jsp";
			}
		} else {
			// �ֻ����ʣ�����json
			returnJson(ex);
			return null;
		}
	}

	private void returnJson(Exception ex) {
		AppData data = new AppData();
		data.setStatus(FAIL);
		data.setMsg(ExceptionUtils.getMessage(ex));
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json;charset=UTF-8");
			out = response.getWriter();
			// out.print(JsonUtils.toJson(data));
		} catch (Exception e) {
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
}
