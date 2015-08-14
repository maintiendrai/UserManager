package com.testapp;

import java.io.*;

public class Util {
	/**
	 * ����������⣬ISOתΪGBK���룬����POST��GET��ʽȡ������
	 * 
	 * @param str ԭʼ�ı�
	 * @return ת�����ı�
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
	 * ����������⣬GBKתISO���룬���ڴ����ݿ��д���ת��
	 * 
	 * @param str ԭʼ�ı�
	 * @return ת�����ı�
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
