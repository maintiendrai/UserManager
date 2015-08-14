package com.testapp;

import java.io.*;

public class Util {
	/**
	 * 解决中文问题，ISO转为GBK编码，用于POST，GET方式取得数据
	 * 
	 * @param str 原始文本
	 * @return 转码后的文本
	 */
	public static String iso2gb(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("ISO8859_1");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error:   Method:   dbconn.iso2gb   :"
						+ e.getMessage());
			}
			try {
				str = new String(tmpbyte, "GBK");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error:   Method:   dbconn.gb2iso   :"
						+ e.getMessage());
			}
		}
		return str;
	}

	/**
	 * 解决中文问题，GBK转ISO编码，用于从数据库中存入转码
	 * 
	 * @param str 原始文本
	 * @return 转换后文本
	 */
	public static String gb2iso(String str) {
		if (str != null) {
			byte[] tmpbyte = null;
			try {
				tmpbyte = str.getBytes("GBK");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error:   Method:   dbconn.gb2iso   :"
						+ e.getMessage());
			}
			try {
				str = new String(tmpbyte, "ISO8859_1");
			} catch (UnsupportedEncodingException e) {
				System.out.println("Error:   Method:   dbconn.gb2iso   :"
						+ e.getMessage());
			}
		}
		return str;
	}

}
