package com.testapp;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

public class ConnectionFactory {
	static String driverName = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

	static String dbUrl = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=pubs";

	static String dbuser = "sa";

	static String dbpassword = "";

	private static ConnectionFactory _instance = null;

	ComboPooledDataSource cpds = null;

	private ConnectionFactory(Properties dbProperties) {
		driverName = dbProperties.getProperty("driverName");
		dbUrl = dbProperties.getProperty("dbUrl");
		dbuser = dbProperties.getProperty("dbuser");
		dbpassword = dbProperties.getProperty("dbpassword");
		System.out.println(driverName);
		System.out.println(dbUrl);
		System.out.println(dbuser);
		System.out.println(dbpassword);
		cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass(driverName);
			// loads the jdbc driver
			cpds.setJdbcUrl(dbUrl);
			cpds.setUser(dbuser);
			cpds.setPassword(dbpassword);
			cpds.setMaxStatements(180);
			cpds.setMinPoolSize(1);
			cpds.setMaxPoolSize(10);
		} catch (Exception e) {
			System.out.println("error here:" + e);
		}
	}

	public static ConnectionFactory getInstance(Properties dbProperties) {
		if (_instance == null) {
			_instance = new ConnectionFactory(dbProperties);
		}
		return _instance;
	}

	public static ConnectionFactory getInstance() {

		return _instance;
	}

	// static {
	// Properties dbProperties = new Properties();
	// InputStream in = Thread.currentThread().getContextClassLoader()
	// .getResourceAsStream("my.conf");
	// try {
	// dbProperties.load(in);
	// driverName = dbProperties.getProperty("driverName");
	// dbUrl = dbProperties.getProperty("dbUrl");
	// dbuser = dbProperties.getProperty("dbuser");
	// dbpassword = dbProperties.getProperty("dbpassword");
	//
	// } catch (Exception e) {
	// System.err.println(e);
	// }
	//
	// }

	public Connection getConnection2() throws Exception {
		Class.forName(driverName);

		Connection c = DriverManager.getConnection(dbUrl, dbuser, dbpassword);
		
		return c;
	}
	
	public static void main(String[] args){
		try{
			System.out.println(driverName);
			System.out.println(dbUrl);
			System.out.println(dbuser);
			System.out.println(dbpassword);
			
			Class.forName(driverName);

			Connection c = DriverManager.getConnection(dbUrl, dbuser, dbpassword);
			if(c.isClosed()){
				System.out.println("isclosed");
			}else{
				System.out.println("is open");
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
	

	public Connection getConnection() throws SQLException {
		long begin = System.currentTimeMillis();

		Connection conn = cpds.getConnection();

		System.out.println("got connection cost: "
				+ (System.currentTimeMillis() - begin) + " ms");

		return conn;

	}

}
