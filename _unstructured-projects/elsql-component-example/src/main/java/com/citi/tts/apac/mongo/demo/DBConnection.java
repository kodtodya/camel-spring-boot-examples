package com.citi.tts.apac.mongo.demo;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import oracle.jdbc.pool.OracleDataSource;


//ESTABLISHES CONNECTION WITH THE DATABASE. REPLACE YOUR URL, USERNAME AND PASSWORD.
@Configuration
public class DBConnection {
	
	@Bean("myDataSource")
	DataSource dataSource() throws SQLException
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUsername("username");
		dataSource.setPassword("password");
		dataSource.setUrl("jdbc:oracle:thin:@oraashkd01-scan.apac.nsroot.net:8888/APCONCD");
		return dataSource;
	}

}
