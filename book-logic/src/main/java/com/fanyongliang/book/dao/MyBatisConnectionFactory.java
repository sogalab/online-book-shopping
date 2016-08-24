package com.fanyongliang.book.dao;



import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.fanyongliang.book.dao.prop.PropertiesParser;
import com.fanyongliang.book.dao.prop.SimpleProperties;


public class MyBatisConnectionFactory {
	private static SqlSessionFactory sqlOaInternalDBSessionFactory;

	static{	
		try {
			String resource = "config/mybatis-config.xml";
			String dataSql=SimpleProperties.getInstance().getStringProperty("database.path");
			Properties dataSqlProperties=new PropertiesParser(dataSql).getProperties();
			Reader reader = Resources.getResourceAsReader(resource);
			if (sqlOaInternalDBSessionFactory == null) {
				
				sqlOaInternalDBSessionFactory = new SqlSessionFactoryBuilder().build(reader, "book", dataSqlProperties);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static SqlSessionFactory getSqlAccountSessionFactory() {
		return sqlOaInternalDBSessionFactory;
	}

}

