package com.testapp;

import java.util.*;
import java.io.*;

public class ConfigManager {

	private Properties dbProperties = null;

	private InputStream in = null;

	public ConfigManager() {
		System.out.println("ConfigManager construct...");
		dbProperties = null;
		in = null;
		try {
			dbProperties = new Properties();
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("my.conf");
			dbProperties.load(in);
			in.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("ConfigManager construct end.");
	}

	public ConfigManager(String filename) {
		System.out.println("ConfigManager construct...");
		dbProperties = null;
		in = null;
		try {
			dbProperties = new Properties();
			in = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(filename);
			dbProperties.load(in);
			in.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("ConfigManager construct end.");
	}

	public Properties getProperties() {
		return this.dbProperties;
	}

	public static Properties getServerConfig(String configFilePath) {
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(configFilePath);
			prop.load(fin);
			fin.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return prop;
	}

	public static Properties getServerConfig() {
		Properties prop = new Properties();
		String workdir = System.getProperty("user.dir");
		String configFilePath = workdir + File.separator + "conf"
				+ File.separator + "SmnmsStatistic.conf";
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(configFilePath);
			prop.load(fin);
			fin.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return prop;
	}

}
