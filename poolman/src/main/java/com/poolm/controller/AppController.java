package com.poolm.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.poolm.entity.AppData;
import com.poolm.exception.DataAccessException;
import com.poolm.exception.SystemException;
import com.poolm.util.JsonHelper;
import com.poolm.util.ValidateHelper;

/**
 * �����������Ϊ��������������ṩ��������
 * 
 * @author ����
 * 
 */
public class AppController extends BaseController {

    public final static int FAIL = 0;// ʧ��

    public final static int SUCCESS = 1;// �ɹ�

    protected final static String PARAM_ERROR = "��������";// ��������

    protected final static String PARAM_NULL = "������ȫ����Ϊ��";// ����Ϊ��

    protected final static String PARAM_ILLEGAL = "���������Ϲ淶";// ���������Ϲ淶

    protected final static String PARAM_EXIST = "�����Ѵ���";// �����Ѵ���

    protected final static String PARAM_NOT_EXIST = "����������";// ����������

    protected final static String RESULT_EMPTY = "���Ϊ��";// ���Ϊ��

    protected final static String USER_NOT_EXIST = "�û�������";// �û�������

    protected final static String USER_EXIST = "�û��Ѵ���";// �û��Ѵ���

    // ����
    protected final static String ACCOUNT_OR_PASSWORD_ERROR = "�û��������������";// �û������������

    // ����
    protected final static String SMS_NOT_MATCH = "��֤�벻ƥ��";// ������֤�벻ƥ��

    // ����
    protected final static String SMS_IS_TIMEOUT = "��֤�볬ʱ";// ������֤�볬ʱ

    // ����
    protected final static String SMS_SEND_FAIL = "������֤�����ʧ��";// ������֤�����ʧ��

    // ����
    protected final static String PHONE_IS_EXIST = "�˺�����ע��";// �˺�����ע��

    // ����
    protected final static String REGISTER_BINDING_FAIL = "ע�����ʧ��";// ע�����ʧ��

    /**
     * app�˵��ýӿڷ������쳣
     */
    protected final static String SERVER_ERROR = "server_error";

    /**
     * app��url����
     */
    protected final static String URL_ERROR = "url_error";

    /**
     * app��δ֪�쳣
     */
    protected final static String UNKNOW_ERROR = "unknow_error";

    /**
     * app�˷���ӿ����ݲ��쳣
     */
    protected final static String DATA_ACCESS_ERROR = "app�˷���ӿ����ݲ��쳣";

    /**
     * ϵͳ�����쳣
     */
    protected final static String SYSTEM_ERROR = "system_access_error";

    /**
     * �ͻ��������쳣
     */
    protected final static String APP_REQUEST_ERROR = "app_request_error";

    /**
     * ajax������߿ͻ�������,�����ص�json����
     * 
     * @param obj
     */
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
                out.write(JsonHelper.convertToJSON(obj, true));
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

    /**
     * ajax������߿ͻ�������,�����ص�json����
     * 
     * @param obj
     */
    public void outJSON(Object obj, boolean flag) {
        PrintWriter out = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            if (obj.getClass().getPackage().toString().indexOf("java.lang") != -1) {
                out.print(obj);
            } else {
                out.write(JsonHelper.convertToJSON(obj, flag));
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

    /**
     * ��ȡ����
     * 
     * @param key
     * @return
     * @throws UnsupportedEncodingException
     */
    protected String getParameter(String key) throws UnsupportedEncodingException {
        Object obj = null;
        String objStr = (obj = request.getAttribute(key)) == null ? request.getParameter(key) : obj.toString();

        if (request.getMethod().equals("POST")) {
            return (obj = request.getAttribute(key)) == null ? request.getParameter(key) : obj.toString();
        }
        if (!ValidateHelper.isNullOrEmpty(objStr))
            return new String(objStr.getBytes("ISO8859_1"), "UTF-8");
        else
            return null;
    }

    /**
     * ��ʽ��ʱ��
     * 
     * @param date ����
     * @param pattern ��ʽ
     * @return ʱ���ַ���
     */
    public String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * ��ʽ��ʱ��
     * 
     * @param date ����
     * @return ʱ���ַ���
     */
    public String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    private Logger logger = null;

    @SuppressWarnings("rawtypes")
    protected void exception(Exception e, Class clazz) {
        AppData cr = new AppData();
        cr.setStatus(FAIL);
        logger = Logger.getLogger(clazz.getName());
        try {
            if (e instanceof DataAccessException) {// ���ݿ������쳣
                logger.error(clazz.getName() + " DataAccessException error:", e);
                cr.setMsg(DATA_ACCESS_ERROR);
            } else if (e instanceof SystemException) {// ����ʱ�쳣
                logger.error(clazz.getName() + " SystemException error:", e);
                cr.setMsg(SYSTEM_ERROR);
            } else if (e instanceof NullPointerException) {
                logger.error(clazz.getName() + " NullPointerException error:", e);
                cr.setMsg(PARAM_ERROR);
            } else {// �����쳣
                logger.error(clazz.getName() + " Exceipton error:", e);
                cr.setMsg(UNKNOW_ERROR);
            }
        } catch (Exception e2) {
            logger.error(clazz.getName() + " appRequest error:", e);
            cr.setMsg(APP_REQUEST_ERROR);
        } finally {
            e.printStackTrace();
            outJSON(cr);
        }

    }

    protected HttpServletRequest request = null;

    protected HttpServletResponse response = null;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

}

