package com.poolm.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ValidateHelper {


	public static String cleanInvalidCharacters(String in) {
		StringBuilder out = new StringBuilder();
		char current;
		if (in == null || ("".equals(in))) {
			return "";
		}
		for (int i = 0; i < in.length(); i++) {
			current = in.charAt(i);
			if (current == 0xa0 || current == 0xc2)
				continue;

			out.append(current);


		}
		return out.toString().trim();
	}
	public static boolean isChinese(String name){
		char[] ch = name.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				return true;
			}
			return false;
		}
		return false;

	}




	
	/**
	 * �ж϶���Ϊ���ַ�������Ϊnull�������������һ���������򷵻�true
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNullOrEmpty(Object obj) {
		boolean isEmpty = false;
		if (obj == null) {
			isEmpty = true;
		} else if (obj instanceof String) {
			isEmpty = ((String) obj).trim().isEmpty();
		} else if (obj instanceof Collection) {
			isEmpty = (((Collection) obj).size() == 0);
		} else if (obj instanceof Map) {
			isEmpty = ((Map) obj).size() == 0;
		} else if (obj.getClass().isArray()) {
			isEmpty = Array.getLength(obj) == 0;
		}
		return isEmpty;
	}
	
	/**
	 * ��� email�����Ƿ���ȷ ��ȷ����д�� ʽΪ username@domain
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkEmail(String text, int length) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && text.length() <= length;
	}

	/**
	 * ���绰���� �Ƿ���ȷ ��ȷ�� ʽ 012-87654321��0123-87654321��0123��7654321
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkTelephone(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches(
						"(0\\d{2,3}-\\d{7,8})|" + 
						"(0\\d{9,11})|" + 
						"(\\d{7})|" + 
						"(\\d{8})|" + 
						"(4\\d{2}\\d{7})|" + 
						"(4\\d{2}-\\d{7})|" + 
						"(4\\d{2}-\\d{3}-\\d{4})|" + 
						"(4\\d{2}-\\d{4}-\\d{3})");
	}

	/**
	 * ����ֻ����� �Ƿ���ȷ
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkMobilephone(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}");
	}

	/**
	 * ����������� ���Ƿ���ȷ
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkChineseName(String text, int length) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("^[\u4e00-\u9fa5]+$") && text.length() <= length;
	}

	/**
	 * ����ַ������Ƿ��пո񣬰����м�ո������β�ո�
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkBlank(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("^\\s*|\\s*$");
	}

	/**
	 * ����ַ����� ����HTML��ǩ
	 * 
	 * @param text
	 * @return
	 */

	public static boolean checkHtmlTag(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
	}

	/**
	 * ���URL�� ��Ϸ�
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkURL(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("[a-zA-z]+://[^\\s]*");
	}

	/**
	 * ���IP�Ƿ� �Ϸ�
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkIP(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
	}



	/**
	 * ���QQ�Ƿ� �Ϸ������������֣�����λ����Ϊ0���15λ
	 * 
	 * @param text
	 * @return
	 */

	public static boolean checkQQ(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("[1-9][0-9]{4,13}");
	}

	/**
	 * ����ʱ��Ƿ� �Ϸ�
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkPostCode(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("[1-9]\\d{5}(?!\\d)");
	}

	/**
	 * ������֤�� ��Ϸ�,15λ��18λ(�������һλΪX)
	 * 
	 * @param text
	 * @return
	 */
	public static boolean checkIDCard(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("\\d{15}|\\d{18}|(\\d{17}[x|X])");
	}

	/**
	 * ��������Ƿ� �����涨����
	 * 
	 * @param length
	 * @param text
	 * @return
	 */
	public static boolean checkLength(String text, int length) {
		return ((ValidateHelper.isNullOrEmpty(text)) ? 0 : text.length()) <= length;
	}

	/**
	 * �ж��Ƿ�Ϊ����
	 * @param text
	 * @return
	 */
	public static boolean isNumber(String text) {
		if(ValidateHelper.isNullOrEmpty(text))return false;
		return text.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"); 
	}
}
