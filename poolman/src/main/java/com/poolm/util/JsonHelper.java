package com.poolm.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * ����:JSON����������
 * 
 * @since: 2011-12-22
 * **/
public class JsonHelper {

	/**
	 * ����:ת���ɶ����ͷ���
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Integer:�����Ͷ���
	 * **/
	public static Short convertToShort(String json) {

		return Short.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * ����:ת�������ͷ���
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Integer:���Ͷ���
	 * **/
	public static Integer convertToInteger(String json) {

		return Integer.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * ����:ת���ɳ����ͷ���
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Integer:�����Ͷ���
	 * **/
	public static Long convertToLong(String json) {

		return Long.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * ����:ת���ɸ������ͷ���
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Integer:�������Ͷ���
	 * **/
	public static Float convertToFloat(String json) {

		return Float.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * ����:ת����˫���ȸ������ͷ���
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Integer:˫���ȸ������Ͷ���
	 * **/
	public static Double convertToDouble(String json) {

		return Double.valueOf(String.valueOf(JSON.parse(json)));
	}

	/**
	 * ����:ת���ɶ��󷽷�
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Object:����
	 * **/
	public static String convertToString(String json) {
		return String.valueOf(JSON.parse(json));
	}

	/**
	 * ����:ת���ɶ��󷽷�
	 * 
	 * @param json
	 *            : json�ı�
	 * @return Object:����
	 * **/
	public static Object convertToObject(String json) {
		return JSON.parse(json);
	}

	/**
	 * ����:ת���ɶ��󷽷�
	 * 
	 * @param json
	 *            : json�ı�
	 * @param objClass
	 *            : �Զ������Class
	 * @return Object:����
	 * **/
	public static <T> T convertToObject(String json, Class<T> objClass) {
		return JSON.parseObject(json, objClass);
	}

	/**
	 * ����:ת����JSON�������ͷ���
	 * 
	 * @param obj
	 *            : ʵ�����
	 * @return JSON: json�����ı�����
	 * **/
	public static String convertToJSON(Object obj,boolean flag) {
		if(flag){
			SerializerFeature[] featureArr = { SerializerFeature.WriteMapNullValue };
			return JSON.toJSONString(obj,featureArr);
		}
		return JSON.toJSONString(obj);
	}

	/**
	 * ����:ת�����б��Ϸ���
	 * 
	 * @param json
	 *            : json�ı�
	 * @param objClass
	 *            : �Զ������Class
	 * @return List<T>:�������
	 * **/
	public static <T> List<T> convertToList(String json, Class<T> objClass) {

		return JSON.parseArray(json, objClass);
	}

	/**
	 * ������ַ������JSONObject���� ע��ֻ�ʺ�������ӦΪjson��ʽ��ַ
	 * 
	 * @param src
	 *            ��Դ��ַ
	 * @param code
	 *            ���뷽ʽ
	 */
	public static JSONObject getJsonObj(String src, String code) {
		InputStreamReader reader = null;
		BufferedReader in = null;
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "MSIE");
			connection.setConnectTimeout(10000);
			reader = new InputStreamReader(connection.getInputStream(), code);
			in = new BufferedReader(reader);
			String line = null; // ÿ������
			int lineFlag = 0; // ���: �ж���û������
			StringBuilder content = new StringBuilder();
			while ((line = in.readLine()) != null) {
				content.append(line);
				lineFlag++;
			}

			return lineFlag == 0 ? null : new JSONObject();
		} catch (SocketTimeoutException e) {
			// //System.out.println("���ӳ�ʱ!!!");
			throw new RuntimeException(e);
		} catch (JSONException e) {
			// //System.out.println("��վ��Ӧ����json��ʽ���޷�ת����JSONObject!!!");
			throw new RuntimeException(e);
		} catch (Exception e) {
			// //System.out.println("������ַ���Ի��ȡ�������쳣!!!");
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	


	/**
	 * ������ַ������JSONObject���� ע��ֻ�ʺ�������ӦΪjson��ʽ��ַ
	 * 
	 * @param src
	 *            ��Դ��ַ
	 * @param code
	 *            ���뷽ʽ
	 */
	public static String getJsonObjString(String src, String code) throws Exception {
		InputStreamReader reader = null;
		BufferedReader in = null;
		try {
			URL url = new URL(src);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "MSIE");
			connection.setConnectTimeout(10000);
			reader = new InputStreamReader(connection.getInputStream(), code);
			in = new BufferedReader(reader);
			String line = null; // ÿ������
			int lineFlag = 0; // ���: �ж���û������
			StringBuilder content = new StringBuilder();
			while ((line = in.readLine()) != null) {
				content.append(line);
				lineFlag++;
			}
			return lineFlag == 0 ? null : content.toString();
		} catch (SocketTimeoutException e) {
			// //System.out.println("���ӳ�ʱ!!!");
			throw new RuntimeException(e);
		} catch (Exception e) {
			// //System.out.println("������ַ���Ի��ȡ�������쳣!!!");
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	
	
	/**
	 * post
	 * @param httpUrl
	 * @param param
	 * @return
	 */
	public static String getJsonObjByPost(String httpUrl,Map<String,String> param) {
		try {

			InputStream in = null;
			BufferedReader rd = null;
			String responseContent = "";
			HttpURLConnection url_con = null;
			try {
				URL url = new URL(httpUrl);
				url_con = (HttpURLConnection) url.openConnection();
				url_con.setRequestMethod("POST");

				url_con.setConnectTimeout(500000000);// ����λ�����룩jdk
				url_con.setReadTimeout(500000000);// ����λ�����룩jdk 1.5�������,��������ʱ

				url_con.setDoOutput(true);

				OutputStream out = url_con.getOutputStream();
				String values = "";
				if (param != null && param.size() > 0) {
					for (Map.Entry<String, String> entry : param.entrySet()) {
						values = values + entry.getKey() + "=" + entry.getValue() + "&";
					}
					values = values.substring(0, values.length() - 1);
					out.write(values.getBytes("UTF-8"));
				}
				out.flush();
				out.close();

				in = url_con.getInputStream();
				rd = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String tempLine = rd.readLine();
				StringBuffer temp = new StringBuffer();
				String crlf = System.getProperty("line.separator");
				while (tempLine != null) {
					temp.append(tempLine);
					temp.append(crlf);
					tempLine = rd.readLine();
				}
				responseContent = temp.toString();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (url_con != null) {
						url_con.disconnect();
					}
					if (rd != null) {
						rd.close();
					}

					if (in != null) {
						in.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			return responseContent;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
