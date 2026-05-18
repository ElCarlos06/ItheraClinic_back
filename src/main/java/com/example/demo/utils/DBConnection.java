package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DBConnection {
    @Value("${db.host}")
    private String DB_HOST;

    @Value("${db.port}")
    private String DB_PORT;

    @Value("${db.name}")
    private String DB_NAME;

    @Value("${db.user}")
    private String DB_USER;

    @Value("${db.pass}")
    private String DB_PASS;

    @Bean
    public DataSource getConnection(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        //1.-Pagina web: com.mysql
        //2.gta sa: .cj
        //3.-protocol: jdbc
        //4.-clase: .Driver
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUsername(DB_USER);
        source.setPassword(DB_PASS);
        //protocolo: t_servicio://host:port/database ?=q-params
        source.setUrl(String.format("jdbc:mysql://%s:%s/%s",DB_HOST,DB_PORT,DB_NAME));
        return source;
    }
}
