package org.kosta.jeongdab.tou.model;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class DataSourceManager {
	private static DataSourceManager instance = new DataSourceManager();
	private DataSource dataSource;

	private DataSourceManager() {
		BasicDataSource dbcp = new BasicDataSource();
		dbcp.setDriverClassName(DbConfig.DRIVER);
		dbcp.setUrl(DbConfig.DB_URL);
		dbcp.setUsername("scott");
		dbcp.setPassword("tiger");
		dbcp.setInitialSize(10);
		dbcp.setMaxTotal(30);
		this.dataSource = dbcp;
	}

	public static DataSourceManager getInstance() {
		return instance;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
