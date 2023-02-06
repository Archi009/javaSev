package co.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	//factory return -Mybatis
	private static SqlSessionFactory sqlSessionFactory;
	private DataSource() {		
	}
	public static SqlSessionFactory getInstance() {
		String resource = "config/mybatis-config.xml";
		InputStream is = null;
		try {
			is = Resources.getResourceAsStream(resource);//입력 스트림을 만들어줌
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sqlSessionFactory =new SqlSessionFactoryBuilder().build(is);
		
		return sqlSessionFactory;
	}
}
