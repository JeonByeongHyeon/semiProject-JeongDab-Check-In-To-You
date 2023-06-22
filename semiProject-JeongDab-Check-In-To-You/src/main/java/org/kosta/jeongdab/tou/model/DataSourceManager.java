package org.kosta.jeongdab.tou.model;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/*
 *  Database Connection Pool : DBCP 
 *  객체를 생성해 Connection 을 빌려주고 반납받는 방식으로 
 *  시스템을 운영하기 위한 클래스 
 *  다양한 DBCP 구현체가 존재하므로  javax.sql.DataSource 타입으로 관리한다 
 *  ( spring 에서도 같은 타입으로 관리 )
 */
public class DataSourceManager {
	private static DataSourceManager instance=new DataSourceManager();
	private DataSource dataSource;
	private DataSourceManager() {
		//apache tomcat에서 제공하는 dbcp를 사용한다 
		BasicDataSource dbcp=new BasicDataSource();
		dbcp.setDriverClassName(DbConfig.DRIVER);
		dbcp.setUrl(DbConfig.DB_URL);
		dbcp.setUsername("scott");
		dbcp.setPassword("tiger");
		dbcp.setInitialSize(10);
		dbcp.setMaxTotal(30);
		this.dataSource=dbcp;
	}
	public static DataSourceManager getInstance() {
		return instance;
	}
	public DataSource getDataSource() {
		return dataSource;
	}
}









