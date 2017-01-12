package com.poolm.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHelper {
	
	/**
	 * 判断对象为空字符串或者为null，如果满足其中一个条件，则返回true
	 * @param object
	 * @return
	 */
	public static boolean isNullOrEmpty(Object object) {
		return object == null || "".equals(object);
	}
	/**
	 * ͨ�õĽ�ȡ�ַ����ķ���
	 * 
	 * @param length
	 *            ��ȡ�ַ����ĳ���
	 * @param bool
	 *            �Ƿ�ȥ��html��ǩ
	 * @param text
	 *            Ҫ������ַ���
	 * @return
	 */
	public static String subString(int length, boolean isReplaceHtml, String text) {
		if (isNullOrEmpty(text))
			return null;
		text = isReplaceHtml ? replaceHTML(text) : text.trim();
		if (text.length() > length) {
			return text.substring(0, length)+"...";
		}
		return text;
	}
	/**
	 * ���ı�ȥ��HTML��ǩ
	 * 
	 * @param text
	 * @return
	 */
	public static String replaceHTML(String text) {
		if (isNullOrEmpty(text))
			return null;
		return text.replaceAll("\t|\r|\n|<[^>]*?>|&nbsp;", "").trim().replaceAll("\u3000", "");
	}
	/**
	 * ��ȡ���ı������е�ͼƬ���ӵ�ַ
	 * 
	 * @return
	 */
	public static List<String> getTextImageSrc(String text) {
		if (TextHelper.isNullOrEmpty(text))
			return null;
		String regex = "<\\s*[I|i][m|M][g|G]\\s+([^>]*)\\s*>";
		Pattern pa = Pattern.compile(regex, Pattern.DOTALL);
		Matcher ma = pa.matcher(text);
		List<String> list = new ArrayList<String>();
		while (ma.find()) {// �����жϻ����������Ƿ���ͼƬ
			list.add(ma.group());
		}
		if (list.size() != 0) {// ��ͼƬ�ļ�
			List<String> imgSrcList = null;
			String a = null;
			for (String s : list) {
				ma = Pattern.compile("[s|S][R|r][c|C]=[\"|'](.*?)[\"|']").matcher(s);
				if (ma.find()) {
					a = ma.group();
					if (imgSrcList == null)
						imgSrcList = new ArrayList<String>();
				} else {
					a = null;
				}
				if (a != null) {
					a = a.replaceAll("[s|S][R|r][c|C]=[\"|']", "").replaceAll("[\"|']", "");
					imgSrcList.add(a);
				}
			}
			if (imgSrcList != null && imgSrcList.size() != 0)
				return imgSrcList;
			else
				return null;
		} else {
			return null;
		}
	}
	/**
	 * �ж��û����Ƿ�Ϊ��Ч
	 * @param nick
	 * @return
	 */
	public static boolean isUserNameStyleString(String nick){
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
		return nick.matches(regex);
	}
}
