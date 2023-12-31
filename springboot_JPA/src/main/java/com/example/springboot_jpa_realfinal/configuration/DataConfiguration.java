package com.example.springboot_jpa_realfinal.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;


//어플리케이션 실행 전 SPRING.JPA를 먼저 실행해준다는 의미.

//자바 설정파일로 선언
@Configuration
//어떤 설정파일을 읽을 것인지 지정
@PropertySource("classpath:/application.properties")
//매퍼 인터페이스 위치지정
@MapperScan(basePackages = "com.example.springboot_jpa_realfinal.mapper")
public class DataConfiguration {
    @Autowired
    //스프링컨테이너 호출
    ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }
    
    //Mybatis 연동
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean =
                new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(
                applicationContext.getResource("classpath:mybatis-config.xml")
        );
        sqlSessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:mapper/**/**.xml")
        );

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //bean은 선택사항..
    //JPA 연동
    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")//어플리케이션을 실행하기 전에  SPRING.JPA를 먼저 실행해준다는 의미
    public Properties hibernateConfig() {
        return new Properties();
    }


}
