package com.jgsu.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {
   /* private final static Class<SqlSessionFactoryUtils> LOCK = SqlSessionFactoryUtils.class;
    private static SqlSessionFactory sqlSessionFactory = null;
    private SqlSessionFactoryUtils(){}
    private static SqlSessionFactory getSqlSessionFactory(){
        //加入synchronized (LOCK)是为了防止再多线程中多次实例化SqlSessionFactory对象，从而保证其唯一性
        synchronized (LOCK){
            if (sqlSessionFactory != null){
                return sqlSessionFactory;
            }
            String resource = "SqlMapConfig.xml";
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }
    public static SqlSession openSqlSession(){
        if (sqlSessionFactory == null){
            getSqlSessionFactory();
        }
        return sqlSessionFactory.openSession();  //创建sqlSession对象
    }*/
}
